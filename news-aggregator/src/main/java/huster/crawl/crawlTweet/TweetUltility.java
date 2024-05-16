package huster.crawl.crawlTweet;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class TweetUltility {
    private ServerClient serverClient;

    public TweetUltility(ServerClient serverClient) {
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
        File file = new File("news-aggregator/resource/data/" + fileJsonName + ".json");
        if (!file.exists()) {
            throw new IOException("File not created");
        }
    }

    public static void replaceJsonFile(String fileJsonName) throws IOException {
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

    @SuppressWarnings("deprecation")
    public void crawlTweet(TweetItem tweetItem, String fileJsonName) {
        try {
            crawlTweetFirst(tweetItem, fileJsonName);
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
                    crawlTweetFirst(tweetItem, fileJsonName);
                }

            } while (isEmptyArray);

            replaceJsonFile(fileJsonName);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void drawChart(String fileJsonName, String filePicturesName) throws IOException {
        JsonObject data = new JsonObject();
        data.addProperty("file_json_name", fileJsonName);
        data.addProperty("file_pictures_name", filePicturesName);
        serverClient.sendRequest("/draw_chart", data);
    }

    public JsonObject jsonAnalyst(String fileJsonName) {
        JsonObject responseData = null;
        String serverUrl = "http://localhost:5000";
        try {
            URL url = new URL(serverUrl + "/json_analyst");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setDoOutput(true);

            JsonObject data = new JsonObject();
            data.addProperty("file_json_name", fileJsonName);

            OutputStream os = connection.getOutputStream();
            os.write(data.toString().getBytes());
            os.flush();
            os.close();

            int responseCode = connection.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String inputLine;
                StringBuilder response = new StringBuilder();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                responseData = JsonParser.parseString(response.toString()).getAsJsonObject();
            }

            connection.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return responseData;
    }

    public static void main(String[] args) throws IOException {
        String serverUrl = "http://127.0.0.1:5000";
        ServerClient serverTest = new ServerClient(serverUrl);
        TweetUltility crawlTweet = new TweetUltility(serverTest);
        TweetItem bitcoin = new TweetItem("Bitcoin", 20);
        // crawlTweet.crawlTweet(bitcoin, "dataBitcoinTweet");
        // crawlTweet.drawChart("dataBitcoinTweet", "picturesBitcoinTweet");
        JsonObject stat = crawlTweet.jsonAnalyst("dataBitcoinTweet");
        System.out.println(stat);
    }
}
