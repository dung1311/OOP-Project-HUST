package huster.crawl.crawlTweet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;

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

        try (OutputStream os = connection.getOutputStream()) {
            os.write(data.toString().getBytes());
            os.flush();
        }

        int responseCode = connection.getResponseCode();
        StringBuilder response = new StringBuilder();

        try (BufferedReader in = new BufferedReader(new InputStreamReader(
                responseCode == HttpURLConnection.HTTP_OK ? connection.getInputStream()
                        : connection.getErrorStream()))) {
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
        }

        System.out.println("Response Code: " + responseCode);
        System.out.println("Response Body: " + response.toString());

        if (responseCode == HttpURLConnection.HTTP_OK) {
            try {
                return JsonParser.parseString(response.toString()).getAsJsonObject();
            } catch (JsonSyntaxException e) {
                System.err.println("Failed to parse JSON response: " + e.getMessage());
                throw new IOException("Failed to parse JSON response", e);
            }
        } else {
            throw new IOException(
                    "Request failed with response code: " + responseCode + " and message: " + response.toString());
        }
    }

    public void shutDownServer() {
        try {
            sendRequestWithResponse("/shutdown", new JsonObject());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ServerClient.runServer();
    }
}
