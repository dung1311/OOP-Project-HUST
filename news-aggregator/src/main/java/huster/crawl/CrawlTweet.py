from flask import Flask, request, jsonify, send_file
from ntscraper import Nitter
import json
from functools import lru_cache
import matplotlib.pyplot as plt
import pandas as pd
import os

app = Flask(__name__)

# test
@app.route("/hello")
def hello():
    return "Hello"

# Tạo một phiên bản của Nitter bên ngoài hàm crawl_tweet
@lru_cache(maxsize=None)
def create_nitter():
    return Nitter(log_level=1, skip_instance_check=False)


@app.route('/crawl_tweet', methods=['POST'])
def crawl_tweet_route():
    data = request.json
    name = data['name']
    mode = data['mode']
    amount = data['amount']
    file_name = data['file_name']

    # Lấy phiên bản Nitter từ cache
    scraper = create_nitter()

    list_tweet = scraper.get_tweets(
        name, mode=mode, number=amount, max_retries=100)

    with open('news-aggregator\\recourse\\data\\' + file_name + '.json', mode='w') as file_json:
        json.dump(list_tweet['tweets'], file_json)

    return jsonify({"message": "Success"})


# @app.route('/draw_chart', methods=['POST'])
# def draw_chart_route():
#     data = request.json
#     file_name = data['file_name']

#     image_path = draw_table(file_name)

#     return send_file(image_path, mimetype='image/png')


@app.route('/draw_chart', methods=['POST'])
def draw_chart_route():
    file_name = request.json['file_name']  # Extract file_name from request data

    # Tạo đường dẫn tương đối đến thư mục chứa dữ liệu
    data_directory = 'news-aggregator/recourse/data'
    file_path = os.path.join(data_directory, file_name + '.json')
    image_path = os.path.join(data_directory, 'output.png')

    list_tweet = pd.read_json(path_or_buf=file_path)
    data_list = []

    for index, tweet in list_tweet.iterrows():
        try:
            data = [tweet['date'], tweet['stats']['comments'], tweet['stats']['retweets'],
                    tweet['stats']['quotes'], tweet['stats']['likes']]
            data_list.append(data)
        except KeyError as e:
            print("KeyError: {}. Skipping tweet at index {}".format(e, index))

    data_list_pd = pd.DataFrame(
        data_list, columns=['time', 'comment', 'retweet', 'quote', 'like'])
    data_list_pd = data_list_pd.sort_values(by='time')

    plt.figure(figsize=(20, 10))
    ax = plt.gca()

    ax.plot(data_list_pd['time'], data_list_pd['comment'],
            label='comment', marker='o')
    ax.plot(data_list_pd['time'], data_list_pd['retweet'],
            label='retweet', marker='o')
    ax.plot(data_list_pd['time'], data_list_pd['like'],
            label='like', marker='o')
    ax.plot(data_list_pd['time'], data_list_pd['quote'],
            label='quote', marker='o')

    plt.xlabel('Time')
    plt.ylabel('Count')
    plt.title('Tweet Stats over Time')
    plt.grid(True)
    plt.legend()

    plt.savefig(image_path)
    plt.close()

    return send_file(image_path, mimetype='image/png')


@app.route('/shutdown', methods=['POST'])
def shutdown():
    # Lệnh để đóng server Flask
    shutdown_server()
    return 'Server shutting down...'


def shutdown_server():
    func = request.environ.get('werkzeug.server.shutdown')
    if func is None:
        raise RuntimeError('Not running with the Werkzeug Server')
    func()


if __name__ == '__main__':
    app.run(debug=True)
