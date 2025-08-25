package TCP.socketChatRoom.client;

import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

public class ChatClient {
    private static final String URL = "localhost";
    private static final int PORT = 5000;
    private volatile Socket socket;

    public void startClient() {
        while (true) {
            try {
                socket = new Socket(URL, PORT);
                System.out.println("Connected to server at " + URL + ":" + PORT);

                // Tạo một ClientListener để liên tục nhận dữ liệu từ server
                ClientListener clientListener = new ClientListener(socket, this);
                Thread listenerThread = new Thread(clientListener);
                listenerThread.start();

                // Liên tục đọc dữ liệu từ Scanner và gửi lên server
                OutputStream outputStream = socket.getOutputStream();
                Scanner scanner = new Scanner(System.in);
                while (!socket.isClosed() && socket.isConnected()) {
                    if (!scanner.hasNextLine()) break;
                    String message = scanner.nextLine();
                    try {
                        outputStream.write(message.getBytes());
                    } catch (Exception e) {
                        // Socket có thể đã đóng, thoát khỏi vòng lặp gửi để reconnect
                        break;
                    }
                }
            } catch (Exception e) {
                // Không báo lỗi, thử lại sau 5s
                try {
                    System.out.println("Failed to connect to server. Retrying in 5 seconds...");
                    Thread.sleep(5000);
                } catch (InterruptedException ie) {
                    // ...ignore...
                }
            }
        }
    }

    // Được gọi từ ClientListener khi mất kết nối
    public void reconnect() {
        try {
            System.out.println("Disconnected from server. Attempting to reconnect...");
            if (socket != null && !socket.isClosed()) {
                socket.close();
            }
        } catch (Exception e) {}
        // Vòng lặp ngoài sẽ tự động thử lại
    }
}
