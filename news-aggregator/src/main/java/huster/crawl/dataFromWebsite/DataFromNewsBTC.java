package huster.crawl.dataFromWebsite;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import huster.crawl.dataFormat.Data;
import huster.crawl.dataFormat.DataListFormat;
import huster.crawl.sourceFromWebSite.SourceFromNewsBTC;
public class DataFromNewsBTC extends DataListFormat{

    @Override
    public String getContent(Document doc)
    {
        String content = "";
        try {
            Elements contentElements = doc.select(".content-inner");
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
    public String getCategory(Document doc)
    {
        String type = null;
        try {
            String[] parts = this.getLink().split("/");
            if(parts.length < 4 ) return "unknown";
            type = parts[3];
        } catch (Exception e) {
            e.printStackTrace();
        }
        return type.replaceAll("�", "\'");
    }

    @Override
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
    
    @Override
    public String getAuthor(Document doc)
    {
        String author = "";
        try {
            Element authorName = doc.selectFirst("meta[name=author]");
            if(authorName == null) return "unknown";
            author = authorName.attr("content");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return author.replaceAll("�", "\'");
    }

    @Override
    public String getLinkImage(Document doc)
    {
        String linkImage = "";
        try {
            Element linkImageElement_1 = doc.selectFirst("meta[property=og:image]");
            Element linkImageElement_2 = doc.selectFirst("meta[name=twitter:image]");
            if(linkImageElement_1 == null && linkImageElement_2 == null) return "unknown";
            if(linkImageElement_1 != null) 
                linkImage = linkImageElement_1.attr("content");
            if(linkImageElement_2 != null)
                linkImage = linkImageElement_2.attr("content");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return linkImage.replaceAll("�", "\'");
    }

    @Override
    public List<Data> getDataList(String url, String innerLinkClass, String innerLinkAttr) {
        try {
            List<Data> dataList = new ArrayList<>();
            SourceFromNewsBTC source = new SourceFromNewsBTC();
            List<String> linkList = source.getLinks(url,innerLinkClass,innerLinkAttr);
            for(int i = 0; i < linkList.size(); i++)
            {
                Data item = new Data();
                DataFromNewsBTC itemLink = new DataFromNewsBTC();
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

