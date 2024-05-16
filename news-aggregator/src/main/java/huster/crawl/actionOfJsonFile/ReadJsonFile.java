package huster.crawl.actionOfJsonFile;

import com.google.gson.Gson;
import com.google.gson.JsonArray;

import java.io.FileReader;

public class ReadJsonFile {
    
    public JsonArray read(String path) {
        try{
            FileReader reader = new FileReader(path);
            JsonArray jsonArray = new Gson().fromJson(reader, JsonArray.class);
            reader.close();
            return jsonArray;
        } catch(Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
