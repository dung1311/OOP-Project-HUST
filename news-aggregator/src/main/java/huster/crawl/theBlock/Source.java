package huster.crawl.theBlock;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
//import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Source {
    String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Source() {
    }

    public Source(String url) {
        this.url = url;
    }
    
    public List<String> getLinks(String url)
    {
        try {
            Document doc = Jsoup.connect(url).ignoreHttpErrors(true).get();
            Elements linkElements = doc.select("a");
            Set<String> tempLinks = new HashSet<>();

            for(int i = 0; i < linkElements.size(); i++)
            {
                String[] parts = linkElements.get(i).attr("href").split("/");
                if(parts.length < 2) continue;
                else if(parts[1].equals("post") || parts[1].equals("learn")) {
                    String linkNextPage = "https://www.theblock.co" + linkElements.get(i).attr("href");
                    tempLinks.add(linkNextPage);
                }
            }
            List<String> links = new ArrayList<>(tempLinks);

            return links;
        } catch(Exception e) {
            e.printStackTrace();
            return null;
        }
        

    }
}
