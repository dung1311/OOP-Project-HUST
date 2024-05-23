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
import huster.crawl.dataFromWebsite.DataFromSouthChinaMorningPost;

public class Main {
    public static void main(String[] args) throws IOException{
        long startTime = System.currentTimeMillis(); 
        new Crawl().crawl();
        // Source source = new Source();
        // List<String> link = source.getLinks("https://www.scmp.com/topics/blockchain", "a.e1whfq0b2.css-8ug9pk.ef1hf1w0", "href");
        // for(String i : link) System.out.println(i);
        
        // // DataFromBlogChainLink data = new DataFromBlogChainLink();
        // DataFromSouthChinaMorningPost data = new DataFromSouthChinaMorningPost();
        // Document doc = Jsoup.connect("https://www.scmp.com/tech/tech-trends/article/3262919/elon-musk-deepfake-crypto-scam-highlights-risks-hong-kong-ai-related-fraud-rises?campaign=3262919&module=perpetual_scroll_0&pgtype=article").ignoreHttpErrors(true).get();
        // System.out.println(data.getLinkImage(doc));
        long endTime = System.currentTimeMillis();
        long runTime = endTime - startTime;
        System.out.println("Running Time: " + runTime); 
    }
}