package huster;

import huster.crawl.coinDesk.DataFromCoinDesk;
import huster.crawl.theBlock.DataFromTheBlock;
import huster.crawl.theBlock.Link;
import huster.crawl.theBlock.Source;

import java.io.IOException;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import huster.gui.Menu;
import huster.gui.ScreenHistory;

public class Main {
    public static void main(String[] args) throws IOException{
        // long startTime = System.currentTimeMillis();
        // String url = "https://www.theblock.co/";
        // Source test1 = new Source();
        // List<String> test =  test1.getLinks(url);
        // // for(String i : test) System.out.println(i);
        // Link link = new Link();
        // Document doc = Jsoup.connect("https://www.theblock.co/learn/287081/what-is-bitwises-bitb-spot-bitcoin-etf").get();
        
        // System.out.println(link.getTag(doc));

        // long endTime = System.currentTimeMillis();
        // long runTime = endTime - startTime;
        // System.out.println("Running Time: " + runTime);     
        

        // Menu menu = new Menu();
        // ScreenHistory.getInstance().pushScreen(menu);
        // menu.setVisible(true);

        MyRunnable myRunnable = new MyRunnable();

        Thread displayThread = new Thread(new Runnable() {
            @Override
            public void run() {
                myRunnable.display();
            }
        });
        displayThread.start();
        Thread crawlThread = new Thread(new Runnable() {
            @Override
            public void run() {
                myRunnable.crawl();
            }
        });
        crawlThread.start();      

    }
}


class MyRunnable {
    public void crawl() {
        DataFromCoinDesk file1 = new huster.crawl.coinDesk.DataFromCoinDesk();
        file1.writeOnJsonFile("https://www.coindesk.com/");

        DataFromTheBlock file = new huster.crawl.theBlock.DataFromTheBlock();
        file.writeOnJsonFile("https://www.theblock.co/");
    }

    public void display() {
        Menu menu = new Menu();
        ScreenHistory.getInstance().pushScreen(menu);
        menu.setVisible(true);
    }
}