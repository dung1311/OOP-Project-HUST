package huster.crawl.dataFormat;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Source {
    protected String url;
    protected String innerLinkClass;
    protected String innerLinkAttr;

    public Source() {
    }

    public Source(String url, String innerLinkClass) {
        this.url = url;
        this.innerLinkClass = innerLinkClass;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getInnerLinkClass() {
        return innerLinkClass;
    }

    public void setInnerLinkClass(String innerLinkClass) {
        this.innerLinkClass = innerLinkClass;
    }

    public String getInnerLinkAttr() {
        return innerLinkAttr;
    }

    public void setInnerLinkAttr(String innerLinkAttr) {
        this.innerLinkAttr = innerLinkAttr;
    }

    public List<String> getLinks(String url, String innerLinkClass, String innerLinkAttr)
    {
        try{
            Document doc = Jsoup.connect(url).ignoreHttpErrors(true).get();
            Elements linkElementsGetByClass = doc.getElementsByClass(innerLinkClass);
            Elements linkElementsSelect = doc.select(innerLinkClass);
            Set<String> tempLinks = new HashSet<>();
            String[] partUrls = url.split("/");
            String urlSource = partUrls[0] + "//" + partUrls[1] + partUrls[2];
            if(linkElementsGetByClass != null) {
                for(Element link : linkElementsGetByClass)
                {
                    String linkNextPage = link.attr(innerLinkAttr);
                    if(linkNextPage.length() < 15) 
                        continue; 
                    if(linkNextPage.equals("")) 
                        continue;
                        String[] parts = linkNextPage.split("/");
                    if(parts[0].equals("https:") == false && parts[0].equals("http:") == false) {
                        linkNextPage = urlSource + linkNextPage;
                    }
                    tempLinks.add(linkNextPage);
                }
            }
            if (linkElementsSelect != null) {
                for(Element link : linkElementsSelect)
                {
                    String linkNextPage = link.attr(innerLinkAttr);
                    if(linkNextPage.length() < 15) 
                        continue;
                    if(linkNextPage.equals("")) 
                        continue;
                    String[] parts = linkNextPage.split("/");
                    if(parts[0].equals("https:") == false && parts[0].equals("http:") == false) {
                        linkNextPage = urlSource + linkNextPage;
                    }
                    tempLinks.add(linkNextPage);
                }
            }
            List<String> links = new ArrayList<>(tempLinks);
            return links;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

    
}

