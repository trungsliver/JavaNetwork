package TCP.socketChat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class MyProcess extends Thread {
    private Socket socket;

    public MyProcess(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {

        try {
            // Xử lý kết nối với client
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);

            Scanner sc = new Scanner(System.in);
            while(true){
                // Nhận tin nhắn
                String message;
                message = reader.readLine();
                System.out.println("Client: " + message);


                // Gửi tin nhắn
                System.out.print("Server: ");
                String response = sc.nextLine();
                writer.println(response);
                writer.flush();
            }


//            TCP.socket.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
