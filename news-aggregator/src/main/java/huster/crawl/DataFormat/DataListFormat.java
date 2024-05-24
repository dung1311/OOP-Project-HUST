package huster.crawl.dataFormat;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;


public class DataListFormat {
    protected String url;
    protected String innerLinkClass;
    protected String innerLinkAttr;
    protected String link;

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

    public Document getDocument(String link) {
        try {
            Document doc = Jsoup.connect(link).get();
            return doc;
        } catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

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

    public String getContent(Document doc) {
        return "unknown";
    }

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

    public List<String> getTag(Document doc) {
        return null;
    }
    
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

    public List<Data> getDataList(DataListFormat itemLink,String url, String innerLinkClass, String innerLinkAttr) {
        try {
            List<Data> dataList = new ArrayList<>();
            Source source = new Source();
            List<String> linkList = source.getLinks(url,innerLinkClass,innerLinkAttr);
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


