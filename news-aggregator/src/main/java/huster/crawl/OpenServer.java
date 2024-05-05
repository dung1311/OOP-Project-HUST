package huster.crawl;

import java.io.IOException;

public class OpenServer {
    public static void openServer() throws IOException, InterruptedException {
        ProcessBuilder processBuilder = new ProcessBuilder("python",
                "news-aggregator/src/main/java/huster/crawl/CrawlTweet.py");
        processBuilder.inheritIO();
        Process process = processBuilder.start();
        process.waitFor();
    }
    public static void main(String[] args) throws IOException, InterruptedException {
        OpenServer.openServer();
    }
}
