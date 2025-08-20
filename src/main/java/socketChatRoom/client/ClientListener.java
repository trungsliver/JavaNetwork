package socketChatRoom.client;

import java.io.InputStream;
import java.net.Socket;

public class ClientListener implements Runnable {
    private Socket socket;
    private InputStream inputStream;

    public ClientListener(Socket socket) {
        this.socket = socket;
        try {
            this.inputStream = socket.getInputStream();
        } catch (Exception e) {
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
                System.out.println(message);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
