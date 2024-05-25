package huster.crawl.dataFromWebsite;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import huster.crawl.dataFormat.DataListFormat;
public class DataFromCoinDesk extends DataListFormat {

    @Override
    public String getSummary(Document doc)
    {
        String summary = "KEY TAKEAWAYS" + "\n\n";
        try {
            Element summaryTitle = doc.selectFirst("meta[property=og:description]");
            Elements summaryElements = doc.getElementsByClass("typography__StyledTypography-sc-owin6q-0 eycWal");
            if(summaryTitle == null) return "unknown";
            if(summaryElements.text().equals(null)) return "unknown";
            summary = summaryTitle.attr("content") + "\n\n";
            for(Element summaryElement : summaryElements)
            {
                summary = summary + summaryElement.text() + "\n\n";
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
            Elements contentElements = doc.getElementsByClass("typography__StyledTypography-sc-owin6q-0 eycWal at-text");
            if(contentElements == null) return "unknown";
            for(Element contentElement : contentElements) 
            {
                content = content + contentElement.text().replaceAll("�", "\'") + "\n\n";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return content.replaceAll("�", "\'");
    } 

    @Override
    public List<String> getTag(Document doc) {
        List<String> tag = new ArrayList<>();
        String tagString = "";
        try {
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
            if(!tagName.equals("#"))
                tag.add(tagName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tag;
    }


}

