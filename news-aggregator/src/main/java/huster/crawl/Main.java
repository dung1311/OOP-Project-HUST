package huster.crawl;

import java.io.IOException;

public class Main {
    static String serverUrl = "http://localhost:5000";

    public static void main(String[] args) throws IOException {
        String fileJsonName = "test1";
        String filePicturesName = "test1";
        TweetItem temp = new TweetItem("Bitcoin", 800);

        ServerClient serverClient = new ServerClient(serverUrl);
        TweetUltility crawTweet = new TweetUltility(serverClient);

        crawTweet.crawlTweet(temp, fileJsonName);
        crawTweet.drawChart(fileJsonName, filePicturesName);

        serverClient.shutDownServer();
    }
}