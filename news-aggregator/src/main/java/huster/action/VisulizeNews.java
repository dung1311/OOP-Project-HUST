package huster.action;

import java.io.BufferedReader;
import java.io.FileReader;

import com.google.gson.Gson;
import com.google.gson.JsonArray;

public class VisulizeNews {
    
    public static void display(){
        String dataFilePath = "news-aggregator\\resource\\data\\webData.json";

        JsonArray newsArray = new JsonArray();

        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(dataFilePath));

            newsArray = new Gson().fromJson(bufferedReader, JsonArray.class);

        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println(newsArray.size());
        // ham gi day
    }   

}
