package huster.action;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class GetData {

    private List<JsonObject> newsElements = new ArrayList<>(10);
    private String dataFilePath = "news-aggregator\\resource\\data\\totalData.json";
    private List<newsObject> newsObjectsList = new ArrayList<>(10);
    // store news from database
    private JsonArray newsArray = new JsonArray();

    public List<JsonObject> getNewsElements() {
        return newsElements;
    }

    public JsonArray getNewsArray() {
        return newsArray;
    }

    public GetData() {
        init();
    }

    public List<newsObject> getNewsObjectList() {
        return newsObjectsList;
    }

    public void init() {
        JsonArray m_newsArray = new JsonArray();
        // read data and asgin to newsArray
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(dataFilePath));

            m_newsArray = new Gson().fromJson(bufferedReader, JsonArray.class);
            newsArray = m_newsArray;
        } catch (Exception e) {
            e.printStackTrace();
        }

        Set<Integer> randomSet = new HashSet<>();
        Random random = new Random();
        // get radom index from newsArray
        while (randomSet.size() < 32) {
            // random number from 0 to 100
            int randomNumber = random.nextInt(40);
            
                randomSet.add(randomNumber);
        }
        // add news Elements into list
        for (int i : randomSet) {
            newsElements.add(newsArray.get(i).getAsJsonObject());
            JsonObject jsonObject = newsArray.get(i).getAsJsonObject();
            newsObjectsList.add(new newsObject(jsonObject));
        }

    }

}
