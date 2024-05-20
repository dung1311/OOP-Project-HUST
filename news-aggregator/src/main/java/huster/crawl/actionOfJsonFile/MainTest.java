package huster.crawl.actionOfJsonFile;

import com.google.gson.JsonArray;

public class MainTest {
    public static void main(String[] args) {
        try{
            ReadJsonFile reader = new ReadJsonFile();
            JsonArray jsonArray = reader.read("news-aggregator/resource/data/data.json");
            // Tag curTag = new Tag();
            // curTag.setCurrentTagList(jsonArray.get(1));
            // System.out.println(curTag.getCurrentTagList());
            // if(curTag.isContainTag("#meme")) System.out.println("haha");
            // else System.out.println("kho co");
            Tag.setTag(jsonArray);
            System.out.println(Tag.getTag());
        } catch(Exception e) {
            e.printStackTrace();

        }
    }
}
