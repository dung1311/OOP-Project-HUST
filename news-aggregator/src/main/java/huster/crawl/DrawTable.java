package huster.crawl;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import com.google.gson.JsonObject;

public class DrawTable {

    public static JsonObject dataPictures(String fileJsonName, String filePicturesName) {
        JsonObject data = new JsonObject();
        data.addProperty("file_json_name", fileJsonName);
        data.addProperty("file_pictures_name", filePicturesName);
        return data;
    }

    public static void main(String[] args) {
        try {

            @SuppressWarnings("deprecation")
            URL url = new URL("http://localhost:5000/draw_chart");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setDoOutput(true);

            JsonObject data = dataPictures("ethJson", "ethTable");

            OutputStream os = connection.getOutputStream();
            os.write(data.toString().getBytes());
            os.flush();
            os.close();

            connection.getResponseCode();
            connection.disconnect();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
