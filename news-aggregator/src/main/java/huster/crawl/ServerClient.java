package huster.crawl;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import com.google.gson.JsonObject;

public class ServerClient {
    private String serverUrl;

    public ServerClient(String serverUrl) {
        this.serverUrl = serverUrl;
    }

    public static void runServer() throws IOException {
        ProcessBuilder processBuilder = new ProcessBuilder("python",
                "news-aggregator/src/main/java/huster/crawl/TweetFlaskServer.py");
        processBuilder.inheritIO();
        processBuilder.start();
    }

    public static void shutDownServer() throws IOException {
        try {
            @SuppressWarnings("deprecation")
            URL url = new URL("http://localhost:5000/shutdown");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.getResponseCode();
            connection.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendRequest(String endPoint, JsonObject data) throws IOException {
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

        connection.getResponseCode();
        connection.disconnect();
    }

    public static void main(String[] args) throws IOException {
        ServerClient.runServer();
    }
}
