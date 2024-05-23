package huster;

import java.io.IOException;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import huster.crawl.crawlWithThread.Crawl;
import huster.crawl.dataFormat.Source;
import huster.crawl.dataFromWebsite.DataFromBlogChainLink;

public class Main {
    public static void main(String[] args) throws IOException{
        long startTime = System.currentTimeMillis(); 
        new Crawl().crawl();
        // Source source = new Source();
        // List<String> link = source.getLinks("https://blog.chain.link/", "post-card-href", "href");
        // for(String i : link) System.out.println(i);
        
        // DataFromBlogChainLink data = new DataFromBlogChainLink();
        // Document doc = Jsoup.connect("https://blog.chain.link/from-tcpip-to-ccip/").get();
        // System.out.println(data.getTag(doc));
        long endTime = System.currentTimeMillis();
        long runTime = endTime - startTime;
        System.out.println("Running Time: " + runTime); 
    }
}