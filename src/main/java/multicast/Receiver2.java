package multicast;

import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class Receiver2 {
    public static void main(String[] args) {
        try{
            InetAddress group = InetAddress.getByName("230.0.0.1");
            MulticastSocket socket = new MulticastSocket(12345);

            // tham gia vào nhóm multicast
            socket.joinGroup(group);

            while(true){
                // nhận dữ liệu từ nhóm multicast
                byte[] receiveData = new byte[1024];
                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                socket.receive(receivePacket);

                String receiveMessage = new String(receivePacket.getData(), 0, receivePacket.getLength());
                System.out.println("Received: " + receiveMessage);
            }

            // socket.leaveGroup(group);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
