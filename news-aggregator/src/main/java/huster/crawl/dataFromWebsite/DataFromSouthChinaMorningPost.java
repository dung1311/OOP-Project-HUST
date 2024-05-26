package huster.crawl.dataFromWebsite;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import huster.crawl.dataFormat.DataListFormat;
//this is base class extends DataListFormat
public class DataFromSouthChinaMorningPost extends DataListFormat{

    @Override
    public String getContent(Document doc) {
         String content = "";
        try {
            Elements contentElements = doc.select("[data-qa=Component-Component]");
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
            Element metaTag = doc.selectFirst("meta[name=keywords]");
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

    @Override
    public String getAuthor(Document doc) {
        String author = "";
        try {
            Element authorName = doc.selectFirst("meta[name=cXenseParse:author]");
            if(authorName == null) return "unknown";
            author = authorName.attr("content");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return author.replaceAll("�", "\'");
    }


}
