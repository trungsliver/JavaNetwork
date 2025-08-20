package UDP.simpleDNS.server;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class DnsServer {
    public static void main(String[] args) {
        try{
            DatagramSocket socket = new DatagramSocket(9876);
            System.out.println("DNS Server is running on port 9876 ...");

            // Giả lập 1 bảng định danh DNS
            String[][] dnsTable = {
                {"www.example.com", "192.168.1.100"},
                {"www.google.com", "8.8.8.8}"},
                {"www.facebook.com", "31.13.65.36"},
                {"www.github.com", "22.22.22.22"},
                {"www.microsoft.com", "78.78.78.78"}
            };

            while (true){
                // Nhận dữ liệu từ client
                byte[] reciveData = new byte[1024];
                DatagramPacket recivePacket = new DatagramPacket(reciveData, reciveData.length);

                // Nhận yêu cầu từ client
                socket.receive(recivePacket);

                // Truy vấn IP từ domain
                String domainName = new String(recivePacket.getData(), 0, recivePacket.getLength());
                domainName = domainName.trim();
                domainName = domainName.toLowerCase();
                String response = "Not found";

                // Tìm kiếm trong bảng DNS
                for (String[] entry : dnsTable) {
                    if (entry[0].equalsIgnoreCase(domainName)) {
                        response = entry[1];
                        break;
                    }
                }

                // Phản hồi
                    // clientAddress là địa chỉ IP của client
                InetAddress clientAddress = recivePacket.getAddress();
                    // clientPort là cổng của client
                int clientPort = recivePacket.getPort();
                byte[] responseData = response.getBytes();
                DatagramPacket sendPacket = new DatagramPacket(responseData, responseData.length, clientAddress, clientPort);
                socket.send(sendPacket);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
