package huster.crawl;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import com.google.gson.JsonObject;

class Main {
    public static void main(String[] args) {
        try {
            //Thiết lập yêu cầu HTTP
            @SuppressWarnings("deprecation")
            URL url = new URL("http://localhost:5000/crawl_tweet");
            HttpURLConnection pythonWeb = (HttpURLConnection) url.openConnection();
            pythonWeb.setRequestMethod("POST");
            pythonWeb.setRequestProperty("Content-Type", "application/json");
            pythonWeb.setDoOutput(true);

            //Tạo một đối tượng JSON để đại diện cho dữ liệu cần gửi.
            JsonObject data = new JsonObject();
            data.addProperty("name", "SpaceX");
            data.addProperty("mode", "user");
            data.addProperty("amount", 200);
            data.addProperty("file_name", "abcd");

            //Mở một luồng đầu ra từ kết nối HTTP để gửi dữ liệu.
            OutputStream os = pythonWeb.getOutputStream();

            //Ghi dữ liệu JSON đã tạo vào luồng đầu ra.
            os.write(data.toString().getBytes());
            os.flush();
            os.close();

            pythonWeb.getResponseCode();
            pythonWeb.disconnect();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
