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
public class DataFromCoinDesk extends DataListFormat {

    @Override
    public String getContent(Document doc) {
        String content = "";
        try {
            Elements contentElements = doc.getElementsByClass("typography__StyledTypography-sc-owin6q-0 eycWal at-text");
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

    @Override
    public List<Data> getDataList(String url, String innerLinkClass, String innerLinkAttr) {
        try {
            List<Data> dataList = new ArrayList<>();
            SourceFromCoinDesk source = new SourceFromCoinDesk();
            List<String> linkList = source.getLinks(url,innerLinkClass,innerLinkAttr);
            for(int i = 0; i < linkList.size(); i++)
            {
                Data item = new Data();
                DataFromCoinDesk itemLink = new DataFromCoinDesk();
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

