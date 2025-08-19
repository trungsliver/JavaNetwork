package nslookup;

import java.net.InetAddress;
import java.util.Scanner;

public class app {
    public static void main(String[] args) {
        // Nhập tên miền từ người dùng
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhập tên miền cần tra cứu: ");
        String domain = sc.nextLine();
        sc.close();

        try {
            // Tra cứu địa chỉ IP từ tên miền
            InetAddress ipAddress = InetAddress.getByName(domain);
            // Lấy địa chỉ IP từ đối tượng InetAddress
            String ip = ipAddress.getHostAddress();
            // Hiển thị kết quả
            System.out.println("IP: "  + ip);
        }catch (Exception e){
            System.out.println("Lỗi: " + e.getMessage());
        }
    }
}
