package huster;

//import java.io.FileWriter;



import huster.gui.Menu;
import huster.gui.ScreenHistory;
import java.io.IOException;

import huster.crawl.CrawlWithThread.Crawl;


public class Main {
    public static void main(String[] args) throws IOException{
        // long startTime = System.currentTimeMillis(); 
        // new Crawl().crawl();
        // long endTime = System.currentTimeMillis();
        // long runTime = endTime - startTime;
        // System.out.println("Running Time: " + runTime); 

        MyRunnable myRunnable = new MyRunnable();

        Thread displayThread = new Thread(new Runnable() {
            @Override
            public void run() {
                myRunnable.display();
            }
        });

        displayThread.start();
    }
}


class MyRunnable {
    public void crawl() {
        Crawl.crawl();
    }

    public void display() {
        Menu menu = new Menu();
        menu.setVisible(true);
    }
}