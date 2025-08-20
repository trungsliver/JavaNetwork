package UDP.simpleUDP.client;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UdpClient {
    public static void main(String[] args) {
        try {
            // Tạo một DatagramSocket để gửi dữ liệu đến server
            DatagramSocket socket = new DatagramSocket();

            // Chuẩn bị dữ liệu để gửi đến server
            InetAddress address = InetAddress.getByName("localhost");
            int serverPort = 9876;

            int i = 0;
            while (true){
                // Chuyển đổi chuỗi thành mảng byte
                String message = "Hello, UDP Server!" + i;
                System.out.println("Sending message: " + message);
                byte[] sendData = message.getBytes();

                // Tạo DatagramPacket để gửi dữ liệu
                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, address, serverPort);

                // Gửi dữ liệu đến server
                socket.send(sendPacket);

                i++;

                // Tạm dừng 1 giây trước khi gửi tiếp
                Thread.sleep(1000);
            }

//            socket.close();

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
