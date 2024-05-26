import os
import pandas as pd
import signal
from flask import Flask, request, jsonify, send_file
import matplotlib
from ntscraper import Nitter
import json
from functools import lru_cache
import matplotlib.pyplot as plt

matplotlib.use("Agg")
import CrawlTweetFromNitter

app = Flask(__name__)


# Tạo một phiên bản của Nitter lưu vào cache
@lru_cache(maxsize=None)
def create_nitter():
    return Nitter(log_level=1, skip_instance_check=False)


@app.route("/crawl_tweet", methods=["POST"])
def crawl_tweet_route():
    data = request.json
    name = data["name"]
    mode = data["mode"]
    amount = data["amount"]
    file_name = data["file_name"]

    # Lấy phiên bản Nitter từ cache
    scraper = create_nitter()

    list_tweet = scraper.get_tweets(name, mode=mode, number=amount, max_retries=100)

    with open(
        "news-aggregator\\resource\\data\\tweetData\\" + file_name + ".json", mode="w"
    ) as file_json:
        json.dump(list_tweet["tweets"], file_json)

    return jsonify({"message": "Success"})


@app.route("/draw_chart", methods=["POST"])
def draw_chart_route():

    file_json_name = request.json["file_json_name"]
    file_pictures_name = request.json["file_pictures_name"]

    # Tạo đường dẫn tương đối đến thư mục chứa dữ liệu
    data_directory = "news-aggregator/resource/data/tweetData"
    file_path = os.path.join(data_directory, file_json_name + ".json")
    image_path = os.path.join(data_directory, file_pictures_name + ".png")

    # Chuyển đổi đường dẫn tương đối thành đường dẫn tuyệt đối
    image_path_absolute = os.path.abspath(image_path)

    list_tweet = pd.read_json(path_or_buf=file_path)
    data_list = []

    for index, tweet in list_tweet.iterrows():
        try:
            data = [
                tweet["link"],
                tweet["content"],
                tweet["datetimeCreation"],
                tweet["comments"],
                tweet["retweets"],
                tweet["quotes"],
                tweet["likes"],
            ]
            data_list.append(data)
        except KeyError as e:
            print("KeyError: {}. Skipping tweet at index {}".format(e, index))

    data_list_pd = pd.DataFrame(
        data_list,
        columns=["link", "content", "time", "comment", "retweet", "quote", "like"],
    )
    data_list_pd = data_list_pd.sort_values(by="time")

    plt.figure(figsize=(20, 10))
    ax = plt.gca()

    ax.plot(data_list_pd["time"], data_list_pd["comment"], label="comment", marker="o")
    ax.plot(data_list_pd["time"], data_list_pd["retweet"], label="retweet", marker="o")
    ax.plot(data_list_pd["time"], data_list_pd["like"], label="like", marker="o")
    ax.plot(data_list_pd["time"], data_list_pd["quote"], label="quote", marker="o")

    plt.xlabel("Time")
    plt.ylabel("Count")
    plt.title("Tweet Stats over Time")
    plt.grid(True)
    plt.legend()

    plt.savefig(image_path_absolute)
    plt.close()

    return send_file(image_path_absolute, mimetype="image/png")


@app.route("/json_analyst", methods=["POST"])
def json_analyst():
    data = request.json
    file_json_name = data.get("file_json_name")
    if not file_json_name:
        return jsonify({"error": "Missing file_json_name parameter"}), 400

    data_directory = "news-aggregator/resource/data/tweetData"
    file_path = os.path.join(data_directory, file_json_name + ".json")

    if not os.path.exists(file_path):
        return jsonify({"error": f"File {file_path} does not exist"}), 404

    try:
        list_tweet = pd.read_json(path_or_buf=file_path)
    except ValueError as e:
        return jsonify({"error": f"Error reading JSON file: {e}"}), 500

    data_list = []

    for index, tweet in list_tweet.iterrows():
        try:
            data = [
                tweet["link"],
                tweet["content"],
                tweet["datetimeCreation"],
                tweet["comments"],
                tweet["retweets"],
                tweet["quotes"],
                tweet["likes"],
            ]
            data_list.append(data)
        except KeyError as e:
            print(f"KeyError: {e}. Skipping tweet at index {index}")

    if not data_list:
        return jsonify({"error": "No valid tweet data found"}), 404

    data_list_pd = pd.DataFrame(
        data_list,
        columns=["link", "content", "time", "comment", "retweet", "quote", "like"],
    )

    highest_interaction_tweet = data_list_pd.loc[
        data_list_pd[["comment", "retweet", "quote", "like"]].sum(axis=1).idxmax()
    ]
    response = {
        "highestInteractionTweet": highest_interaction_tweet.to_dict(),
    }

    return jsonify(response)


@app.route("/crawl_nitter", methods=["POST"])
def crawl_nitter():
    data = request.json
    user_name = data.get("user_name")
    if not user_name:
        return jsonify({"error": "Missing user_name"}), 400

    try:
        result = CrawlTweetFromNitter.main(user_name)
        return jsonify({"status": "success", "data": result}), 200
    except Exception as e:
        return jsonify({"error": str(e)}), 500


@app.route("/shutdown", methods=["POST"])
def shutdown_server():
    print("Shutting down server...")
    os.kill(os.getpid(), signal.SIGINT)


if __name__ == "__main__":
    app.run(debug=True)
