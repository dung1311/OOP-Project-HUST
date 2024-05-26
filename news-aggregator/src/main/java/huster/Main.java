package huster;

import huster.crawl.dataFormat.Data;

//import java.io.FileWriter;



import huster.gui.Menu;
import huster.gui.MenuHistory;

import java.io.IOException;



public class Main {
    public static void main(String[] args) throws IOException{
 
        
        MyRunnable myRunnable = new MyRunnable();
        Thread displayThread = new Thread(new Runnable() {
            @Override
            public void run() {
                myRunnable.display();
            }
        });

        displayThread.start();

        // Thread crawlThread = new Thread(new Runnable() {
        //     @Override
        //     public void run() {
        //         myRunnable.crawl();                
        //     }
        // });

        // crawlThread.start();
    }
}


class MyRunnable {
    public void crawl() {
        new Data().crawl();
    }

    public void display() {
        Menu menu = new Menu();
        menu.setVisible(true);
        MenuHistory.getInstance().pushScreen(menu);
    }
}