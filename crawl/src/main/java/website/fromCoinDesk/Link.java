package website.fromCoinDesk;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Link {
    private String link;

    public Link() {

    }

    public Link(String link) {
        this.link = link;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
    
    public String getTitle(String link)
    {
        String title = null;
        try {
            Document doc = Jsoup.connect(link).get();
            Element titleElement = doc.selectFirst("meta[property=og:title]");
            title = titleElement.attr("content");
        } catch(Exception e) {
            e.printStackTrace();
        }   
        return title;
    }

    public String getSummary(String link)
    {
        String summary = null;
        try {
            Document doc = Jsoup.connect(link).get();
            Element summaryTitle = doc.selectFirst("meta[property=og:description]");
            Elements summaryElements = doc.getElementsByClass("typography__StyledTypography-sc-owin6q-0 eycWal");
            summary = summaryTitle.attr("content") + "\n";
            for(Element summaryElement : summaryElements)
            {
                summary = summary + summaryElement.text() + "\n";
            }
        } catch(Exception e) {
            e.printStackTrace();
        }   
        return summary;
    }

    public String getType(String link)
    {
        String type = null;
        try {
            Document doc = Jsoup.connect(link).get();
            Element typeElement = doc.selectFirst("meta[property=article:section]");
            if(typeElement == null) return "unknownType";
            type = typeElement.attr("content");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return type;
    }

    public String getContent(String link)
    {
        String content = "";
        try {
            Document doc = Jsoup.connect(link).get();
            Elements contentElements = doc.getElementsByClass("typography__StyledTypography-sc-owin6q-0 eycWal at-text");
            for(Element contentElement : contentElements) 
            {
                content = content + contentElement.text() + "\n";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return content;
    } 

    public String getCategory(String link)
    {
        String category = null;
        try {
            Document doc = Jsoup.connect(link).get();
            Element categoryElement = doc.selectFirst("meta[property=og:type]");
            category = categoryElement.attr("content");
        } catch(Exception e) {
            e.printStackTrace();
        }   
        return category;
    }

    public String getDateTimeCreation(String link)
    {
        String dateTimeCreation = null;
        try {
            Document doc = Jsoup.connect(link).get();
            Element dateTimeElement = doc.selectFirst("meta[property=article:published_time]");
            if(dateTimeElement == null) return "unknownDateTimeCreation";
            dateTimeCreation = dateTimeElement.attr("content");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dateTimeCreation;
    }

    public List<String> getTag(String link)
    {
        List<String> tag = new ArrayList<>();
        String tagString = "";
        try {
            Document doc = Jsoup.connect(link).get();
            Element metaTag = doc.selectFirst("meta[property=article:tag]");
            if(metaTag == null)
            {
                return tag;
            }
            tagString = metaTag.attr("content");
            String tagName = "#";
            for(int i = 0; i < tagString.length(); i++)
            {
                if(tagString.charAt(i) == ',' )
                {
                    tag.add(tagName);
                    tagName = "#";
                }
                else if(tagString.charAt(i) == ' ')
                {
                    continue;
                }
                else
                {
                    tagName = tagName + tagString.charAt(i);
                }
            }
            tag.add(tagName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tag;
    }
    
    public String getAuthor(String link)
    {
        String author = "";
        try {
            Document doc = Jsoup.connect(link).get();
            Element authorName = doc.selectFirst("meta[property=article:author]");
            if(authorName == null) return "unknownAuthor";
            author = authorName.attr("content");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return author;
    }

    public String getLinkImage(String link)
    {
        String linkImage = "";
        try {
            Document doc = Jsoup.connect(link).get();
            Element linkImageElement = doc.selectFirst("meta[property=og:image]");
            linkImage = linkImageElement.attr("content");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return linkImage;
    }

}
