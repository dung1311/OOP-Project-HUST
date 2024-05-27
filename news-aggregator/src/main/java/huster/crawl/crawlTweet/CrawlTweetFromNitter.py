import requests
from bs4 import BeautifulSoup
import logging
from base64 import b64decode
from urllib.parse import unquote
import json

logging.basicConfig(
    level=logging.INFO, format="%(asctime)s - %(levelname)s - %(message)s"
)
DEFAULT_PICTURE = "news-aggregator\\resource\\assets\\minhchung.png"


class DataScraper:

    def __init__(self, user_name):
        base_url = "https://nitter.privacydev.net/" + user_name
        if base_url:
            self.base_url = base_url
        else:
            self.base_url = input("Enter the base URL: ")
        self.headers = {
            "User-Agent": "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.110 Safari/537.3"
        }

    def _get_page(self, url):
        try:
            response = requests.get(url, headers=self.headers, timeout=10)
            response.raise_for_status()
            return BeautifulSoup(response.text, "lxml")
        except requests.exceptions.RequestException as e:
            logging.error(f"Request failed: {e}")
            return None

    def _get_tweet_link(self, tweet):
        return (
            "https://twitter.com" + tweet.find("a")["href"] if tweet.find("a") else ""
        )

    def _get_tweet_content(self, tweet):
        tweet_content = tweet.find("div", class_="tweet-content media-body")

        if tweet_content:
            return tweet_content.text.strip().replace("\n", " ")
        else:
            return ""

    def _get_tweet_date(self, tweet):

        tweet_date_span = tweet.find("span", class_="tweet-date")

        if tweet_date_span:
            tweet_date = tweet_date_span.find("a")["title"]
            return tweet_date.split("/")[-1].split("#")[0]
        else:
            return ""

    def _get_author(self, tweet, is_encrypted):

        author = {}
        author["name"] = tweet.find("a", class_="fullname").text.strip()
        author["username"] = tweet.find("a", class_="username").text.strip()
        avatar_tag = tweet.find("img", class_="avatar")
        if avatar_tag:
            avatar_src = avatar_tag["src"]
            if is_encrypted:
                avatar = "https://pbs.twimg.com/" + b64decode(
                    avatar_src.split("/")[-1].encode("utf-8")
                ).decode("utf-8")
            else:
                avatar = "https://pbs.twimg.com" + unquote(avatar_src.split("/pic")[1])
            author["avatar"] = avatar

        return author

    def _get_tweet_media(self, tweet, is_encrypted):
        pictures, videos, gifs = [], [], []
        if tweet.find("div", class_="tweet-body").find(
            "div", class_="attachments", recursive=False
        ):
            if is_encrypted:
                pictures = [
                    "https://pbs.twimg.com/"
                    + b64decode(img["src"].split("/")[-1].encode("utf-8"))
                    .decode("utf-8")
                    .split("?")[0]
                    for img in tweet.find("div", class_="tweet-body")
                    .find("div", class_="attachments", recursive=False)
                    .find_all("img")
                ]
                videos = [
                    (
                        b64decode(
                            video["data-url"].split("/")[-1].encode("utf-8")
                        ).decode("utf-8")
                        if "data-url" in video.attrs
                        else video.find("source")["src"]
                    )
                    for video in tweet.find("div", class_="tweet-body")
                    .find("div", class_="attachments", recursive=False)
                    .find_all("video", class_="")
                ]
                gifs = [
                    "https://"
                    + b64decode(
                        gif.source["src"].split("/")[-1].encode("utf-8")
                    ).decode("utf-8")
                    for gif in tweet.find("div", class_="tweet-body")
                    .find("div", class_="attachments", recursive=False)
                    .find_all("video", class_="gif")
                ]
            else:
                pictures = [
                    "https://pbs.twimg.com"
                    + unquote(img["src"].split("/pic")[1]).split("?")[0]
                    for img in tweet.find("div", class_="tweet-body")
                    .find("div", class_="attachments", recursive=False)
                    .find_all("img")
                ]
                videos = [
                    (
                        unquote("https" + video["data-url"].split("https")[1])
                        if "data-url" in video.attrs
                        else video.find("source")["src"]
                    )
                    for video in tweet.find("div", class_="tweet-body")
                    .find("div", class_="attachments", recursive=False)
                    .find_all("video", class_="")
                ]
                gifs = [
                    unquote("https://" + gif.source["src"].split("/pic/")[1])
                    for gif in tweet.find("div", class_="tweet-body")
                    .find("div", class_="attachments", recursive=False)
                    .find_all("video", class_="gif")
                ]

        pictures = ", ".join(pictures) if pictures else DEFAULT_PICTURE
        videos = ", ".join(videos)
        gifs = ", ".join(gifs)

        return pictures, videos, gifs

    def get_tweet_reaction(self, tweet):
        stats = ["comments", "retweets", "quotes", "likes"]
        tweet_stats = {}

        stat_spans = tweet.find_all("span", class_="tweet-stat")

        if len(stat_spans) < len(stats):
            logging.warning("Some tweet stats are missing, filling with zeros.")
            for stat in stats:
                tweet_stats[stat] = 0
        else:
            for i, stat in enumerate(stats):
                try:
                    stat_value = stat_spans[i].find("div").text.strip().replace(",", "")
                    if stat_value.isdigit():
                        tweet_stats[stat] = int(stat_value)
                    else:
                        logging.error(
                            f"Invalid value for {stat}: {stat_value}, setting to zero."
                        )
                        tweet_stats[stat] = 0
                except (AttributeError, ValueError, IndexError) as e:
                    logging.error(f"Error extracting {stat}: {e}, setting to zero.")
                    tweet_stats[stat] = 0

        return tweet_stats

    def get_data(self, path="/", is_encrypted=False):
        data = []
        full_url = self.base_url + path
        soup = self._get_page(full_url)

        if not soup:
            logging.error(f"Failed to fetch data from {full_url}")
            return data

        tweets = soup.find_all("div", class_="timeline-item")

        if not tweets:
            logging.warning(f"No data found on page {full_url}")
            return data

        logging.info(f"Fetched {len(tweets)} items from {full_url}")

        for tweet in tweets:
            tweet_link = self._get_tweet_link(tweet)
            tweet_text = self._get_tweet_content(tweet)
            tweet_date = self._get_tweet_date(tweet)
            tweet_user = self._get_author(tweet, is_encrypted)
            tweet_media = self._get_tweet_media(tweet, is_encrypted)

            tweet_stats = self.get_tweet_reaction(tweet)

            tweet_stats["url"] = "https://x.com/" + tweet_user["name"]
            tweet_stats["link"] = tweet_link
            tweet_stats["content"] = tweet_text
            tweet_stats["summary"] = "null"
            tweet_stats["title"] = tweet_user["username"]
            tweet_stats["datetimeCreation"] = tweet_date
            tweet_stats["author"] = tweet_user["name"]
            tweet_stats["authorID"] = tweet_user["username"]
            tweet_stats["authorAvatar"] = tweet_user["avatar"]
            tweet_stats["linkImage"] = tweet_media[0]
            tweet_stats["video"] = tweet_media[1]
            tweet_stats["gif"] = tweet_media[2]

            data.append(tweet_stats)

        return data


def main(user_name):
    scraper = DataScraper(user_name)
    data = scraper.get_data()

    with open(
        f"news-aggregator\\resource\\data\\tweetData\\{user_name}.json", "w"
    ) as file:
        json.dump(data, file, indent=4)
