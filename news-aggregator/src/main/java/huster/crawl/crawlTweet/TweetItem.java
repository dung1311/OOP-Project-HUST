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
    private String fileJsonName;
    private String filePicturesName;
    private JsonObject highestInteractionTweet;

    public TweetItem(String name) {
        this.name = name;
        this.fileJsonName = name;
        this.filePicturesName = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setFileJsonName(String fileJsonName) {
        this.fileJsonName = fileJsonName;
    }

    public String getFileJsonName() {
        return fileJsonName;
    }

    public JsonObject getHighestInteractionTweet() {
        return highestInteractionTweet;
    }

    public void setHighestInteractionTweet(JsonObject highestInteractionTweet) {
        this.highestInteractionTweet = highestInteractionTweet;
    }

    public void replaceJsonFile(String fileJsonName) {
        String dataPath = "news-aggregator\\resource\\data\\tweetData\\" + fileJsonName + ".json";

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(dataPath))) {
            JsonArray jsonArray = new Gson().fromJson(bufferedReader, JsonArray.class);

            for (int i = 0; i < jsonArray.size(); i++) {
                JsonObject tweetObject = jsonArray.get(i).getAsJsonObject();

                // Tách thông tin về tác giả
                JsonObject authorObject = tweetObject.getAsJsonObject("user");
                String authorName = authorObject.get("name").getAsString();
                String authorUsername = authorObject.get("username").getAsString();
                String authorAvatar = authorObject.get("avatar").getAsString();

                // Tách thông tin thống kê
                JsonObject statsObject = tweetObject.getAsJsonObject("stats");
                int comments = statsObject.get("comments").getAsInt();
                int retweets = statsObject.get("retweets").getAsInt();
                int quotes = statsObject.get("quotes").getAsInt();
                int likes = statsObject.get("likes").getAsInt();

                // Tách đối tượng hình ảnh, video, gif
                JsonArray picturesArray = tweetObject.getAsJsonArray("pictures");
                JsonArray videosArray = tweetObject.getAsJsonArray("videos");
                JsonArray gifsArray = tweetObject.getAsJsonArray("gifs");

                // Đổi các trường thành chuỗi
                tweetObject.addProperty("title", "[]");
                tweetObject.addProperty("summary", "[]");
                tweetObject.addProperty("url", "https://x.com/" + authorName);
                tweetObject.addProperty("content", tweetObject.get("text").getAsString());
                tweetObject.addProperty("datetimeCreation", tweetObject.get("date").getAsString());
                tweetObject.addProperty("author", authorName);
                tweetObject.addProperty("authorUsername", authorUsername);
                tweetObject.addProperty("authorAvatar", authorAvatar);
                tweetObject.addProperty("comments", comments);
                tweetObject.addProperty("retweets", retweets);
                tweetObject.addProperty("quotes", quotes);
                tweetObject.addProperty("likes", likes);
                tweetObject.addProperty("linkImage", picturesArray.toString());
                tweetObject.addProperty("videos", videosArray.toString());
                tweetObject.addProperty("gifs", gifsArray.toString());

                // Xóa các trường không cần thiết
                tweetObject.remove("text");
                tweetObject.remove("date");
                tweetObject.remove("user");
                tweetObject.remove("stats");
                tweetObject.remove("pictures");
            }

            Gson gson = new Gson();

            try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(dataPath))) {
                gson.toJson(jsonArray, bufferedWriter);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void crawlTweetFirst() throws IOException {

        JsonObject data = new JsonObject();
        data.addProperty("name", name);
        data.addProperty("mode", "user");
        data.addProperty("amount", 20);
        data.addProperty("file_name", fileJsonName);
        try {
            serverClient.sendRequestWithResponse("/crawl_tweet", data);
        } catch (Exception e) {
            e.printStackTrace();
        }
        File file = new File("news-aggregator/resource/data/tweetData/" + fileJsonName + ".json");
        if (!file.exists()) {
            throw new IOException("File not created");
        }
    }

    public void crawlTweetFromNitter() throws IOException {
        JsonObject data = new JsonObject();
        data.addProperty("user_name", name);
        serverClient.sendRequestWithResponse("/crawl_nitter", data);
    }

    public void jsonAnalyst(String fileJsonName) throws IOException {
        JsonObject data = new JsonObject();
        data.addProperty("file_json_name", fileJsonName);
        JsonObject responseData = serverClient.sendRequestWithResponse("/json_analyst", data);
        this.highestInteractionTweet = responseData.getAsJsonObject("highestInteractionTweet");
    }

    @SuppressWarnings("deprecation")
    public void crawlTweet() {
        try {
            crawlTweetFirst();
            boolean isEmptyArray = true;

            do {
                JsonParser parser = new JsonParser();
                JsonElement readJson = parser
                        .parse(new FileReader("news-aggregator\\resource\\data\\tweetData\\" + fileJsonName + ".json"));

                JsonArray jsonArray = readJson.getAsJsonArray();
                if (jsonArray.size() != 0) {
                    isEmptyArray = false;
                }

                if (isEmptyArray) {
                    crawlTweetFromNitter();
                }

            } while (isEmptyArray);

            replaceJsonFile(fileJsonName);
            jsonAnalyst(fileJsonName);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void drawChart() throws IOException {
        JsonObject data = new JsonObject();
        data.addProperty("file_json_name", fileJsonName);
        data.addProperty("file_pictures_name", filePicturesName);
        serverClient.sendRequestWithResponse("/draw_chart", data);
    }

    public static void main(String[] args) {
        TweetItem t = new TweetItem("SpaceX");
        t.replaceJsonFile("Bitcoin");
    }
}