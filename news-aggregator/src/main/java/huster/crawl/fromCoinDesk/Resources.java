package huster.crawl.fromCoinDesk;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Resources {
    private String url;
    public static int capacity = 0;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Resources() {
    }

    public Resources(String url) {
        this.url = url;
    }

    public List<String> getLinks(String url)
    {
        try{
            Document doc = Jsoup.connect(url).get();
            Elements linkElements = doc.select("a.card-titlestyles__CardTitleWrapper-sc-1ptmy9y-0.junCw.card-title-link");
            Set<String> tempLinks = new HashSet<>();

            for(Element link : linkElements)
            {
                String linkNextPage = "https://www.coindesk.com" + link.attr("href");
                tempLinks.add(linkNextPage);
                Resources.capacity ++;
            }
            List<String> links = new ArrayList<>(tempLinks);
            return links;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }
    
}
