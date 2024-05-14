package huster.crawl;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class ServerManager {

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

    public static void main(String[] args) throws IOException {
        ServerManager.runServer();
    }
}
