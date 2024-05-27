package huster;

import huster.crawl.crawlTweet.ServerClient;
import huster.crawl.dataFormat.TotalData;

//import java.io.FileWriter;

import huster.gui.Menu;
import huster.gui.MenuHistory;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.swing.JOptionPane;

public class Main {

    public static boolean isInternetAvailable(String url) throws IOException {
        try {
            HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
            connection.setRequestMethod("HEAD");
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);
            int responseCode = connection.getResponseCode();
            return (200 <= responseCode && responseCode <= 399);
        } catch (IOException e) {
            return false;
        }
    }

    public static void main(String[] args) throws IOException, InterruptedException {

        String url = "http://www.google.com";
        try {
            if (!isInternetAvailable(url)) {
                JOptionPane.showMessageDialog(null, "Error: Không có kết nối mạng", "Error", JOptionPane.ERROR_MESSAGE);
                System.exit(1);
            } else {

                MyRunnable myRunnable = new MyRunnable();

                // Thread crawlThread = new Thread(new Runnable() {
                // @Override
                // public void run() {
                // myRunnable.crawl();
                // }
                // });

                // crawlThread.start();

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

                runServer.start();
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error: Có lỗi xảy ra khi kiểm tra kết nối mạng", "Error",
                    JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
            System.exit(1);
        }

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