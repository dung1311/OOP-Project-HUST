package NewsExample;


import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.jsoup.select.Elements;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
// import com.google.gson.JsonArray;
// import com.google.gson.JsonObject;


public class SaveToJSON {
    public void saveToJSON(String url )
    {
        try{
            List<DataItem> dataList = new ArrayList<>();
            DataItem topData = new DataItem();
            // Thêm nội dung tin tức mới nhất được đưa lên trang
            DocumentAndSelectTest topNew = new DocumentAndSelectTest();
            List<String> topNewList = new ArrayList<>(); 
            topNewList.addAll(topNew.getTopSearching(url));  
    
            topData.setDatetime(topNewList.get(0));
            topData.setTitle(topNewList.get(1));
            topData.setContent(topNewList.get(2));
            dataList.add(topData);
            //Lặp qua các phần tử trên trang web để trích xuất dữ liệu và thêm vào mảng dataList
            DocumentAndSelectTest news = new DocumentAndSelectTest();
            Elements datetime = news.getDatetime(url);
            Elements title = news.getTitle(url);
            Elements content = news.getContent(url);

            for (int i = 0; i < datetime.size(); i++) {
                DataItem item = new DataItem();
                item.setDatetime(datetime.get(i).text());
                item.setTitle(title.get(i).text());
                item.setContent(content.get(i).text());
                dataList.add(item);
            }
            
            // Tạo đối tượng Gson để chuyển đổi danh sách thành chuỗi JSON
            Gson gson = new GsonBuilder().setPrettyPrinting().create();

            String json = gson.toJson(dataList);


            // JsonArray dataListJsonArray = new Gson().fromJson(json, JsonArray.class);
            // for(int i = 0; i < 11; i++){
            //     JsonObject object = dataListJsonArray.get(i).getAsJsonObject();
            //     String repalceContent = object.get("content").getAsString();
            //     repalceContent = repalceContent.replaceAll("\u0027", "'");
            //     System.out.println(repalceContent);
            //}
            
            // Ghi vào file JSON
            Scanner input = new Scanner(System.in);
            String fileName = input.next();
            input.close();
            FileWriter fileWriter = new FileWriter("D:/Bear/jsoup/MyJavaProject/demo/src/main/java/NewsExample/" + 
           fileName +".json");
            fileWriter.write(json);
            fileWriter.close();
            
            System.out.println("Write-on or create JSON file successfull!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
