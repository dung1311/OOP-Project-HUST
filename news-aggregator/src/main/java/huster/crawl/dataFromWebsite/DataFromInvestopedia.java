package huster.crawl.dataFromWebsite;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import huster.crawl.DataFormat.DataListFormat;
//this is base class extends DataListFormat
public class DataFromInvestopedia extends DataListFormat{

    @Override
    public String getSummary(Document doc) {
        String summary = "KEY TAKEAWAYS\n";
        try {
            Element summaryTitle = doc.selectFirst("meta[property=og:description]");
            if(summaryTitle == null) return "unknown";
            summary += summaryTitle.attr("content") + "\n";
            Elements summaryBody = doc.select("div[class=comp mntl-sc-block-callout-body mntl-text-block]");
            for(Element summaryBodyContent : summaryBody) {
                summary += summaryBodyContent.text() + "\n" ;
            }
        } catch(Exception e) {
            e.printStackTrace();
        }   
        return summary.replaceAll("�", "\'");
    }

    @Override
    public String getContent(Document doc) {
        String content = "";
        try {
            Element contentElementBody = doc.selectFirst("div[class=comp mntl-sc-page mntl-block article-body-content]");
            for(int i = 0; i < 100; i++) {
                String numberOfContentClass = String.valueOf(i);
                Element contentHeading = contentElementBody.selectFirst("h2[id=mntl-sc-block_"+ numberOfContentClass +"-0]");
                if(contentHeading != null) {
                    content += contentHeading.text().replaceAll("�", "\'") + "\n\n";
                }
                else {
                    Element contentBody = contentElementBody.selectFirst("p[id=mntl-sc-block_"+ numberOfContentClass +"-0]");
                    if(contentBody == null) 
                        continue;
                    content += contentBody.text().replaceAll("�", "\'");
                    if(content.charAt(content.length()-1) == '.') content += "\n\n";
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return content.replaceAll("�", "\'");
    }

    @Override
    public String getCategory(Document doc) {
        String category = null;
        try {
            Element categoryElement = doc.selectFirst("meta[name=parsely-section]");
            if(categoryElement == null) return "unknown";
            category = categoryElement.attr("content");
        } catch(Exception e) {
            e.printStackTrace();
        }   
        if(category == null) return "unknown";
        return category.replaceAll("�", "\'");
    }

    @Override
    public String getDateTimeCreation(Document doc)
    {
        String dateTimeCreation = "";
        try {
            Element dateTimeElement = doc.selectFirst(".mntl-attribution__item-date");
            if(dateTimeElement == null) return "unknown";
            String[] parts = dateTimeElement.text().split(" ");
            for(int i = 1; i < parts.length; i++) {
                dateTimeCreation += parts[i] + " ";
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return dateTimeCreation.replaceAll("�", "\'");
    }

    @Override
    public List<String> getTag(Document doc) {
        List<String> tag = new ArrayList<>();
        String tagString = "";
        try {
            Element metaTag = doc.selectFirst("meta[name=parsely-tags]");
            if(metaTag == null) {
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
            if(!tagName.equals("#"))
                tag.add(tagName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tag;
    }

    @Override
    public String getAuthor(Document doc) {
        String author = "";
        try {
            Element authorName = doc.selectFirst("meta[name=sailthru.author]");
            if(authorName == null) return "unknown";
            author = authorName.attr("content");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return author.replaceAll("�", "\'");
    }
}
