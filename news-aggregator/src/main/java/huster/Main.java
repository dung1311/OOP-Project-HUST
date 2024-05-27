package huster;

import huster.crawl.crawlTweet.ServerClient;
import huster.crawl.dataFormat.TotalData;

import huster.gui.Menu;
import huster.gui.MenuHistory;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.atomic.AtomicBoolean;

import javax.swing.JOptionPane;

public class Main {
    private static final String TEST_URL = "http://www.google.com";
    private static final int CHECK_INTERVAL = 5000;
    private static AtomicBoolean isNetworkAvailable = new AtomicBoolean(true); 

    public static void main(String[] args) {

        Thread networkCheckThread = new Thread(new NetworkChecker());
        networkCheckThread.setDaemon(true);
        networkCheckThread.start();

        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                MyRunnable myRunnable = new MyRunnable();

                Thread crawlThread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        myRunnable.crawl();
                    }
                });

                // crawlThread.start();

                // check status before call crawlThread.join
                while (true) {
                    if (!isNetworkAvailable.get()) {
                        JOptionPane.showMessageDialog(null, "Error: No internet connection!", "Error", JOptionPane.ERROR_MESSAGE);
                        System.exit(1);
                    }
                    try {
                        crawlThread.join(1000); // check internet connection status every 1000ms
                        if (!crawlThread.isAlive()) {
                            break; 
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

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
        });
    }

    public static boolean isInternetAvailable(String url) {
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

    static class NetworkChecker implements Runnable {
        @Override
        public void run() {
            while (true) {
                if (!isInternetAvailable(TEST_URL)) {
                    isNetworkAvailable.set(false); // Update internet status
                    JOptionPane.showMessageDialog(null, "Error: No internet connection!", "Error", JOptionPane.ERROR_MESSAGE);
                    ServerClient.shutDownServer();
                    System.exit(1);
                } else {
                    isNetworkAvailable.set(true); // Update internet status
                }
                try {
                    Thread.sleep(CHECK_INTERVAL);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
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