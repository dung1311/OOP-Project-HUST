package huster.crawl.coinDesk;

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
            Elements linkElements = doc.select("a.card-titlestyles__CardTitleWrapper-sc-1ptmy9y-0.junCw.card-title-link");
            Set<String> tempLinks = new HashSet<>();

            for(Element link : linkElements)
            {
                String linkNextPage = "https://www.coindesk.com" + link.attr("href");
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
            Elements linkElements = doc.select("a.card-titlestyles__CardTitleWrapper-sc-1ptmy9y-0.junCw.card-title-link");
            Set<String> tempLinks = new HashSet<>();

            for(Element link : linkElements)
            {
                String linkNextPage = "https://www.coindesk.com" + link.attr("href");
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
