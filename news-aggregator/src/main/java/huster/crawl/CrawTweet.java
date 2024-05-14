package huster.crawl;

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

public class CrawTweet {
    private ServerClient serverClient;

    public CrawTweet(ServerClient serverClient) {
        this.serverClient = serverClient;
    }

    public void crawlTweetFirst(TweetItem tweetItem, String fileJsonName) throws IOException {

        JsonObject data = new JsonObject();
        data.addProperty("name", tweetItem.getName());
        data.addProperty("mode", "user");
        data.addProperty("amount", tweetItem.getAmount());
        data.addProperty("file_name", fileJsonName);
        try {
            serverClient.sendRequest("/crawl_tweet", data);
        } catch (Exception e) {
            e.printStackTrace();
        }
        File file = new File("news-aggregator/recourse/data/" + fileJsonName + ".json");
        if (!file.exists()) {
            throw new IOException("File not created");
        }
    }

    public static void replaceJsonFile(String fileJsonName) throws IOException {
        String dataPath = "news-aggregator\\recourse\\data\\" + fileJsonName + ".json";

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

    @SuppressWarnings("deprecation")
    public void crawlTweet(TweetItem tweetItem, String fileJsonName) {
        try {
            crawlTweetFirst(tweetItem, fileJsonName);
            boolean isEmptyArray = true;

            do {
                JsonParser parser = new JsonParser();
                JsonElement readJson = parser
                        .parse(new FileReader("news-aggregator\\recourse\\data\\" + fileJsonName + ".json"));

                JsonArray jsonArray = readJson.getAsJsonArray();
                if (jsonArray.size() != 0) {
                    isEmptyArray = false;
                }

                if (isEmptyArray) {
                    crawlTweetFirst(tweetItem, fileJsonName);
                }

            } while (isEmptyArray);

            replaceJsonFile(fileJsonName);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
