package calculatorRMI.server.cal;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Server {
    public static void main(String[] args) {
        try{
            // Tạo ra 1 thanh ghi ở phía server
            Registry registry = LocateRegistry.createRegistry(1099);

            // Tạo calculator object
            CalculatorInterface stub = new CalculatorImplements();

            // Đăng ký calculator object với thanh ghi
            registry.rebind("CalculatorService", stub);

            while (true){
                System.out.println("server is running...");
                Thread.sleep(5000); // Giữ server chạy liên tục
            }

        }catch (Exception e){
            System.out.println("Server exception: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
