package huster;

import java.io.IOException;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import huster.crawl.crawlWithThread.Crawl;
import huster.crawl.crawlWithThread.TotalData;
import huster.crawl.dataFormat.Source;
import huster.crawl.dataFromWebsite.DataFrom101Blockchains;
import huster.crawl.sourceFromWebSite.SourceFrom101Blockchains;

public class Main {
    public static void main(String[] args) throws IOException{
        long startTime = System.currentTimeMillis(); 
        new Crawl().crawl();
        // SourceFrom101Blockchains source = new SourceFrom101Blockchains();
        // List<String> links = source.getLinks("https://101blockchains.com/blog/", "a[rel=bookmark]", "href");
        // for(String i : links) {
        //     System.out.println(i);
        //     Document doc = Jsoup.connect(i).get();
        //     DataFrom101Blockchains data = new DataFrom101Blockchains();
        //     data.setLink(i);
        //     Elements tag = doc.select("a.blog-category-green");
        //     System.out.println(tag.get(0).text());
        //     System.out.println(data.getCategory(doc));
        // }
        // DataFrom101Blockchains data = new DataFrom101Blockchains();
        // data.setLink("https://101blockchains.com/importance-of-ai-certification/");
        // Document doc = Jsoup.connect("https://101blockchains.com/future-of-crypto-in-the-next-5-years/").get();
        // Elements tag = doc.select("a.blog-category-green");
        // System.out.println(tag.get(0).text());
        // System.out.println(data.getAuthor(doc));
        long endTime = System.currentTimeMillis();
        long runTime = endTime - startTime;
        System.out.println("Running Time: " + runTime); 

    }
}