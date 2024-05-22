package huster;

import java.io.IOException;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import huster.crawl.crawlWithThread.Crawl;
import huster.crawl.crawlWithThread.TotalData;

public class Main {
    public static void main(String[] args) throws IOException{
        long startTime = System.currentTimeMillis(); 
        new Crawl().crawl();
        long endTime = System.currentTimeMillis();
        long runTime = endTime - startTime;
        System.out.println("Running Time: " + runTime); 

    }
}