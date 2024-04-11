package huster.crawl.coinDesk;

import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
// import org.apache.commons.text.StringEscapeUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class DataFromCoinDesk {
    public void writeOnJsonFile(String url)
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

            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            String json = gson.toJson(dataList);
            // String unicodeJSON = StringEscapeUtils.unescapeJava(json);

            // Scanner sc = new Scanner(System.in);
            // String fileName = sc.next();
            // sc.close();

            FileWriter fileWriter = new FileWriter("news-aggregator/resource/data/data.json");
            if(json != null) fileWriter.write(json);
            fileWriter.close();

            System.out.println("Write on JsonFile successful");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
