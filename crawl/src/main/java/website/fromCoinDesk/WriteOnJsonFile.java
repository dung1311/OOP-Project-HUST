package website.fromCoinDesk;

import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import org.apache.commons.text.StringEscapeUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class WriteOnJsonFile {
    public void writeOnJsonFile(String url)
    {
        try {
            List<Data> dataList = new ArrayList<>();
            Resources source = new Resources();
            List<String> linkList = source.getLinks(url);
            for(int i = 0; i < linkList.size(); i++)
            {
                Data item = new Data();
                Link itemLink = new Link();
                itemLink.setLink(linkList.get(i));
                item.setUrl(url);
                item.setLink(itemLink.getLink());
                item.setTitle(itemLink.getTitle(itemLink.getLink()));
                item.setType(itemLink.getType(itemLink.getLink()));
                item.setSummary(itemLink.getSummary(itemLink.getLink()));
                item.setContent(itemLink.getContent(itemLink.getLink()));
                item.setCategory(itemLink.getCategory(itemLink.getLink()));
                item.setDatetimeCreation(itemLink.getDateTimeCreation(itemLink.getLink()));
                item.setTag(itemLink.getTag(itemLink.getLink()));
                item.setAuthor(itemLink.getAuthor(itemLink.getLink()));
                item.setLinkImage(itemLink.getLinkImage(itemLink.getLink()));
                dataList.add(item);
            }

            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            String json = gson.toJson(dataList);
            String unicodeJSON = StringEscapeUtils.unescapeJava(json);

            Scanner sc = new Scanner(System.in);
            String fileName = sc.next();
            sc.close();

            FileWriter fileWriter = new FileWriter("D:/Bear/Project/OOP-Project-HUST/crawl/src/main/java/website/" + fileName +".json");
            fileWriter.write(unicodeJSON);
            fileWriter.close();

            System.out.println("Write on JsonFile successful");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
