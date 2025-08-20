package TCP.socketChat;

import java.net.ServerSocket;
import java.net.Socket;

public class ChatServer {
    public static void main(String[] args) {
        try {
            // Tạo server TCP.socket và lắng nghe kết nối từ client
           int port = 99;
           ServerSocket serverSocket = new ServerSocket(port);

           // Chấp nhận kết nối từ 1 client
//            Socket clientSocket = serverSocket.accept();

            // Chấp nhận kết nối từ nhiều client
            while(true){
                Socket socket = serverSocket.accept();
                System.out.println("Client connected: " + socket.getInetAddress() + ":" + socket.getPort());

                // Tạo một luồng mới để xử lý kết nối với client
                MyProcess process = new MyProcess(socket);
                process.start();
            }

            // Bắt đầu trao đổi thông tin với client
//            Thread.sleep(5000);

            // Đóng kết nối
//            clientSocket.close();
//            serverSocket.close();

        }catch (Exception e){
            System.out.println("Error in server: " + e.getMessage());
        }
    }
}
