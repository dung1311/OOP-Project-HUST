package huster.crawl.newsBTC;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Source {
    private String url;

    public Source() {
    }

    public Source(String url) {
        this.url = url;
    }

    public List<String> getLinks()
    {
        try{
            Document doc = Jsoup.connect(url).ignoreHttpErrors(true).get();
            Elements linkElements = doc.getElementsByClass("block-article__link");
            Set<String> tempLinks = new HashSet<>();

            for(Element link : linkElements)
            {
                String linkNextPage = link.attr("href");
                if(linkNextPage.equals("")) continue;
                tempLinks.add(linkNextPage);
            }
            List<String> links = new ArrayList<>(tempLinks);
            return links;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }
    
    public List<String> getLinks(String url)
    {
        try{
            Document doc = Jsoup.connect(url).ignoreHttpErrors(true).get();
            Elements linkElements = doc.getElementsByClass("pageable-container");
            Set<String> tempLinks = new HashSet<>();

            for(Element link : linkElements)
            {
                String linkNextPage = link.attr("data-page");
                if(linkNextPage.equals("")) continue;
                tempLinks.add(linkNextPage);
            }
            List<String> links = new ArrayList<>(tempLinks);
            return links;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
