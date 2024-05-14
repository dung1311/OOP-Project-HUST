package huster.crawl;

import java.io.IOException;

public class Main {
    static String serverUrl = "http://localhost:5000";

    public static void main(String[] args) throws IOException {
        String fileJsonName = "test3";
        String filePicturesName = "test3";
        TweetItem temp = new TweetItem("elonmusk", 200);

        ServerClient serverClient = new ServerClient(serverUrl);
        CrawTweet crawTweet = new CrawTweet(serverClient);
        DrawChart drawChart = new DrawChart(serverClient);
        crawTweet.crawlTweet(temp, fileJsonName);
        drawChart.drawChart(fileJsonName, filePicturesName);
    }
}