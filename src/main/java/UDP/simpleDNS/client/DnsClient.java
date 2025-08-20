package UDP.simpleDNS.client;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class DnsClient {
    public static void main(String[] args) {
        try {
            DatagramSocket socket = new DatagramSocket();

            InetAddress serverAddress = InetAddress.getByName("localhost");
            int serverPort = 9876;

            Scanner sc = new Scanner(System.in);
            while (true) {
                System.out.println("\n===============================");
                System.out.println("Enter domain name to resolve: ");
                String domainName = sc.nextLine().trim();

                // Gửi truy vấn đến DNS server
                byte[] sendData = domainName.getBytes();
                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, serverAddress, serverPort);
                socket.send(sendPacket);

                // Nhận phản hồi từ DNS server
                byte[] receiveData = new byte[1024];
                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                socket.receive(receivePacket);
                String response = new String(receivePacket.getData(), 0, receivePacket.getLength());
                if (response.equals("Not found")) {
                    System.out.println("Domain not found.");
                } else {
                    System.out.println("IP address for " + domainName + ": " + response);
                }
                System.out.println("===============================");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
