package huster.crawl.dataFormat;

import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public abstract class DataListFormat {
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
            title = titleElement.attr("content");
        } catch(Exception e) {
            e.printStackTrace();
        }   
        return title.replaceAll("�", "\'");
    }

    public abstract String getSummary(Document doc) ;

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

    public abstract String getContent(Document doc) ;

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

    public abstract List<String> getTag(Document doc) ;
    
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

    public abstract List<Data> getDataList(String url, String innerLinkClass, String innerLinkAttr);
}


