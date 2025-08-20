package TCP.network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.net.URL;

public class UrlTest {
    public static void main(String[] args) throws IOException, URISyntaxException {
        // Example URL
        String urlString = "https://vi.wikipedia.org/wiki/Vi%E1%BB%87t_Nam";
        URL url = new URL(urlString);
        System.out.println("URL: " + url);

        // Đọc dữ liệu
        InputStreamReader inputStreamReader = new InputStreamReader(url.openStream());
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            System.out.println(line);
        }
        bufferedReader.close();
        inputStreamReader.close();

    }
}
