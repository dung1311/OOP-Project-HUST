package huster.crawl.crawlTweet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class ServerClient {
    private String serverUrl;

    public ServerClient(String serverUrl) {
        this.serverUrl = serverUrl;
    }

    public static void runServer() {
        try {
            ProcessBuilder processBuilder = new ProcessBuilder("python",
                    "news-aggregator/src/main/java/huster/crawl/crawlTweet/TweetFlaskServer.py");
            processBuilder.inheritIO();
            processBuilder.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public JsonObject sendRequestWithResponse(String endPoint, JsonObject data) throws IOException {
        @SuppressWarnings("deprecation")
        URL url = new URL(serverUrl + endPoint);

        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setDoOutput(true);

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

            return JsonParser.parseString(response.toString()).getAsJsonObject();
        } else {
            throw new IOException("Request failed with response code: " + responseCode);
        }
    }

    public void shutDownServer() {
        try {
            sendRequestWithResponse("/shutdown", new JsonObject());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
