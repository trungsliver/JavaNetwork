package remoteServer.Server;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class RemoteServer {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(5000);
            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("Client connected: " + socket.getInetAddress().getHostAddress());

                // Tạo Thread thực thi
                Thread thread = new Thread(() -> {
                    handleClientRequest(socket);
                });
                thread.start();
            }
        } catch (Exception e) {
            System.out.println("Error creating server TCP.socket: " + e.getMessage());
        }
    }

    public static void handleClientRequest(Socket socket) {
        try{
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);

            String request = reader.readLine();
            if (request.equals("shutdown")) {
               Runtime.getRuntime().exec("shutdown -s -t 3600");
                writer.println("Server will shut down in 1 hour.");
            } else if (request.equals("restart")) {
               Runtime.getRuntime().exec("shutdown -r -t 3600");
                writer.println("Server will restart in 1 hour.");
            } else if (request.equals("cancel")) {
                Runtime.getRuntime().exec("shutdown -a");
                writer.println("Shutdown or restart command has been canceled.");
            } else if (request.equals("screenshot")) {
                BufferedImage image = new Robot().createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                ImageIO.write(image, "png", baos);
                byte[] imageBytes = baos.toByteArray();
                baos.close();

                writer.println(imageBytes.length);
                writer.flush(); // Ensure the size is sent before the image bytes

                int totalBytesSent = 0;
                while (totalBytesSent < imageBytes.length) {
                    int bytesToSend = Math.min(4096, imageBytes.length - totalBytesSent);
                    socket.getOutputStream().write(imageBytes, totalBytesSent, bytesToSend);
                    totalBytesSent += bytesToSend;
                }
                socket.getOutputStream().flush();
                System.out.println("Screenshot sent to client.");
            } else {
                // Xử lý các yêu cầu khác
                writer.println("Received request: " + request);
                System.out.println("Processed request: " + request);
            }
        }catch (Exception e){}
    }
}
