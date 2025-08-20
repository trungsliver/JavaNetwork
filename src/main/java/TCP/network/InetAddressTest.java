package TCP.network;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class InetAddressTest {
    public static void main(String[] args) throws UnknownHostException {
        // Get the IP address of a domain name
        String domain = "www.google.com";
        InetAddress inetAddress = InetAddress.getByName(domain);

        System.out.println("Host Name: " + domain);
        System.out.println("IP Address: " + inetAddress.getHostAddress());

        // Get the local host address
        InetAddress localHost = InetAddress.getLocalHost();
        System.out.println("\nLocal Host Name: " + localHost.getHostName());
        System.out.println("Local Host IP Address: " + localHost.getHostAddress());
    }
}
