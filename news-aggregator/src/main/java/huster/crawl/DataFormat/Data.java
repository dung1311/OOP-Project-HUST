package huster.crawl.dataFormat;

import java.util.List;

public class Data {
    private String url;
    private String link;
    private String title;
    private String type;
    private String summary;
    private String content;
    private String category;
    private String datetimeCreation;
    private List<String> tag;
    private String author;
    private String linkImage;

    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        String[] parts = url.split("/");
        this.url = parts[0] + "//" + parts[1] + parts[2] + "/";
    }
    public String getLink() {
        return link;
    }
    public void setLink(String link) {
        if(isContain(link, '�') == false)
            this.link = link;
        else this.link = "unknown";
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getSummary() {
        return summary;
    }
    public void setSummary(String summary) {
        this.summary = summary;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public String getCategory() {
        return category;
    }
    public void setCategory(String category) {
        this.category = category;
    }
    public String getDatetimeCreation() {
        return datetimeCreation;
    }
    public void setDatetimeCreation(String datetimeCreation) {
        this.datetimeCreation = datetimeCreation;
    }
    public List<String> getTag() {
        return tag;
    }
    public void setTag(List<String> tag) {
        this.tag = tag;
    }
    public String getAuthor() {
        return author;
    }
    public void setAuthor(String author) {
        this.author = author;
    }
    public String getLinkImage() {
        return linkImage;
    }
    public void setLinkImage(String linkImage) {
        if(isContain(linkImage,'�') == false)
            this.linkImage = linkImage;
        else this.linkImage = "unknown";
    }

    public void setData(String url, String link, String title, String type, String summary, String content, String category, String datetimeCreation, List<String> tag, String author, String linkImage) {
        String[] parts = url.split("/");
        this.url = parts[0] + "//" + parts[1] + parts[2] + "/";
        this.link = link;
        this.title = title;
        this.type = type;
        this.summary = summary;
        this.content = content;
        this.category = category;
        this.datetimeCreation = datetimeCreation;
        this.tag = tag;
        this.author = author;
        this.linkImage = linkImage;
    }

    public boolean isContain(String s, char c) {
        if(s == null) return false;
        for(int i = 0; i < s.length(); i++)
        {
            if(s.charAt(i) == c) return true; 
        }
        return false;
    }

    public boolean isDataFormat(Data item) {
        if(item.getLink() == "unknown" || item.getAuthor() == "unknown" || item.getCategory() == "unknown" || item.getContent() == "unknown" || item.getDatetimeCreation() == "unknown" || item.getLink() == "unknown" || item.getLinkImage() == "unknown" || item.getSummary() == "unknown" || item.getTag() == null || item.getTitle() == "unknown" || item.getType() == "unknown") 
            return false;
        return true;
    }
}
