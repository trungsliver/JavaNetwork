package TCP.socketChatRoom.client;

import java.io.InputStream;
import java.net.Socket;

public class ClientListener implements Runnable {
    private Socket socket;
    private InputStream inputStream;
    private ChatClient chatClient;

    public ClientListener(Socket socket, ChatClient chatClient) {
        this.socket = socket;
        this.chatClient = chatClient;
        try {
            this.inputStream = socket.getInputStream();
        } catch (Exception e) {
            // ...ignore...
        }
    }

    @Override
    public void run() {
        try {
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                String message = new String(buffer, 0, bytesRead);
                System.out.println(message);
            }
        } catch (Exception e) {
            // Không in stack trace khi mất kết nối
        } finally {
            chatClient.reconnect();
        }
    }
}
