package stream;

import java.io.File;

public class Tree {
    public static void main(String[] args) {
        String defaultPath = "D:/InternBE/JavaNetwork/src/main/java";
        // Nhập đường dẫn thư mục / tập tin
        java.util.Scanner sc = new java.util.Scanner(System.in);
        System.out.print("Nhập đường dẫn thư mục hoặc tập tin: ");
        String path = sc.nextLine();
        path = defaultPath + path.trim();
        sc.close();
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
