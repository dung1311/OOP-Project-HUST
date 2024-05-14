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

}
