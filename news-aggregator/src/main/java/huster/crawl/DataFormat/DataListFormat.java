package huster.crawl.DataFormat;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


public class DataListFormat {
    protected String url; //This string is the path to the source page, where blockchain-related information is found on that website
    protected String innerLinkClass; //This string is a tag in html of source page that contains attributes related to the links of the articles or the blogs, it is used by select() or getElementsByClass() method
    protected String innerLinkAttr; //This string is a attributes which you can get links, it is use by Element.attr() or Elements.attr() method
    protected String link; //this link is the path of article or blog

    public DataListFormat() {
    }

    public DataListFormat(String url, String innerLinkClass, String innerLinkAttr) {
        this.url = url;
        this.innerLinkClass = innerLinkClass;
        this.innerLinkAttr = innerLinkAttr;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        String[] parts = url.split("/");
        this.url = parts[0] + "//" + parts[1] + parts[2] + "/";
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

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
    /*Due to the variation in HTML tags used by different websites to display information, 
    overriding the method is necessary to extract this information effectively. But some method is still right without override like
    getTitle, getType, getSummary, getCategory, getDatetimeCreation, getAuthor, getLinkImage */

    //This method returns some link, extracted by jsoup from source page (url)
    public List<String> getLinks(String url, String innerLinkClass, String innerLinkAttr){
        try{
            Document doc = Jsoup.connect(url).ignoreHttpErrors(true).get();
            Elements linkElementsGetByClass = doc.getElementsByClass(innerLinkClass);
            Elements linkElementsSelect = doc.select(innerLinkClass);
            Set<String> tempLinks = new HashSet<>();
            String[] partUrls = url.split("/");
            String urlSource = partUrls[0] + "//" + partUrls[1] + partUrls[2];
            if(linkElementsGetByClass != null) {
                for(Element link : linkElementsGetByClass){
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
                for(Element link : linkElementsSelect){
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

    //This method extracts the title of an article from a Document linked to a URL
    public String getTitle(Document doc) {
        String title = null;
        try {
            Element titleElement = doc.selectFirst("meta[property=og:title]");
            if(titleElement == null) return "unknown";
            title = titleElement.attr("content") + "\n\n";
        } catch(Exception e) {
            e.printStackTrace();
        }   
        return title.replaceAll("�", "\'");
    }

    //This method extracts the summary of an article from a Document linked to a URL
    public String getSummary(Document doc)
    {
        String summary = "KEY TAKEAWAYS" + "\n\n";
        try {
            Element summaryTitle = doc.selectFirst("meta[property=og:description]");
            if(summaryTitle == null) return "unknown";
            summary += summaryTitle.attr("content") + "\n\n";
        } catch(Exception e) {
            e.printStackTrace();
        }   
        return summary.replaceAll("�", "\'");
    }

    //This method extracts the type of an article from a Document linked to a URL
    public String getType(Document doc) {
        String type = null;
        try {
            Element typeElement = doc.selectFirst("meta[property=og:type]");
            if(typeElement == null) return "unknown";
            type = typeElement.attr("content");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return type.replaceAll("�", "\'");
    }

    //This method must be overrode; After that, it extracts the content of an article from a Document linked to a URL 
    public String getContent(Document doc) {
        return "unknown";
    }

    //This method extracts the title of an article from a Document linked to a URL
    public String getCategory(Document doc) {
        String category = null;
        try {
            Element categoryElement = doc.selectFirst("meta[property=article:section]");
            if(categoryElement == null) return "unknown";
            category = categoryElement.attr("content");
        } catch(Exception e) {
            e.printStackTrace();
        }   
        return category.replaceAll("�", "\'");
    }

    //This method extracts the dateCreation of an article from a Document linked to a URL.
    public String getDateTimeCreation(Document doc)
    {
        String dateTimeCreation = null;
        try {
            Element dateTimeElement = doc.selectFirst("meta[property=article:published_time]");
            if(dateTimeElement == null) return "unknown";
            dateTimeCreation = dateTimeElement.attr("content");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dateTimeCreation.replaceAll("�", "\'");
    }

    //This method must be overrode; After that, it extracts the list of tag of an article from a Document linked to a URL 
    public List<String> getTag(Document doc) {
        return null;
    }
    
    //This method extracts the author of an article from a Document linked to a URL.
    public String getAuthor(Document doc) {
        String author = "";
        try {
            Element authorName = doc.selectFirst("meta[property=article:author]");
            if(authorName == null) return "unknown";
            author = authorName.attr("content");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return author.replaceAll("�", "\'");
    }

    //This method extracts the link image of an article from a Document linked to a URL.
    public String getLinkImage(Document doc)
    {
        String linkImage = "";
        try {
            Element linkImageElement = doc.selectFirst("meta[property=og:image]");
            if(linkImageElement == null) return "unknown";
            linkImage = linkImageElement.attr("content");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return linkImage.replaceAll("�", "\'");
    }

    //This method returns a list of elements with data type Data. It is fully inherited
    public List<Data> getDataList(DataListFormat itemLink,String url, String innerLinkClass, String innerLinkAttr) {
        try {
            List<Data> dataList = new ArrayList<>();
            List<String> linkList = itemLink.getLinks(url,innerLinkClass,innerLinkAttr);
            for(int i = 0; i < linkList.size(); i++)
            {
                Data item = new Data();
                itemLink.setLink(linkList.get(i));
                Document doc = Jsoup.connect(itemLink.getLink()).ignoreHttpErrors(true).get();
                item.setData(itemLink.getLink(), itemLink.getLink(), itemLink.getTitle(doc), itemLink.getType(doc), itemLink.getSummary(doc), itemLink.getContent(doc), itemLink.getCategory(doc), itemLink.getDateTimeCreation(doc), itemLink.getTag(doc), itemLink.getAuthor(doc),itemLink.getLinkImage(doc));
                if(item.isDataFormat(item))   
                    dataList.add(item);                 
            }
            if(dataList.isEmpty()) return null;
            else return dataList;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}


