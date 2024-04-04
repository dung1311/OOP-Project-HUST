package huster;

// import java.io.FileReader;
// import java.util.HashMap;
// import java.util.Map;

// import com.google.gson.Gson;
// import com.google.gson.reflect.TypeToken;

// import huster.crawl.coinDesk.Data;
import huster.crawl.coinDesk.WriteOnJsonFile;
public class Main {
    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
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
        //     FileReader reader = new FileReader("news-aggregator/resource/data/data.json");
        //     Gson gson = new Gson();
        //     HashMap<String, Data> dataList = gson.fromJson(reader, new TypeToken<HashMap<String, Data>>(){}.getType());
        //     int sum = 0;
        //     for(Map.Entry<String, Data> entry : dataList.entrySet())
        //     {
        //         String key = entry.getKey();
        //         Data data = entry.getValue();
        //         System.out.println(key);
        //         System.out.println(data.getDatetimeCreation());
        //         sum++;
        //     }
        //     reader.close();
        //     System.out.println(sum);
        // } catch (Exception e) {
        //     e.printStackTrace();
        // }
        long endTime = System.currentTimeMillis();
        long runTime = endTime - startTime;
        System.out.println("Running Time: " + runTime); 
    }

        
        
}
