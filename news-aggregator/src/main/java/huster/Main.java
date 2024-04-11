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
        // Document doc = Jsoup.connect(test.get(6)).get();
        
        // System.out.println(link.getLinkImage(doc));
        // String url = "https://www.coindesk.com/";

        // DataFromTheBlock file = new huster.crawl.theBlock.DataFromTheBlock();
        // file.writeOnJsonFile(url);
        
        // long endTime = System.currentTimeMillis();
        // long runTime = endTime - startTime;
        // System.out.println("Running Time: " + runTime);     
        

        Menu menu = new Menu();
        ScreenHistory.getInstance().pushScreen(menu);
        menu.setVisible(true);
    }
}
