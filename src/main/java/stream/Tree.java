package stream;

import java.io.File;
import java.util.Scanner;

//import static jdk.jpackage.internal.IOUtils.copyFile;

public class Tree {

    public static void main(String[] args) {
        String defaultPath = "D:/InternBE/JavaNetwork/src/main/java";
        // Nhập đường dẫn thư mục / tập tin
        java.util.Scanner sc = new java.util.Scanner(System.in);
        System.out.print("Nhập đường dẫn thư mục hoặc tập tin: ");
        String path = sc.nextLine();
        path = defaultPath + path.trim();
//        sc.close();
        System.out.println("Đường dẫn đã nhập: " + path);

        // Kiểm tra xem đường dẫn có hợp lệ không
        if (path.isEmpty()) {
            System.out.println("Đường dẫn không được để trống.");
            return;
        }

        // Nếu là thư mục thì hiển thị cây thư mục con
        java.io.File file = new java.io.File(path);
        if (file.isDirectory()) {
            System.out.println("Đây là một thư mục. Hiển thị cây thư mục con");
            displayDirectoryTree(file, 0);
        } else if (file.isFile()) {
            System.out.println("Đây là một tập tin.");
            System.out.println("Tên tập tin: " + file.getName());
            System.out.println("Đường dẫn tập tin: " + file.getAbsolutePath());
        } else {
            System.out.println("Đường dẫn không hợp lệ hoặc không tồn tại.");
        }

        // Cho phép thực hiện: đổi tên, xóa folder/file (xóa hết), copy (nhập đường dẫn đích), di chuyển (nhập đường dẫn đích)
        // Làm switch case tất cả các cức năng
//        Scanner sc = new Scanner(System.in);
        System.out.println("Các chức năng khác: đổi tên, xóa folder/file, copy, di chuyển.");
        System.out.print("Nhập chức năng bạn muốn thực hiện (1: đổi tên, 2: xóa, 3: copy, 4: di chuyển): ");
        int choice = sc.nextInt();
        sc.nextLine(); // Đọc dòng mới sau khi nhập số
        switch (choice) {
            case 0: // Hiển thị cây thư mục con
                System.out.println("Hiển thị cây thư mục con:");
                displayDirectoryTree(file, 0);
                break;
            case 1: // Đổi tên
                renameFile(file, sc);
                break;
            case 2: // Xóa
                deleteFile(file, sc);
                break;
            case 3: // Copy
               copyFile(file, sc);
                break;
            case 4: // Di chuyển
                moveFile(file, sc);
                break;
            default:
                System.out.println("Chức năng không hợp lệ.");
        }


    }

    private static void moveFile(File file, Scanner sc) {
        if (!file.exists()) {
            System.out.println("Tập tin hoặc thư mục không tồn tại.");
            return;
        }
        System.out.println("Tập tin hoặc thư mục hiện tại: " + file.getAbsolutePath());
        // Nhập đường dẫn đích
        System.out.print("Nhập đường dẫn đích để di chuyển: ");
        String destPath = sc.nextLine();
        destPath = "D:/InternBE/JavaNetwork/src/main/java" + destPath.trim();
        File destFile = new File(destPath, file.getName());
        // Thực hiện di chuyển
        try {
            if (file.isDirectory()) {
                // Nếu là thư mục, di chuyển toàn bộ nội dung
                copyDirectory(file, destFile);
            } else {
                // Nếu là tập tin, di chuyển tập tin
                java.nio.file.Files.move(file.toPath(), destFile.toPath());
            }
            System.out.println("Di chuyển thành công: " + destFile.getAbsolutePath());
            // Xóa tập tin gốc sau khi di chuyển
            if (file.delete()) {
                System.out.println("Đã xóa tập tin gốc: " + file.getAbsolutePath());
            } else {
                System.out.println("Không thể xóa tập tin gốc.");
            }
        } catch (Exception e) {
            System.out.println("Di chuyển không thành công: " + e.getMessage());
        }
    }

    private static void copyFile(File file, Scanner sc) {
        if (!file.exists()) {
            System.out.println("Tập tin hoặc thư mục không tồn tại.");
            return;
        }
        System.out.println("Tập tin hoặc thư mục hiện tại: " + file.getAbsolutePath());
        // Nhập đường dẫn đích
        System.out.print("Nhập đường dẫn đích để sao chép: ");
        String destPath = sc.nextLine();
        destPath = "D:/InternBE/JavaNetwork/src/main/java" + destPath.trim();
        File destFile = new File(destPath, file.getName());
        // Thực hiện sao chép
        try {
            if (file.isDirectory()) {
                // Nếu là thư mục, sao chép toàn bộ nội dung
                copyDirectory(file, destFile);
            } else {
                // Nếu là tập tin, sao chép tập tin
                java.nio.file.Files.copy(file.toPath(), destFile.toPath());
            }
            System.out.println("Sao chép thành công: " + destFile.getAbsolutePath());
        } catch (Exception e) {
            System.out.println("Sao chép không thành công: " + e.getMessage());
        }
    }

    private static void copyDirectory(File file, File destFile) {
        if (!destFile.exists()) {
            destFile.mkdirs(); // Tạo thư mục đích nếu chưa tồn tại
        }
        File[] files = file.listFiles();
        if (files != null) {
            for (File f : files) {
                File newFile = new File(destFile, f.getName());
                if (f.isDirectory()) {
                    copyDirectory(f, newFile); // Gọi đệ quy để sao chép thư mục con
                } else {
                    try {
                        java.nio.file.Files.copy(f.toPath(), newFile.toPath());
                    } catch (Exception e) {
                        System.out.println("Sao chép tập tin " + f.getName() + " không thành công: " + e.getMessage());
                    }
                }
            }
        }
    }

    private static void deleteFile(File file, Scanner sc) {
        if (!file.exists()) {
            System.out.println("Tập tin hoặc thư mục không tồn tại.");
            return;
        }
        System.out.println("Tập tin hoặc thư mục hiện tại: " + file.getAbsolutePath());
        // Xác nhận xóa
        System.out.print("Bạn có chắc chắn muốn xóa tập tin hoặc thư mục này? (y/n): ");
        String confirm = sc.nextLine();
        if (!confirm.equalsIgnoreCase("y")) {
            System.out.println("Hủy bỏ xóa.");
            return;
        }
        // Thực hiện xóa
        if (file.isDirectory()) {
            // Nếu là thư mục, xóa tất cả nội dung bên trong
            File[] files = file.listFiles();
            if (files != null) {
                for (File f : files) {
                    deleteFile(f, sc); // Gọi đệ quy để xóa từng tập tin/thư mục con
                }
            }
        }
        if (file.delete()) {
            System.out.println("Xóa thành công: " + file.getAbsolutePath());
        } else {
            System.out.println("Xóa không thành công.");
        }
        // Nếu là tập tin, xóa trực tiếp
        if (file.isFile()) {
            if (file.delete()) {
                System.out.println("Xóa thành công: " + file.getAbsolutePath());
            } else {
                System.out.println("Xóa không thành công.");
            }
        }
    }

    private static void renameFile(File file, Scanner sc) {
        if (!file.exists()) {
            System.out.println("Tập tin hoặc thư mục không tồn tại.");
            return;
        }
        System.out.println("Tập tin hoặc thư mục hiện tại: " + file.getAbsolutePath());
        // Nhập tên mới
        System.out.print("Nhập tên mới cho tập tin hoặc thư mục: ");
        String newName = sc.nextLine();
        if (newName.trim().isEmpty()) {
            System.out.println("Tên mới không được để trống.");
            return;
        }
        // Tạo đối tượng File mới với tên mới
        File newFile = new File(file.getParent(), newName);
        // Thực hiện đổi tên
        if (file.renameTo(newFile)) {
            System.out.println("Đổi tên thành công: " + newFile.getAbsolutePath());
        } else {
            System.out.println("Đổi tên không thành công.");
        }
    }

    // Hiển thị cây thư mục con
    private static void displayDirectoryTree(File file, int i) {
        // Hiển thị tên thư mục hoặc tập tin
        // i là cấp độ của thư mục trong cây, dùng để in lùi
        for (int j = 0; j < i; j++) {
            System.out.print("  ");
        }
        System.out.println("|-- " + file.getName());

        // Nếu là thư mục, hiển thị các thư mục con và tập tin bên trong
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            if (files != null) {
                for (File f : files) {
                    displayDirectoryTree(f, i + 1);
                }
            }
        }
    }
}
