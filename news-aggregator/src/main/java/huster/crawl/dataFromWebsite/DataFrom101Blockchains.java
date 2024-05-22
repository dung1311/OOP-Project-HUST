package huster.crawl.dataFromWebsite;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import huster.crawl.dataFormat.Data;
import huster.crawl.dataFormat.DataListFormat;
import huster.crawl.sourceFromWebSite.SourceFromCoinDesk;

public class DataFrom101Blockchains extends DataListFormat {

    @Override
    public String getSummary(Document doc)
    {
        String summary = null;
        try {
            Element summaryTitle = doc.selectFirst("meta[property=og:description]");
            if(summaryTitle == null) return "unknown";
            summary = summaryTitle.attr("content") + "\n" + "\n";
        } catch(Exception e) {
            e.printStackTrace();
        }   
        return summary.replaceAll("�", "\'");
    }

    @Override
    public String getType(Document doc) {
        return "blog";
    }

    @Override
    public String getContent(Document doc) {
        String content = "";
        try {
            Elements contentElements = doc.getElementsByClass("post-content description ");
            if(contentElements == null) return "unknown";
            for(Element contentElement : contentElements) 
            {
                content = content + contentElement.text().replaceAll("�", "\'") + "\n" + "\n";
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
            Elements categoryElement = doc.select("a.blog-category-green");
            if(categoryElement == null) return "unknown";
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

    @Override
    public List<Data> getDataList(String url, String innerLinkClass, String innerLinkAttr) {
        try {
            List<Data> dataList = new ArrayList<>();
            SourceFromCoinDesk source = new SourceFromCoinDesk();
            List<String> linkList = source.getLinks(url,innerLinkClass,innerLinkAttr);
            for(int i = 0; i < linkList.size(); i++)
            {
                Data item = new Data();
                DataFrom101Blockchains itemLink = new DataFrom101Blockchains();
                itemLink.setLink(linkList.get(i));
                Document doc = Jsoup.connect(itemLink.getLink()).ignoreHttpErrors(true).get();
                item.setData(itemLink.getLink(), itemLink.getLink(), itemLink.getTitle(doc), itemLink.getType(doc), itemLink.getSummary(doc), itemLink.getContent(doc), itemLink.getCategory(doc), itemLink.getDateTimeCreation(doc), itemLink.getTag(doc), itemLink.getAuthor(doc),itemLink.getLinkImage(doc));
                if(item.isDataFormat(item))   
                    dataList.add(item);                 
            }
            if(dataList.isEmpty()) return null;
            else return dataList;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
