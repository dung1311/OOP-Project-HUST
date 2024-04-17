from ntscraper import Nitter #thư viện crawl tweet
from numpy import column_stack
import pandas as pd #chuyển dữ liệu sang dạng bảng
#thư viện vẽ biểu đồ
import matplotlib.pyplot as plt 
from matplotlib.dates import DateFormatter
import seaborn as sns

# scraper = Nitter(log_level = 1, skip_instance_check = False)

# #lấy tất cả dữ liệu, chia cột rồi lưu và tweetList_full
# tweetList_full = scraper.get_tweets('SpaceX',mode= 'user',number= 10)
# tweetList_full_pd = pd.DataFrame(tweetList_full['tweets'])
# tweetList_full_pd.to_excel('news-aggregator\\recourse\\data\\tweetList_full.xlsx')

# #hàm crawl Tweet, trả về những giá trị cần dùng
# def crawl_tweet(name, amount):
#     list_tweets = scraper.get_tweets(name, mode = 'user', number = amount)
#     data_list = []
#     for tweet in list_tweets['tweets']:
#         pictures_string = ', '.join(tweet['pictures']) if tweet['pictures'] else 'none'
#         video_string = ', '.join(tweet['videos']) if tweet['videos'] else 'none'
#         data = [ tweet['link'],  tweet['text'],  
#                 tweet['date'], 
#                 pictures_string , video_string, 
#                 tweet['stats']['comments'], tweet['stats']['retweets'], tweet['stats']['quotes'], tweet['stats']['likes'] ]
#         data_list.append(data)
#     data_list_pd = pd.DataFrame(data_list, columns=['link', 'content', 'date&time', 'picture', 'video', 'comment', 'retweet', 'quote', 'like'])     
#     return data_list_pd

# #test
# temp = crawl_tweet('SpaceX', 10)
# temp.to_excel('news-aggregator\\recourse\\data\\tweets.xlsx')

#vẽ biểu đồ cột chồng với dữ liệu lấy từ reactionTweet_full, gộp lại theo từng bài với mốc là date
reactionTweet_full = pd.read_excel('news-aggregator\\recourse\\data\\tweets.xlsx')
column_items = pd.melt(reactionTweet_full, id_vars = 'date&time', 
               value_vars = ['like', 'retweet', 'comment', 'quote'],
               var_name = 'reaction_type',
               value_name = 'count'
              )
plt.figure(figsize=(20,15))
sns.barplot(x = 'date&time', y = 'count', hue = 'reaction_type', data = column_items)
plt.xlabel('Date&Time')
plt.ylabel('Count')
plt.title('Grouped Reaction Metrics Over Time')
plt.show()