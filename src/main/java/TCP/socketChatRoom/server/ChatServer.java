package TCP.socketChatRoom.server;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ChatServer {
    private static final int PORT = 5000;
    private List<ClientHandler> clients = new ArrayList<>();

    public void startServer(){
        try {
            // websocket
            ServerSocket serverSocket = new ServerSocket(PORT);
            System.out.println("Server started on port " + PORT);

            // client connect to server
            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("New client connected: " + clientSocket.getInetAddress() + ":" + clientSocket.getPort());

                ClientHandler clientHandler = new ClientHandler(clientSocket, System.currentTimeMillis()+"", this);
                clients.add(clientHandler);
                // Start a new thread for each client
                Thread thread = new Thread(clientHandler);
                thread.start();
            }
        }catch (Exception e){}
    }

    public void broadcastMessage(String id, String message) {
        for (ClientHandler clientHandler : clients) {
            if (!clientHandler.getId().equals(id)) {
                clientHandler.sendMessage(id + ": " + message);
            }

        }
    }
}
