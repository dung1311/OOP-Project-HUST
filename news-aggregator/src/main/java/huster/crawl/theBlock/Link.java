package huster.crawl.theBlock;


import java.util.ArrayList;
import java.util.Arrays;
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
            Elements summaryElements = doc.select("li[data-v-f87c67ca]");
            summary = summaryTitle.attr("content") + "\n";
            if(summaryElements == null) return summary;
            for(Element summaryElement : summaryElements)
            {
                if(summaryElement.equals(summaryTitle)) continue;
                else summary = summary + summaryElement.text() + "\n";
            }
        } catch(Exception e) {
            e.printStackTrace();
        }   
        return summary;
    }

    public String getType(Document doc)
    {
        String type = null;
        try {
            Element typeElement = doc.selectFirst("meta[property=article:section]");
            if(typeElement == null) return "unknownType";
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
            Elements contentElements = doc.select("span[data-v-f87c67ca]");
            for(Element contentElement : contentElements) 
            {
                if(contentElement.text().length() < 25) continue;
                content = content + contentElement.text() + "\n";
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
            if(categoryElement == null) return null;
            category = categoryElement.attr("content");
        } catch(Exception e) {
            e.printStackTrace();
        }   
        return category;
    }

    public String getDateTimeCreation(Document doc)

    {
        String dateTimeCreation = "";
        try {
            Elements dateTimeElement = doc.getElementsByClass("timestamp tbcoTimestamp");
            if(dateTimeElement == null) return "unknownDateTimeCreation";
            String tempDateTime = dateTimeElement.get(0).text();
            String[] parts = tempDateTime.split(" ");
            List<String> month = Arrays.asList("January","February","March","April","May","June","July","August","September","October","November","December");
            int i = -1;
            boolean check = false;
            while(!check)
            {
                i++;
                check = month.contains(parts[i]);
            }
            for(; i < parts.length; i++) {
                dateTimeCreation += parts[i] + " ";
            }

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
            Element metaKeywords = doc.selectFirst("meta[name=keywords]");
            Elements metaTag = doc.select("meta[property=article:tag]");
            if(metaKeywords == null && metaTag == null) return tag;
            tagString = metaKeywords.attr("content") + ", ";
    
            for(int i = 0; i < metaTag.size(); i++) {
                tagString += metaTag.get(i).attr("content") + ", ";
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
            if(tagName.equals("#") == false)
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
            Element authorName = doc.selectFirst("a[data-v-f87c67ca]");
            if(authorName == null) return "unknownAuthor";
            author = authorName.text();
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
