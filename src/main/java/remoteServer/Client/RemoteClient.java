package remoteServer.Client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

public class RemoteClient {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 5000);

            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);

            Scanner scanner = new Scanner(System.in);

            boolean exit = false;
            while (!exit) {
                System.out.println("\n================== MENU ==================");
                System.out.println("1. Shutdown the server");
                System.out.println("2. Restart the server");
                System.out.println("3. Cancel request shutdown/restart");
                System.out.println("4. Screenshot");
                System.out.println("5. Exit");
                System.out.println("==========================================");
                System.out.print("Enter your choice: ");
                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline character
                switch (choice) {
                    case 1:
                        writer.println("shutdown");
                        writer.flush();
                        System.out.println("Shutdown command sent to server.");
                        System.out.println(reader.readLine());
                        break;
                    case 2:
                        writer.println("restart");
                        writer.flush();
                        System.out.println("Restart command sent to server.");
                        System.out.println(reader.readLine());
                        break;
                    case 3:
                        writer.println("cancel");
                        writer.flush();
                        System.out.println("Cancel the command.");
                        System.out.println(reader.readLine());
                        break;
                    case 4:
                        writer.println("screenshot");
                        writer.flush();
                        System.out.println("screenshot command sent to server.");
                        int imageSize = Integer.parseInt(reader.readLine());
                        byte[] imageBytes = new byte[imageSize];
                        int bytesRead = socket.getInputStream().read(imageBytes);
                        if (bytesRead > 0){
                            System.out.println("Nhập tên ảnh: ");
                            String imageName = scanner.nextLine();
                            String directory = "D:\\";
                            Path imagePath = Path.of(directory + imageName + ".png");
                            Files.write(imagePath, imageBytes);
                            System.out.println("Screenshot saved to " + imagePath.toAbsolutePath());
                        }
                        break;
                    case 5:
                        exit = true;
                        System.out.println("Exiting client.");
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
