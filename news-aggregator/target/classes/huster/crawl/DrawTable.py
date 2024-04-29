import matplotlib.pyplot as plt
import pandas as pd


def draw_table(file_name):
    file_path = 'news-aggregator\\recourse\\data\\' + file_name + '.json'
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

    plt.savefig('news-aggregator\\recourse\\data\\output.png')


# test
# file_name = 'abc'
# draw_table(file_name)
