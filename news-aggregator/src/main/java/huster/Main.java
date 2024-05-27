package huster;

import huster.crawl.crawlTweet.ServerClient;
import huster.crawl.dataFormat.TotalData;

import huster.gui.Menu;
import huster.gui.MenuHistory;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.atomic.AtomicBoolean;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

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
                        JOptionPane.showMessageDialog(null, "Error: Không có kết nối mạng", "Error",
                                JOptionPane.ERROR_MESSAGE);
                        System.exit(1);
                    }
                    try {
                        crawlThread.join(1000); // Thử join với thời gian ngắn để kiểm tra định kỳ trạng thái mạng
                        if (!crawlThread.isAlive()) {
                            break; // Thoát khỏi vòng lặp nếu crawlThread đã kết thúc
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                Thread displayThread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        myRunnable.display();
        
                        // Kiểm tra biến cờ và dừng luồng khi cần thiết
                        while (myRunnable.isRunning()) {
                            try {
                                Thread.sleep(1000); // hoặc thực hiện các công việc khác
                            } catch (InterruptedException e) {
                                Thread.currentThread().interrupt();
                            }
                        }
        
                        ServerClient.shutDownServer();
                        System.out.println("Luồng displayThread đã dừng.");
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
                    isNetworkAvailable.set(false); // Cập nhật trạng thái mạng
                    JOptionPane.showMessageDialog(null, "Error: Không có kết nối mạng", "Error",
                            JOptionPane.ERROR_MESSAGE);
                    System.exit(1);
                } else {
                    isNetworkAvailable.set(true); // Cập nhật trạng thái mạng
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

    private volatile boolean running = true;

    public void crawl() {
        new TotalData().crawl();
    }

    public void display() {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                Menu menu = new Menu();
                menu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                menu.setVisible(true);
                MenuHistory.getInstance().pushScreen(menu);
                ServerClient.runServer();

                // Thêm WindowListener để cập nhật biến cờ khi đóng JFrame
                menu.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosing(WindowEvent e) {
                        running = false;
                    }
                });
            }
        });
    }

    public void runServer() {
    ServerClient.shutDownServer();
    }

    public boolean isRunning() {
        return running;
    }
}