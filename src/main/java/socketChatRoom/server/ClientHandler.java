package socketChatRoom.server;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class ClientHandler implements Runnable{
    private Socket socket;
    private String id;
    private ChatServer chatServer;
    private InputStream inputStream;
    private OutputStream outputStream;

    public ClientHandler(Socket socket, String id, ChatServer chatServer) {
        this.socket = socket;
        this.id = id;
        this.chatServer = chatServer;
        try {
            this.inputStream = socket.getInputStream();
            this.outputStream = socket.getOutputStream();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        try {
            // Đọc dữ liệu từ client
            byte[] buffer = new byte[1024];
            // Số lượng byte đọc được
            int bytesRead;
            // Đọc dư liệu từ inputStream
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                String message = new String(buffer, 0, bytesRead);
                chatServer.broadcastMessage(this.id,  message);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void broadcastMessage(String message) {

    }

    public void sendMessage(String message){
        try {
            outputStream.write(message.getBytes());
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
