package huster.crawl.crawlTweet;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class TweetItem {
    private static String serverUrl = "http://127.0.0.1:5000";
    private static ServerClient serverClient = new ServerClient(serverUrl);
    private String name;
    private int amount;
    private JsonObject highestInteractionTweet;
    private JsonObject lowestInteractionTweet;

    public TweetItem(String name, int amount) {
        this.name = name;
        this.amount = amount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public JsonObject getHighestInteractionTweet() {
        return highestInteractionTweet;
    }

    public void setHighestInteractionTweet(JsonObject highestInteractionTweet) {
        this.highestInteractionTweet = highestInteractionTweet;
    }

    public JsonObject getLowestInteractionTweet() {
        return lowestInteractionTweet;
    }

    public void setLowestInteractionTweet(JsonObject lowestInteractionTweet) {
        this.lowestInteractionTweet = lowestInteractionTweet;
    }

    public static void replaceJsonFile(String fileJsonName) {
        String dataPath = "news-aggregator\\resource\\data\\" + fileJsonName + ".json";

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(dataPath))) {
            JsonArray jsonArray = new Gson().fromJson(bufferedReader, JsonArray.class);

            String modifiedJsonString = jsonArray.toString();
            modifiedJsonString = modifiedJsonString.replace("date", "PostingDate");
            modifiedJsonString = modifiedJsonString.replace("text", "Content");
            modifiedJsonString = modifiedJsonString.replace("link", "LinkTweet");

            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            jsonArray = new Gson().fromJson(modifiedJsonString, JsonArray.class);

            try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(dataPath))) {
                gson.toJson(jsonArray, bufferedWriter);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void crawlTweetFirst(String fileJsonName) throws IOException {

        JsonObject data = new JsonObject();
        data.addProperty("name", name);
        data.addProperty("mode", "user");
        data.addProperty("amount", amount);
        data.addProperty("file_name", fileJsonName);
        try {
            serverClient.sendRequestWithResponse("/crawl_tweet", data);
        } catch (Exception e) {
            e.printStackTrace();
        }
        File file = new File("news-aggregator/resource/data/" + fileJsonName + ".json");
        if (!file.exists()) {
            throw new IOException("File not created");
        }
    }

    public void jsonAnalyst(String fileJsonName) throws IOException {
        JsonObject data = new JsonObject();
        data.addProperty("file_json_name", fileJsonName);
        JsonObject responseData = serverClient.sendRequestWithResponse("/json_analyst", data);
        this.highestInteractionTweet = responseData.getAsJsonObject("highestInteractionTweet");
        this.lowestInteractionTweet = responseData.getAsJsonObject("lowestInteractionTweet");

    }

    @SuppressWarnings("deprecation")
    public void crawlTweet(String fileJsonName) {
        try {
            crawlTweetFirst(fileJsonName);
            boolean isEmptyArray = true;

            do {
                JsonParser parser = new JsonParser();
                JsonElement readJson = parser
                        .parse(new FileReader("news-aggregator\\resource\\data\\" + fileJsonName + ".json"));

                JsonArray jsonArray = readJson.getAsJsonArray();
                if (jsonArray.size() != 0) {
                    isEmptyArray = false;
                }

                if (isEmptyArray) {
                    crawlTweetFirst(fileJsonName);
                }

            } while (isEmptyArray);

            replaceJsonFile(fileJsonName);
            jsonAnalyst(fileJsonName);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void drawChart(String fileJsonName, String filePicturesName) throws IOException {
        JsonObject data = new JsonObject();
        data.addProperty("file_json_name", fileJsonName);
        data.addProperty("file_pictures_name", filePicturesName);
        serverClient.sendRequestWithResponse("/draw_chart", data);
    }

    public static void main(String[] args) {
        String fileJsonName = "dataEthTweet";
        String filePicturesName = "picturesEthTweet";
        TweetItem bitcoin = new TweetItem("ethereum", 800);
        try {
            bitcoin.crawlTweet(fileJsonName);
            bitcoin.drawChart(fileJsonName, filePicturesName);
        } catch (Exception e) {
            System.err.println("Server Flask not running");
        }

    }

}