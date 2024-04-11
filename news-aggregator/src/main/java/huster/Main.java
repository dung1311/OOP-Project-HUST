package huster;

//import java.io.FileWriter;

import huster.crawl.fromCoinDesk.WriteOnJsonFile;

import java.util.Stack;

import javax.swing.JFrame;

import huster.gui.Menu;


public class Main {
    public static void main(String[] args) {

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
    public void display() {
        Stack<JFrame> screenHistory = new Stack<>();
        Menu menu = new Menu(screenHistory);
        menu.setVisible(true);
    }

    public void crawl() {
        String url = "https://www.coindesk.com/";
        WriteOnJsonFile file = new WriteOnJsonFile();
        file.writeOnJsonFile(url);
    }
}
