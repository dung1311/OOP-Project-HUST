package huster;

//import java.io.FileWriter;



import huster.gui.Menu;
import huster.gui.ScreenHistory;
import java.io.IOException;

import huster.crawl.CrawlWithThread.Crawl;


public class Main {
    public static void main(String[] args) throws IOException{
        long startTime = System.currentTimeMillis(); 
        new Crawl().crawl();
        long endTime = System.currentTimeMillis();
        long runTime = endTime - startTime;
        System.out.println("Running Time: " + runTime); 

    }
}
