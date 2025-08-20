package UDP.simpleUDP.server;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class UdpServer {
    public static void main(String[] args) {
        try {
            // Tạo một DatagramSocket để lắng nghe kết nối từ client
            DatagramSocket socket = new DatagramSocket(9876);

            // Dùng để nhận dữ liệu từ client
            byte[] receiveData = new byte[1024];

            while (true) {
                // Tạo DatagramPacket để nhận dữ liệu
                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);

                socket.receive(receivePacket);

                // Chờ nhận dữ liệu từ client
                String recivedMessage = new String(receivePacket.getData(),0, receivePacket.getLength());

                if (recivedMessage.trim().length() > 0) {
                    System.out.println("Received message from client: " + recivedMessage);
                }

            }

//            socket.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
