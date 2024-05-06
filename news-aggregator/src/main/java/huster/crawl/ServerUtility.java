package huster.crawl;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class ServerUtility {

    public static void openServer() throws IOException, InterruptedException {
        ProcessBuilder processBuilder = new ProcessBuilder("python",
                "news-aggregator/src/main/java/huster/crawl/CrawlTweet.py");
        processBuilder.inheritIO();
        Process process = processBuilder.start();
        process.waitFor();
    }

    public static void shutDownServer() throws IOException {
        @SuppressWarnings("deprecation")
        URL url = new URL("http://localhost:5000/shutdown");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.getResponseCode();
        connection.disconnect();
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        ServerUtility.openServer();
    }
}
