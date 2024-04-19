from ntscraper import Nitter 
import pandas as pd 
import matplotlib.pyplot as plt
import seaborn as sns
import json
import os

scraper = Nitter(log_level=1, skip_instance_check=False)

def crawl_tweet(name, mode, amount, file_name):
    tweetList_full = scraper.get_tweets(
        name, mode=mode, number=amount, max_retries=100)
    
    if not os.path.exists(f'news-aggregator\\recourse\\data\\{file_name}.json'):
        with open(file=f'news-aggregator\\recourse\\data\\{file_name}.json', mode='w') as file_json:
            json.dump(tweetList_full['tweets'], file_json)
    else:
        with open(file=f'news-aggregator\\recourse\\data\\{file_name}.json', mode='w') as file_json:
            json.dump(tweetList_full['tweets'], file_json)

def draw_table(file_name):
    list_tweet = pd.read_json(path_or_buf=f'news-aggregator\\recourse\\data\\{file_name}.json')
    data_list = []
    for index, tweet in list_tweet.iterrows():
        try:
            data = [
                tweet['date'],
                tweet['stats']['comments'],
                tweet['stats']['retweets'],
                tweet['stats']['quotes'],
                tweet['stats']['likes']
            ]
            data_list.append(data)
        except KeyError as e:
            print(f"KeyError: {e}. Skipping tweet at index {index}")

    data_list_pd = pd.DataFrame(
        data_list, columns=['time', 'comment', 'retweet', 'quote', 'like'])
    
    data_list_pd = data_list_pd.sort_values(by='time')
    
    fig, ax = plt.subplots(figsize=(20, 10))
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
    plt.show()

#test
#draw_table('news-aggregator\\recourse\\data\\tweets.json')
# file_name = 'abc'
# crawl_tweet('Bitcoin', mode='user', amount=10, file_name=file_name)
# draw_table(file_name)