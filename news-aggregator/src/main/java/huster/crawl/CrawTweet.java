package huster.crawl;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class CrawTweet {

    public static void crawlTweetFisrt(TweetItem temp, String fileName) {
        try {
            // Thiết lập yêu cầu HTTP
            @SuppressWarnings("deprecation")
            URL url = new URL("http://localhost:5000/crawl_tweet");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            // Thiết lập phương thức yêu cầu là POST
            connection.setRequestMethod("POST");
            // Thiết lập tiêu đề yêu cầu để chỉ định kiểu dữ liệu truyền đi là JSON
            connection.setRequestProperty("Content-Type", "application/json");
            // Cho phép ghi dữ liệu đến máy chủ
            connection.setDoOutput(true);

            // Tạo một đối tượng JSON để đại diện cho dữ liệu cần gửi
            JsonObject data = new JsonObject();
            data.addProperty("name", temp.getName());
            data.addProperty("mode", "user");
            data.addProperty("amount", temp.getAmount());
            data.addProperty("file_name", fileName);

            // Mở một luồng đầu ra từ kết nối HTTP để gửi dữ liệu
            OutputStream os = connection.getOutputStream();

            // Ghi dữ liệu JSON đã tạo vào luồng đầu ra
            os.write(data.toString().getBytes());
            os.flush();
            os.close();

            // Nhận mã phản hồi từ server
            connection.getResponseCode();
            // Đóng kết nối
            connection.disconnect();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void replaceJsonFile(String fileJsonName) throws IOException {
        JsonArray jsonArray = new JsonArray();
        String dataPath = "news-aggregator\\recourse\\data\\" + fileJsonName + ".json";

        BufferedReader bufferedReader = new BufferedReader(new FileReader(dataPath));
        jsonArray = new Gson().fromJson(bufferedReader, JsonArray.class);
        bufferedReader.close();

        String modifyString = jsonArray.toString();
        modifyString = modifyString.replace("date", "PostingDate");
        modifyString = modifyString.replace("text", "Content");
        modifyString = modifyString.replace("link", "LinkTweet");

        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(dataPath));
        bufferedWriter.write(modifyString);
        bufferedWriter.close();
    }

    @SuppressWarnings("deprecation")
    public static void crawlTweet(TweetItem temp, String fileName) {
        crawlTweetFisrt(temp, fileName);
        boolean isEmptyArray = true;
        try {
            do {
                JsonParser parser = new JsonParser();
                JsonElement readJson = parser
                        .parse(new FileReader("news-aggregator\\recourse\\data\\" + fileName + ".json"));

                JsonArray jsonArray = readJson.getAsJsonArray();
                if (jsonArray.size() != 0) {
                    isEmptyArray = false;
                }

                if (isEmptyArray) {
                    CrawTweet.crawlTweetFisrt(temp, fileName);
                }

            } while (isEmptyArray);

            replaceJsonFile(fileName);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

        String fileName = "test4";
        TweetItem bitcoin = new TweetItem("elonmusk", "300");
        crawlTweet(bitcoin, fileName);

    }
}
