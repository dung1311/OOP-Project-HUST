package huster.crawl.dataFromWebsite;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import huster.crawl.dataFormat.Data;
import huster.crawl.dataFormat.DataListFormat;
import huster.crawl.dataFormat.Source;
public class DataFromCoinDesk extends DataListFormat {
    
    @Override
    public String getTitle(Document doc)
    {
        String title = null;
        try {
            Element titleElement = doc.selectFirst("meta[property=og:title]");
            if(titleElement == null) return "unknown";
            title = titleElement.attr("content");
        } catch(Exception e) {
            e.printStackTrace();
        }   
        return title.replaceAll("�", "\'");
    }

    @Override
    public String getSummary(Document doc)
    {
        String summary = null;
        try {
            Element summaryTitle = doc.selectFirst("meta[property=og:description]");
            Elements summaryElements = doc.getElementsByClass("typography__StyledTypography-sc-owin6q-0 eycWal");
            if(summaryTitle == null && summaryElements == null) return "unknown";
            summary = summaryTitle.attr("content") + "\n" + "\n";
            for(Element summaryElement : summaryElements)
            {
                summary = summary + summaryElement.text() + "\n" + "\n";
            }
        } catch(Exception e) {
            e.printStackTrace();
        }   
        return summary.replaceAll("�", "\'");
    }

    @Override
    public String getType(Document doc)
    {
        String type = null;
        try {
            Element typeElement = doc.selectFirst("meta[property=article:section]");
            if(typeElement == null) return "unknown";
            type = typeElement.attr("content");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return type.replaceAll("�", "\'");
    }

    @Override
    public String getContent(Document doc)
    {
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
    public String getCategory(Document doc)
    {
        String category = null;
        try {
            Element categoryElement = doc.selectFirst("meta[property=og:type]");
            if(categoryElement == null) return "unknown";
            category = categoryElement.attr("content");
        } catch(Exception e) {
            e.printStackTrace();
        }   
        return category.replaceAll("�", "\'");
    }

    @Override
    public String getDateTimeCreation(Document doc)
    {
        String dateTimeCreation = null;
        try {
            Element dateTimeElement = doc.selectFirst("meta[property=article:published_time]");
            if(dateTimeElement == null) return "unknown";
            dateTimeCreation = dateTimeElement.attr("content");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dateTimeCreation.replaceAll("�", "\'");
    }

    @Override
    public List<String> getTag(Document doc)
    {
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
    public String getAuthor(Document doc)
    {
        String author = "";
        try {
            Element authorName = doc.selectFirst("meta[property=article:author]");
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
            Element linkImageElement = doc.selectFirst("meta[property=og:image]");
            if(linkImageElement == null) return "unknown";
            linkImage = linkImageElement.attr("content");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return linkImage.replaceAll("�", "\'");
    }

    @Override
    public List<Data> getDataList(String url, String innerLinkClass, String innerLinkAttr)
    {
        try {
            List<Data> dataList = new ArrayList<>();
            Source source = new Source();
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

