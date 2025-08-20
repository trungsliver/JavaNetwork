package socketChatRoom.client;

public class ChatRoomClient {
    public static void main(String[] args) {
        // Tạo một đối tượng Client
        ChatClient chatClient = new ChatClient();

        // Bắt đầu client
        chatClient.startClient();

        // In ra thông báo client đã khởi động
        System.out.println("Chat client is running...");
    }
}
