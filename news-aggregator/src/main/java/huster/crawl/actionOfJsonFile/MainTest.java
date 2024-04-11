package huster.crawl.actionOfJsonFile;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class MainTest {
    public static void main(String[] args) {
        try{
            ReadJsonFile reader = new ReadJsonFile();
            JsonArray jsonArray = reader.read("news-aggregator/resource/data/data.json");
            Tag curTag = new Tag();
            curTag.setCurrentTagList(jsonArray.get(1));
            System.out.println(curTag.getCurrentTagList());
            if(curTag.isContainTag("#Tradin")) System.out.println("haha");
            else System.out.println("kho co");
            // Tag.setTag(jsonArray);
            // System.out.println(Tag.hashtag);
        } catch(Exception e) {
            e.printStackTrace();

        }
    }
}
