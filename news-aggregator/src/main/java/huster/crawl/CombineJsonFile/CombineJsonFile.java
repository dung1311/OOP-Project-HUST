package huster.crawl.CombineJsonFile;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;

public class CombineJsonFile {
    public void combine()
    {
        try{
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            String json = "";
            File file = new File("news-aggregator/resource/data");
            if (file.isDirectory()) {
                for (File f : file.listFiles()) {
                    if(f.getName().equals("combine.json")) continue;
                    JsonArray dataFromCurrentFile = gson.fromJson(new FileReader("news-aggregator/resource/data/" + f.getName()), JsonArray.class);;
                    json += gson.toJson(dataFromCurrentFile);
                }
            } else {
                System.out.println("not a folder!");
            }
    
            FileWriter fileWriter = new FileWriter("news-aggregator/resource/data/combined.json");
            fileWriter.write(json);
            fileWriter.close();
        }catch(Exception e) {
            e.printStackTrace();
        }
        
    }
}
