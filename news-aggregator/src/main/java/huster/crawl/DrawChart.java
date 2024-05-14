package huster.crawl;

import java.io.IOException;
import com.google.gson.JsonObject;

public class DrawChart {
    private ServerClient serverClient;

    public DrawChart(ServerClient serverClient) {
        this.serverClient = serverClient;
    }

    public void drawChart(String fileJsonName, String filePicturesName) throws IOException {
        JsonObject data = new JsonObject();
        data.addProperty("file_json_name", fileJsonName);
        data.addProperty("file_pictures_name", filePicturesName);
        serverClient.sendRequest("/draw_chart", data);
    }
}
