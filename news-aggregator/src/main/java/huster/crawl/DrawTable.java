package huster.crawl;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import com.google.gson.JsonObject;

public class DrawTable {
    public static void main(String[] args) {
        try {
            // Thiết lập yêu cầu HTTP để gửi yêu cầu vẽ biểu đồ
            @SuppressWarnings("deprecation")
            URL url = new URL("http://localhost:5000/draw_chart");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            // Thiết lập phương thức yêu cầu là POST
            connection.setRequestMethod("POST");
            // Thiết lập tiêu đề yêu cầu để chỉ định kiểu dữ liệu truyền đi là JSON
            connection.setRequestProperty("Content-Type", "application/json");
            // Cho phép ghi dữ liệu đến máy chủ
            connection.setDoOutput(true);

            // Tạo một đối tượng JSON để đại diện cho dữ liệu cần gửi (tên file)
            JsonObject data = new JsonObject();
            data.addProperty("file_name", "abcd");

            // Mở một luồng đầu ra từ kết nối HTTP để gửi dữ liệu
            OutputStream os = connection.getOutputStream();
            // Ghi dữ liệu JSON đã tạo vào luồng đầu ra
            os.write(data.toString().getBytes());
            os.flush();
            os.close();

            // Nhận mã phản hồi từ máy chủ
            int responseCode = connection.getResponseCode();
            System.out.println("Response Code: " + responseCode);

            // Đóng kết nối
            connection.disconnect();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
