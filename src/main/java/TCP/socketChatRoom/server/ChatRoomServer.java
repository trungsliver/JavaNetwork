package TCP.socketChatRoom.server;

public class ChatRoomServer {
    public static void main(String[] args) {
        // Tạo một đối tượng ChatServer
        ChatServer chatServer = new ChatServer();

        // Bắt đầu server
        chatServer.startServer();

        // In ra thông báo server đã khởi động
        System.out.println("Chat server is running...");
    }
}
