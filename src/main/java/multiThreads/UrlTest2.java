package multiThreads;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class UrlTest2 {
    public static void main(String[] args) {
        String[] websites = {
            "https://vi.wikipedia.org/wiki/Vi%E1%BB%87t_Nam",
            "https://www.google.com",
            "https://www.github.com",
            "https://www.stackoverflow.com",
            "https://www.vnexpress1.net" // Thử nghiệm với một website không tồn tại
        };

        // Kiểm tra website có hoạt động không
        for (String website : websites) {
            checkWebsite(website);
        }

    }

    public static void checkWebsite(String url) {
        try {
            URL website = new URL(url);
            // Mở kết nối đến website
            HttpURLConnection connection = (HttpURLConnection) website.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();

            // Kiểm tra mã trạng thái HTTP
            int responseCode = connection.getResponseCode();
            if (responseCode == 200) {
                System.out.println(url + " is reachable.");
            } else {
                System.out.println(url + " is not reachable. Response code: " + responseCode);
            }
        } catch (MalformedURLException e) {     /* Xử lý url không hợp lệ */
            System.out.println(url + " is not a valid URL.");
        } catch (IOException e) {               /* Xử lý lỗi kết nối */
            System.out.println("Error connecting to " + url + ": " + e.getMessage());
        }
    }
}
