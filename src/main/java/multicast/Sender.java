package multicast;

import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class Sender {
    public static void main(String[] args) {
        try{
            InetAddress group = InetAddress.getByName("230.0.0.1");
            MulticastSocket socket = new MulticastSocket();

            String message = "Hello, from sender!";
            byte[] sendData = message.getBytes();

            while (true){
                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, group, 9876);
                socket.send(sendPacket);
                Thread.sleep(1000);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
