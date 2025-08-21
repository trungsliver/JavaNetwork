package multicastLightStick;

import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.util.Scanner;

public class LightControllerServer {
    public static void main(String[] args) {
        try{
            InetAddress group = InetAddress.getByName("230.0.0.3");
            MulticastSocket socket = new MulticastSocket();

            String[] colors = {"red", "green", "blue", "yellow", "purple", "cyan"};
            int colorIndex = 0;

            Scanner sc = new Scanner(System.in);
            while(true){
//                System.out.println("=========== PICK COLOR ===========");
//                System.out.println("0. Red");
//                System.out.println("1. Green");
//                System.out.println("2. Blue");
//                System.out.println("3. Yellow");
//                System.out.println("4. Purple");
//                System.out.println("5. Cyan");
//                System.out.println("===================================");
//                System.out.println("Current color: " + colors[colorIndex]);
//                System.out.print("Enter your choice (0-5): ");
//                int choice = sc.nextInt();
//                if (choice < 0 || choice > 5) {
//                    System.out.println("Invalid choice. Please try again.");
//                    continue;
//                }
//                String message = colors[choice];
//                byte[] snedData = message.getBytes();
//                DatagramPacket sendPacket = new DatagramPacket(snedData, snedData.length, group, 9876);
//                socket.send(sendPacket);

                // Chuyển đổi màu nền
                colorIndex = (colorIndex + 1) % colors.length;

                String message = colors[colorIndex];
                byte[] snedData = message.getBytes();
                DatagramPacket sendPacket = new DatagramPacket(snedData, snedData.length, group, 9876);
                socket.send(sendPacket);

                // Chờ 5 giây trước khi gửi màu tiếp theo
                Thread.sleep(5000);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
