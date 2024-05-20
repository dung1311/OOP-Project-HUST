package huster.crawl.DataFormat;

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
    private String innerLinkClass;

    public Source() {
    }

    public Source(String url, String innerLinkClass) {
        this.url = url;
        this.innerLinkClass = innerLinkClass;
    }

    public String getInnerLinkClass() {
        return innerLinkClass;
    }

    public void setInnerLinkClass(String innerLinkClass) {
        this.innerLinkClass = innerLinkClass;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<String> getLinks(String url, String innerLinkClass)
    {
        try{
            Document doc = Jsoup.connect(url).ignoreHttpErrors(true).get();
            Elements linkElements = doc.select(innerLinkClass);
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

