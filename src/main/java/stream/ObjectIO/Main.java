package stream.ObjectIO;

import java.io.*;
import java.util.Scanner;

public class Main {
    private static Card card;
    private static String defaultFilePath = "src/main/java/stream/ObjectIO/data/";

    public static void main(String[] args) throws FileNotFoundException {
        card = new Card();
        // Tạo swich case lựa chọn chức năng: tạo thẻ, đọc thẻ, ghi thẻ (có thể ghi nhiều thẻ), xóa thẻ (vài lưu thay đổi vào file)
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("==================== OBJECT IO ====================");
            System.out.println("1. Tạo thẻ");
            System.out.println("2. Đọc thẻ");
            System.out.println("3. Ghi thẻ");
            System.out.println("4. Xóa thẻ");
            System.out.println("5. Thoát");
            System.out.println("===================================================");
            System.out.print("Chọn chức năng: ");
            int choice = sc.nextInt();
            sc.nextLine(); // Đọc dòng mới sau khi nhập số
            switch (choice) {
                case 1:
                    createCard(sc);
                    break;
                case 2:
                    readCard();
                    break;
                case 3:
                    writeCard();
                    break;
                case 4:
                    deleteCard();
                    break;
                case 5:
                    System.out.println("Thoát chương trình.");
                    //break;
                    return;
                default:
                    System.out.println("Lựa chọn không hợp lệ. Vui lòng chọn lại.");
            }
        }
    }

    private static void deleteCard() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhập tên file để xóa thẻ: ");
        String path = sc.nextLine();
        path = defaultFilePath + path;
        java.io.File file = new java.io.File(path);
        if (!file.exists()) {
            System.out.println("File không tồn tại. Không thể xóa thẻ.");
            return;
        }
        if (file.delete()) {
            System.out.println("Thẻ đã được xóa thành công!");
        } else {
            System.out.println("Không thể xóa thẻ. Vui lòng kiểm tra lại.");
        }

    }

    // Ghi object vvafo file card.txt (có thể ghi nhiều thẻ)
    // Nếu file card.txt đã tồn tại thì ghi tiếp vào file đó
    private static void writeCard() throws FileNotFoundException {
        // nhập tên file luu thẻ
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhập tên file để lưu thẻ: ");
        String path = sc.nextLine();
        path = defaultFilePath + path;
        // Nếu chưa tồn taị file thì tạo mới
        java.io.File file = new java.io.File(path);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (java.io.IOException e) {
                System.out.println("Lỗi khi tạo file: " + e.getMessage());
                return;
            }
        }
        try {
                FileOutputStream fos = new FileOutputStream(file);
                ObjectOutputStream oos = new ObjectOutputStream(fos);
                // Ghi ối tượng vào dòng tiếp theo
                oos.writeObject(card);
                oos.close();
                fos.close();
                System.out.println("Thẻ đã được ghi vào file thành công!");
        } catch (java.io.IOException e) {
            System.out.println("Lỗi khi ghi thẻ vào file: " + e.getMessage());
        }
    }

    private static void readCard() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhập tên file để đọc thẻ: ");
        String path = sc.nextLine();
        path = defaultFilePath + path;
        java.io.File file = new java.io.File(path);
        if (!file.exists()) {
            System.out.println("File không tồn tại. Vui lòng tạo thẻ trước.");
            return;}
        try (FileInputStream fis = new FileInputStream(file);
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            Card readCard = (Card) ois.readObject();
            System.out.println("Thông tin thẻ:");
            System.out.println("Tên: " + readCard.getName());
            System.out.println("Địa chỉ: " + readCard.getAddress());
            System.out.println("Số điện thoại: " + readCard.getPhone());
            System.out.println("Email: " + readCard.getEmail());
        } catch (FileNotFoundException e) {
            System.out.println("File không tồn tại: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Lỗi khi đọc thẻ từ file: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println("Lỗi khi chuyển đổi đối tượng: " + e.getMessage());
        }
    }

    private static void createCard(Scanner sc) {
        System.out.print("Nhập tên: ");
        String name = sc.nextLine();
        System.out.print("Nhập địa chỉ: ");
        String address = sc.nextLine();
        System.out.print("Nhập số điện thoại: ");
        String phone = sc.nextLine();
        System.out.print("Nhập email: ");
        String email = sc.nextLine();

        card.setName(name);
        card.setAddress(address);
        card.setPhone(phone);
        card.setEmail(email);

        System.out.println("Thẻ đã được tạo thành công!");
    }
}
