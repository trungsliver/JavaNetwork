package socketChat;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ChatClient {
    public static void main(String[] args) {
        try {
            // Tạo client socket và kết nối đến server
            // Lưu ý: Địa chỉ IP và cổng phải khớp với server
            int port = 99;
            Socket socket = new Socket("localhost", port);

            // Tạo BufferedReader và PrintWriter để đọc và ghi dữ liệu
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);

            String message;
            Scanner sc = new Scanner(System.in);
            while(true){
                // Gửi tin nhắn
                System.out.print("Client: ");
                String response = sc.nextLine();
                writer.println(response);
                writer.flush();

                // Nhận tin nhắn
                message = reader.readLine();
                System.out.println("Server: " + message);
            }
        }catch (Exception e){
            System.out.println("Error connecting to server: " + e.getMessage());
        }
    }
}
