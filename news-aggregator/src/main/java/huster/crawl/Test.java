package huster.crawl;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

class Main {
    public static void main(String[] args) {
        try {
            @SuppressWarnings("deprecation")
            URL url = new URL("http://localhost:5000/draw_chart");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Type", "application/json");
            con.setDoOutput(true);

            // Dữ liệu JSON chứa tên file dữ liệu JSON để vẽ biểu đồ
            String jsonInputString = "{news-aggregator\\recourse\\data\\abcd.json}";

            byte[] input = jsonInputString.getBytes(StandardCharsets.UTF_8);
            con.getOutputStream().write(input, 0, input.length);

            int responseCode = con.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                // Lấy dữ liệu hình ảnh từ response
                InputStream inputStream = con.getInputStream();
                FileOutputStream outputStream = new FileOutputStream("output.png");
                byte[] buffer = new byte[1024];
                int bytesRead;
                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, bytesRead);
                }
                outputStream.close();
                inputStream.close();
                System.out.println("Biểu đồ đã được tạo và lưu thành công vào file output.png");
            } else {
                System.out.println("Lỗi khi gọi API: " + responseCode);
            }
            con.disconnect(); // Đóng kết nối sau khi gửi yêu cầu
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

