package multicastLightStick;

import javax.swing.*;
import java.awt.*;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class LightControllerClient {
    public static void main(String[] args) {
//        SwingUtilities.invokeLater(() -> {
            LightControllerGUI gui = new LightControllerGUI();
            gui.createAndShowGUI();
//        });
    }
}

class LightControllerGUI{
    private JLabel lightLabel;
    private JPanel mainPanel;

    public void createAndShowGUI() {
        JFrame frame = new JFrame("Light Controller");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        lightLabel = new JLabel("Light status: ");
        lightLabel.setHorizontalAlignment(JLabel.CENTER);
        mainPanel.add(lightLabel,BorderLayout.CENTER);

        frame.setMinimumSize(new Dimension(300, 200));
        frame.getContentPane().add(mainPanel);
        frame.pack();
        frame.setVisible(true);

        startListening();
    }

    private void startListening(){
        try{
            InetAddress group = InetAddress.getByName("230.0.0.3");
            MulticastSocket socket = new MulticastSocket(9876);
            socket.joinGroup(group);

            while (true){
                byte[] receiverData = new byte[1024];
                DatagramPacket receivePacket = new DatagramPacket(receiverData, receiverData.length);
                socket.receive(receivePacket);

                String receivedMessage = new String(receivePacket.getData(), 0, receivePacket.getLength());
                System.out.println("Received message: " + receivedMessage);
                updateLightStatus(receivedMessage);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void updateLightStatus(String message){
//        SwingUtilities.invokeLater(() -> {
            lightLabel.setText("Light status: " + message);
            switch (message.toLowerCase()) {
                case "red":
                    mainPanel.setBackground(Color.RED);
                    break;
                case "green":
                    mainPanel.setBackground(Color.GREEN);
                    break;
                case "blue":
                    mainPanel.setBackground(Color.BLUE);
                    break;
                case "yellow":
                    mainPanel.setBackground(Color.YELLOW);
                    break;
                case "purple":
                    mainPanel.setBackground(new Color(128, 0, 128));
                    break;
                case "cyan":
                    mainPanel.setBackground(Color.CYAN);
                    break;
                default:
                    mainPanel.setBackground(Color.WHITE);
            }
            lightLabel.setText("Light status: " + message);
//        });
    }
}