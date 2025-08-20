package TCP.socket;

import java.net.Socket;

public class PortScanner {
    public static void main(String[] args) {
        checkPort("localhost");
    }

    public static void checkPort(String urlString){
        int startPort = 1;
        int endPort = 100000;

        System.out.println("Checking port: " + urlString);
        for (int port = startPort; port <= endPort; port++) {
            try {
                Socket socket = new Socket(urlString, port);
                System.out.println("Port " + port + " is open on " + urlString);
                socket.close();
            }catch (Exception e){}
        }
        System.out.println("Port scan completed for " + urlString);
    }
}

// Can use wireShark :))))
