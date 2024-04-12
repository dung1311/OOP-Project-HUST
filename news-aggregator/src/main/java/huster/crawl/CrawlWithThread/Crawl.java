package huster.crawl.CrawlWithThread;

import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import huster.crawl.DataFormat.Data;

public class Crawl {
    private List<Data> dataList = new ArrayList<>();
    public Crawl() {};

    public Crawl(List<Data> dataList)
    {
        this.dataList = dataList;
    }

    public List<Data> getDataList() {
        return dataList;
    }

    public void setDataList(List<Data> dataList) {
        this.dataList = dataList;
    }

    public void crawl()
    {
        try{
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            TotalData dataList = new TotalData();
            dataList.setDataList();
            String json = gson.toJson(dataList.getDataList());
    
            FileWriter fileWriter = new FileWriter("news-aggregator/resource/data/totalData.json");
            fileWriter.write(json);
            fileWriter.close();
            System.out.println("Successful!!");
        } catch(Exception e) {
            e.printStackTrace();
        }
        
    }
    
}
