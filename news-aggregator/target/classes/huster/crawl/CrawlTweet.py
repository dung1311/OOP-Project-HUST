from flask import Flask, request, jsonify
from ntscraper import Nitter
import json
from functools import lru_cache

app = Flask(__name__)

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
    
    list_tweet = scraper.get_tweets(name, mode=mode, number=amount, max_retries=100)

    with open(file='news-aggregator\\recourse\\data\\' + file_name + '.json', mode='w') as file_json:
        json.dump(list_tweet['tweets'], file_json)

    return jsonify({"message": "Success"})

if __name__ == '__main__':
    app.run(debug=True)
