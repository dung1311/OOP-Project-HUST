package huster.crawl.dataFromWebsite;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import huster.crawl.dataFormat.DataListFormat;

public class DataFromBlogChainLink extends DataListFormat {

    @Override
    public String getType(Document doc) {
        return "blog";
    }

    @Override
    public String getContent(Document doc) {
        String content = "";
        try {
            Elements contentElements = doc.select(".post-editor-content");
            if(contentElements == null) return "unknown";
            for(Element contentElement : contentElements) 
            {
                content = content + contentElement.text().replaceAll("�", "\'");
                if(content.charAt(content.length()-1) == '.') content += "\n\n" ;
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
            Elements categoryElement = doc.select("a[rel=category tag]");
            if(categoryElement == null || categoryElement.size() == 0) return "unknown";
            category = categoryElement.get(0).text();
        } catch(Exception e) {
            e.printStackTrace();
        }   
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
            Elements metaTag = doc.select("a[style=font-size: 10px;]");
            if(metaTag == null) {
                return tag;
            }
            else {
                for(Element tagCloud : metaTag) {
                    tagString += tagCloud.text() + ", ";
                }
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

    
}
