package huster.crawl.crawlTweet;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import com.google.gson.stream.JsonReader;

public class ServerClient {
    private static String serverUrl = "http://127.0.0.1:5000";

    public ServerClient() {
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

    @SuppressWarnings("deprecation")
    public static JsonObject sendRequestWithResponse(String endPoint, JsonObject data) throws IOException {
        URL url = new URL(serverUrl + endPoint);

        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setDoOutput(true);

        OutputStream os = connection.getOutputStream();
        os.write(data.toString().getBytes());
        os.flush();
        os.close();

        String contentType = connection.getContentType();

        if (contentType != null && contentType.startsWith("application/json")) {
            BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;

            while ((line = br.readLine()) != null) {
                response.append(line);
            }
            br.close();

            connection.disconnect();

            JsonReader reader = new JsonReader(new StringReader(response.toString()));
            reader.setLenient(true); // Set lenient mode
            try {
                return JsonParser.parseReader(reader).getAsJsonObject();
            } catch (JsonSyntaxException e) {
                throw new IOException("Failed to parse JSON response", e);
            }
        } else if (contentType != null && contentType.startsWith("image/png")) {
            InputStream in = connection.getInputStream();
            FileOutputStream out = new FileOutputStream("output.png");

            byte[] buffer = new byte[4096];
            int bytesRead;
            while ((bytesRead = in.read(buffer)) != -1) {
                out.write(buffer, 0, bytesRead);
            }

            in.close();
            out.close();
            connection.disconnect();

            // Return an empty JsonObject to indicate success
            return new JsonObject();
        } else {
            connection.disconnect();
            throw new IOException("Unsupported content type: " + contentType);
        }
    }

    public static void shutDownServer() {
        try {
            sendRequestWithResponse("/shutdown", new JsonObject());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
