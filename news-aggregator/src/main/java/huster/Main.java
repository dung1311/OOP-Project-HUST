package huster;

import java.io.FileReader;
//import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import huster.crawl.fromCoinDesk.WriteOnJsonFile;

public class Main {
    public static void main(String[] args) {
        // Resources test1 = new Resources();
        // List<String> test =  test1.getLinks("https://www.coindesk.com/");
        // for(int i = 0; i < test.size(); i++)
        // {
        //     System.out.println(test.get(i));
        // }
        String url = "https://www.coindesk.com/";

        WriteOnJsonFile file = new WriteOnJsonFile();
        file.writeOnJsonFile(url);
        // try {
        //     FileReader reader = new FileReader("output.json");
        //     Gson gson = new Gson();
        //     HashMap<String,Data> dataList = gson.fromJson(reader, new TypeToken<HashMap<String, Data>>(){}.getType());
        //     int sum = 0;
        //     for(Map.Entry<String, Data> entry : dataList.entrySet())
        //     {
        //         String key = entry.getKey();
        //         Data data = entry.getValue();
        //         System.out.println(key);
        //         System.out.println(data.getLink());
        //         System.out.println(data.getType());
        //         sum++;
        //     }
        //     reader.close();
        //     System.out.println(sum);
        // } catch (IOException e) {
        //     e.printStackTrace();
        // }
        
    }

        
        
}
