package url;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpGet {
    public static void main(String[] args) {
        try {
            // URL API để gửi yêu cầu GET
            URL url = new URL("https://httpbin.org/get");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            // Cấu hình request
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            // Lấy mã phản hồi từ server
            int responseCode = conn.getResponseCode();
            System.out.println("Response Code: " + responseCode);

            // Nếu thành công (HTTP 200 OK), đọc nội dung phản hồi
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
                StringBuilder response = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                reader.close();

                // Hiển thị nội dung phản hồi
                System.out.println("Response: " + response);
            } else {
                System.out.println("GET request failed.");
            }

            conn.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
