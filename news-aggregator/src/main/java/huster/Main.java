package huster;

import huster.gui.Menu;
import java.io.IOException;

import huster.crawl.CrawlWithThread.Crawl;
import huster.crawl.crawlTweet.ServerClient;
import huster.crawl.crawlTweet.TweetItem;
import huster.crawl.crawlTweet.TweetUltility;

public class Main {

    public static void main(String[] args) throws IOException {

        MyRunnable myRunnable = new MyRunnable();

        Thread displayThread = new Thread(new Runnable() {
            @Override
            public void run() {
                myRunnable.display();
            }
        });

        Thread statisticThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    myRunnable.statistics();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread crawlThread = new Thread(new Runnable() {
            @Override
            public void run() {
                myRunnable.crawl();
            }
        });

        displayThread.start();
        // statisticThread.start();
        // crawlThread.start();

    }
}

class MyRunnable {
    public void crawl() {
        // Crawl.crawl();
        String serverUrl = "http://localhost:5000";
        String fileJsonName = "test1";
        String filePicturesName = "test1";
        TweetItem temp = new TweetItem("Bitcoin", 800);
        ServerClient serverClient = new ServerClient(serverUrl);
        TweetUltility crawTweet = new TweetUltility(serverClient);
        crawTweet.crawlTweet(temp, fileJsonName);
        // crawTweet.drawChart(fileJsonName, filePicturesName);

    }

    public void display() {
        Menu menu = new Menu();
        menu.setVisible(true);
    }

    public void statistics() throws IOException {
        Thread runServThread = new Thread(new Runnable() {
            @Override
            public void run() {
                ServerClient.runServer();
            }
        });

        runServThread.start();
        // crawlThread.start();

        // serverClient.shutDownServer();
    }
}