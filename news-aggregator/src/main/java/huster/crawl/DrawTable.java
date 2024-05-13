package huster.crawl;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import com.google.gson.JsonObject;

public class DrawTable {

    public static void drawTable(String fileJsonName, String filePicturesName) {
        try {
            @SuppressWarnings("deprecation")
            URL url = new URL("http://localhost:5000/draw_chart");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setDoOutput(true);

            JsonObject data = new JsonObject();
            data.addProperty("file_json_name", fileJsonName);
            data.addProperty("file_pictures_name", filePicturesName);

            OutputStream os = connection.getOutputStream();
            os.write(data.toString().getBytes());
            os.flush();
            os.close();

            connection.getResponseCode();
            connection.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        DrawTable.drawTable("test4", "test4");
    }
}
