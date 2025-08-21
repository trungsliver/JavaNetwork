package url;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpPost {
    public static void main(String[] args) {
        try {
            // URL API để gửi yêu cầu POST
            URL url = new URL("https://httpbin.org/post");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            // Cấu hình request
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json; utf-8");
            conn.setRequestProperty("Accept", "application/json");
            conn.setDoOutput(true); // Cho phép gửi dữ liệu

            // Dữ liệu JSON gửi đi
            String jsonInputString = "{"
                    + "\"name\": \"John\","
                    + "\"age\": 30,"
                    + "\"city\": \"New York\""
                    + "}";

            // Gửi dữ liệu JSON qua OutputStream
            try (OutputStream os = conn.getOutputStream()) {
                byte[] input = jsonInputString.getBytes("utf-8");
                os.write(input, 0, input.length);
            }

            // Đọc phản hồi từ server
            int responseCode = conn.getResponseCode();
            System.out.println("Response Code: " + responseCode);

            if (responseCode == HttpURLConnection.HTTP_OK) { // 200 OK
                BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
                StringBuilder response = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    response.append(line.trim());
                }
                reader.close();
                System.out.println("Response: " + response);
            } else {
                System.out.println("POST request failed.");
            }

            conn.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
