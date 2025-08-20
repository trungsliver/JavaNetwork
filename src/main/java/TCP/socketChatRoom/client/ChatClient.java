package TCP.socketChatRoom.client;

import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

public class ChatClient {
    private static final String URL = "localhost";
    private static final int PORT = 5000;

    public void startClient(){
        try {
            Socket socket = new Socket(URL, PORT);
            System.out.println("Connected to server at " + URL + ":" + PORT);

            // Tạo một ClientListener để liên tục nhận dữ liệu từ server
            ClientListener clientListener = new ClientListener(socket);
            new Thread(clientListener).start();

            // Liên tục đọc dữ liệu từ Scanner
            OutputStream outputStream = socket.getOutputStream();
            Scanner scanner = new Scanner(System.in);
            while (true) {
                String message = scanner.nextLine();
                outputStream.write(message.getBytes());
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
