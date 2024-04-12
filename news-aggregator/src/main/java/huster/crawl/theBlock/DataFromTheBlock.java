package huster.crawl.theBlock;

import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import huster.crawl.DataFormat.Data;
public class DataFromTheBlock {
    public List<Data> writeOnJsonFile(String url)
    {
        try {
            List<Data> dataList = new ArrayList<>();
            Source source = new Source();
            List<String> linkList = source.getLinks(url);
            for(int i = 0; i < linkList.size(); i++)
            {
                Data item = new Data();
                Link itemLink = new Link();
                itemLink.setLink(linkList.get(i));
                Document doc = Jsoup.connect(itemLink.getLink()).ignoreHttpErrors(true).get();
                item.setUrl(itemLink.getLink());
                item.setLink(itemLink.getLink());
                item.setTitle(itemLink.getTitle(doc));
                item.setType(itemLink.getType(doc));
                if(item.getType().equals("unknownType")){
                    continue;
                }
                item.setSummary(itemLink.getSummary(doc));
                item.setContent(itemLink.getContent(doc));
                item.setCategory(itemLink.getCategory(doc));
                item.setDatetimeCreation(itemLink.getDateTimeCreation(doc));
                item.setTag(itemLink.getTag(doc));
                item.setAuthor(itemLink.getAuthor(doc));
                item.setLinkImage(itemLink.getLinkImage(doc));   
                dataList.add(item);                 
            }
            if(dataList.isEmpty()) return null;
            else return dataList;
            // Gson gson = new GsonBuilder().setPrettyPrinting().create();
            // String json = gson.toJson(dataList);
        
            // FileWriter fileWriter = new FileWriter("news-aggregator/resource/data/dataTheBlock.json");
            // if(json != null) fileWriter.write(json);
            // fileWriter.close();

            // System.out.println("Write on JsonFile successful");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}

