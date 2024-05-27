package huster;

import huster.crawl.crawlTweet.ServerClient;
import huster.crawl.dataFormat.TotalData;

//import java.io.FileWriter;

import huster.gui.Menu;
import huster.gui.MenuHistory;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {

        MyRunnable myRunnable = new MyRunnable();

        // Thread crawlThread = new Thread(new Runnable() {
        // @Override
        // public void run() {
        // myRunnable.crawl();
        // }
        // });

        // crawlThread.start();
        // //
        // crawlThread.join();
        
        Thread displayThread = new Thread(new Runnable() {
            @Override
            public void run() {
                myRunnable.display();
            }
        });

        displayThread.start();

        Thread runServer = new Thread(new Runnable() {
            @Override
            public void run() {
                myRunnable.runServer();
            }
        });

        // runServer.start();
    }
}

class MyRunnable {
    public void crawl() {
        new TotalData().crawl();
    }

    public void display() {
        Menu menu = new Menu();
        menu.setVisible(true);
        MenuHistory.getInstance().pushScreen(menu);
    }

    public void runServer() {
        ServerClient.runServer();
    }
}