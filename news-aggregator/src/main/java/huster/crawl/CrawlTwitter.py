from ntscraper import Nitter
import pandas as pd

scraper = Nitter(log_level = 1, skip_instance_check = False)
def crawl_tweet(name, type, amount):
    data_list = []
    tweets = scraper.get_tweets(name, mode=type, number=amount)
    for tweet in tweets['tweets']:
        data = [tweet['link'], tweet['text'], tweet['date'], tweet['stats']['comments'], tweet['stats']['retweets'], tweet['stats']['likes']]
        data_list.append(data)
    data = pd.DataFrame(data_list, columns=['links' , 'text', 'date', 'comments', 'retweets', 'likes'])
    return data

data = crawl_tweet('itscrypto_news', 'user', 5)
data.to_json(path_or_buf='news-aggregator\recourse\data\tweets.json')