package PricesExample;

import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.jsoup.select.Elements;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class SaveToJSON {
    public void saveToJSON(String url, int capacity)
    {
        try{
            List<Data> dataList = new ArrayList<>();

            UpdatePrice price = new UpdatePrice();
            Elements idOfCoin = price.getIdOfCoin(url);
            Elements nameOfCoin = price.getNameOfCoin(url);
            Elements currentPrice = price.getCurrentPrice(url);

            for(int i = 0; i < capacity; i++)
            {
                Data item = new Data();
                item.setIdOfCoin(idOfCoin.get(i).text());
                item.setNameOfCoin(nameOfCoin.get(i).text());
                item.setCurrentPrice(currentPrice.get(i).text());
                dataList.add(item);
            }

            Gson gson = new GsonBuilder().setPrettyPrinting().create();

            String json = gson.toJson(dataList);
            
            //Nhập tên file JSON
            Scanner input = new Scanner(System.in);
            String fileName = input.next();
            input.close();
            FileWriter fileWriter = new FileWriter("D:/Bear/Project/testGithub/demo/src/main/java/PricesExample/" + fileName +".json");
            fileWriter.write(json);
            fileWriter.close();

            System.out.println("Write on Json file Successfully");
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public void saveToJSON(String url)
    {
        try{
            List<Data> dataList = new ArrayList<>();

            UpdatePrice price = new UpdatePrice();
            Elements idOfCoin = price.getIdOfCoin(url);
            Elements nameOfCoin = price.getNameOfCoin(url);
            Elements currentPrice = price.getCurrentPrice(url);

            for(int i = 0; i < idOfCoin.size(); i++)
            {
                Data item = new Data();
                item.setIdOfCoin(idOfCoin.get(i).text());
                item.setNameOfCoin(nameOfCoin.get(i).text());
                item.setCurrentPrice(currentPrice.get(i).text());
                dataList.add(item);
            }

            Gson gson = new GsonBuilder().setPrettyPrinting().create();

            String json = gson.toJson(dataList);
            
            //Nhập tên file JSON
            Scanner input = new Scanner(System.in);
            String fileName = input.next();
            input.close();
            FileWriter fileWriter = new FileWriter("D:/Bear/Project/testGithub/demo/src/main/java/PricesExample/" + fileName +".json");
            fileWriter.write(json);
            fileWriter.close();

            System.out.println("Write on Json file Successfully");
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
