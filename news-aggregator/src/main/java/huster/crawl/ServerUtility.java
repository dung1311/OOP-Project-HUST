package huster.crawl;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class ServerUtility {

    public static void openServer() {
        try {
            ProcessBuilder processBuilder = new ProcessBuilder("python",
                    "news-aggregator/src/main/java/huster/crawl/TweetFlaskServer.py");
            processBuilder.inheritIO();
            Process process = processBuilder.start();
            process.waitFor();
        } catch (InterruptedException | IOException i) {
            i.printStackTrace();
        }
    }

    @SuppressWarnings("deprecation")
    public static void shutDownServer() {
        try {
            URL url = new URL("http://localhost:5000/shutdown");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.getResponseCode();
            connection.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ServerUtility.openServer();
    }
}
