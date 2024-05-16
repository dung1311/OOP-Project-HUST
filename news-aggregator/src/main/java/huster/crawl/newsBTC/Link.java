package huster.crawl.newsBTC;

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

    public Document getDocument(String link)
    {
        try {
            Document doc = Jsoup.connect(link).get();
            return doc;
        } catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public String getTitle(Document doc)
    {
        String title = null;
        try {
            Element titleElement = doc.selectFirst("meta[property=og:title]");
            if(titleElement == null) return null;
            title = titleElement.attr("content");
        } catch(Exception e) {
            e.printStackTrace();
        }   
        return title;
    }

    public String getSummary(Document doc)
    {
        String summary = null;
        try {
            Element summaryTitle = doc.selectFirst("meta[property=og:description]");
            if(summaryTitle == null) return null;
            summary = summaryTitle.attr("content") + "\n";
        }catch(Exception e) {
            e.printStackTrace();
        }   
        return summary;
    }

    public String getType(Document doc)
    {
        String type = null;
        try {
            Element typeElement = doc.selectFirst("meta[property=article:section]");
            if(typeElement == null) return "The website do not provide type of article";
            type = typeElement.attr("content");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return type;
    }

    public String getContent(Document doc)
    {
        String content = "";
        try {
            Elements contentElements = doc.select(".content-inner");
            for(Element contentElement : contentElements) 
            {

                content = content + contentElement.text() + "\n" ;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return content;
    } 

    public String getCategory(Document doc)
    {
        String category = null;
        try {
            Element categoryElement = doc.selectFirst("meta[property=og:type]");
            if(categoryElement == null) return "unknownCategory";
            category = categoryElement.attr("content");
        } catch(Exception e) {
            e.printStackTrace();
        }   
        return category;
    }

    public String getDateTimeCreation(Document doc)
    {
        String dateTimeCreation = null;
        try {
            Element dateTimeElement = doc.selectFirst("meta[property=article:published_time]");
            if(dateTimeElement == null) return "unknownDateTimeCreation";
            dateTimeCreation = dateTimeElement.attr("content");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dateTimeCreation;
    }

    public List<String> getTag(Document doc)
    {
        List<String> tag = new ArrayList<>();
        String tagString = "";
        try {
            Element specialTag = doc.selectFirst("a[rel=category tag]");
            if(specialTag != null)
            {
                String[] partsSpecialTag = specialTag.text().split(" ");
                tagString += partsSpecialTag[0] + ", ";
            }    
            String[] parts = link.split("/");
            if(parts[3].equals("news") && parts[4].length() < 20)
            {
                tagString += parts[4].substring(0,1).toUpperCase() + parts[4].substring(1) + ", ";
            }
            else if(!parts[3].equals("news") && parts[4].length() == 3)
            {
                tagString += parts[4].toUpperCase() + ", ";
            }

            if(!parts[3].equals("news"))
            {
                parts[3].replace('-',' ');
                tagString += parts[3].substring(0,1).toUpperCase() + parts[3].substring(1) + ", ";
            }

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
            if(!tagName.equals("#"))
                tag.add(tagName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tag;
    }

    public String getAuthor(Document doc)
    {
        String author = "";
        try {
            Element authorName = doc.selectFirst("meta[name=author]");
            if(authorName == null) return "unknownAuthor";
            author = authorName.attr("content");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return author;
    }
    
    public String getLinkImage(Document doc)
    {
        String linkImage = "";
        try {
            Element linkImageElement = doc.selectFirst("meta[property=og:image]");
            if(linkImageElement == null) return null;
            linkImage = linkImageElement.attr("content");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return linkImage;
    }
}