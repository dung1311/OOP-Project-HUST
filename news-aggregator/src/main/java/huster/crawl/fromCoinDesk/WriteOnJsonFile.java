package huster.crawl.fromCoinDesk;

import java.io.FileReader;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.List;
// import org.apache.commons.text.StringEscapeUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

public class WriteOnJsonFile {
    public void writeOnJsonFile(String url)
    {
        try {
            FileReader reader = new FileReader("news-aggregator/resource/data/data.json");
            Gson gson = new Gson();
            HashMap<String,Data> dataList = gson.fromJson(reader, new TypeToken<HashMap<String, Data>>(){}.getType());
            if(dataList == null)
            {
                dataList = new HashMap<>();
            }
            Resources source = new Resources();
            List<String> linkList = source.getLinks(url);
            for(int i = 0; i < 11; i++)
            {
                Data item = new Data();
                Link itemLink = new Link();
                itemLink.setLink(linkList.get(i));
                Document doc = Jsoup.connect(itemLink.getLink()).get();
                item.setUrl(url);
                //item.setLink(itemLink.getLink());
                item.setTitle(itemLink.getTitle(doc));
                item.setType(itemLink.getType(doc));
                item.setSummary(itemLink.getSummary(doc));
                item.setContent(itemLink.getContent(doc));
                item.setCategory(itemLink.getCategory(doc));
                item.setDatetimeCreation(itemLink.getDateTimeCreation(doc));
                item.setTag(itemLink.getTag(doc));
                item.setAuthor(itemLink.getAuthor(doc));
                item.setLinkImage(itemLink.getLinkImage(doc));
                if(dataList.containsKey(itemLink.getLink())) 
                    break;
                else
                    dataList.put(itemLink.getLink(),item);
            }

            gson = new GsonBuilder().setPrettyPrinting().create();
            String json = gson.toJson(dataList);
            // String unicodeJSON = StringEscapeUtils.unescapeJava(json);

            // Scanner sc = new Scanner(System.in);
            // String fileName = sc.next();
            // sc.close();

            FileWriter fileWriter = new FileWriter("news-aggregator/resource/data/data.json",true);
            fileWriter.write(json);
            fileWriter.close();

            System.out.println("Write on JsonFile successful");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
