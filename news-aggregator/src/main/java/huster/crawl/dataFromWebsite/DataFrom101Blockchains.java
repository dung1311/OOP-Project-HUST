package huster.crawl.dataFromWebsite;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import huster.crawl.dataFormat.DataListFormat;

public class DataFrom101Blockchains extends DataListFormat {

    @Override
    public String getType(Document doc) {
        return "blog";
    }

    @Override
    public String getContent(Document doc) {
        String content = "";
        try {
            Elements contentElements = doc.getElementsByClass("post-content-right");
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
    public String getCategory(Document doc) {
        String category = null;
        try {
            Element categoryElement = doc.selectFirst("a.blog-category-green");
            if(categoryElement == null) return "unknown";
            category = categoryElement.text();
        } catch(Exception e) {
            e.printStackTrace();
        }   
        if(category == null) return "unknown";
        return category.replaceAll("�", "\'");
    }

    @Override
    public String getAuthor(Document doc) {
        String author = "";
        try {
            Element authorName = doc.selectFirst("meta[name=twitter:data1]");
            if(authorName == null) return "unknown";
            author = authorName.attr("content");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return author.replaceAll("�", "\'");
    }

    @Override
    public List<String> getTag(Document doc) {
        List<String> tag = new ArrayList<>();
        String tagString = "";
        try {
            Elements metaTag = doc.select("a.blog-category-green");
            if(metaTag == null)
            {
                return tag;
            }
            tagString = metaTag.text();
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
