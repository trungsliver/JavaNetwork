package stream;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class FileIO {
    private static File logFile;

    public static void main(String[] args) throws IOException {
        String defaultPath = "D:/InternBE/JavaNetwork/src/main/java";
        openLogFile();

        java.util.Scanner sc = new java.util.Scanner(System.in);
        while (true) {
            System.out.println("===================== FILE IO =====================");
            System.out.println("0. Mở file/folder");
            System.out.println("1. Tạo file/folder");
            System.out.println("2. Đổi tên file/folder");
            System.out.println("3. Xóa file/folder");
            System.out.println("4. Copy file/folder");
            System.out.println("5. Di chuyển file/folder");
            System.out.println("6. Đọc nội dung file");
            System.out.println("7. Ghi nội dung vào file");
            System.out.println("8. Thoát");
            System.out.println("=====================================================");
            System.out.print("Nhập lựa chọn của bạn (0-8): ");
            int choice = sc.nextInt();
            sc.nextLine(); // Đọc dòng mới sau khi nhập số
            switch (choice) {
                case 0:
                    openFileOrFolder(sc);
                    break;
                case 1:
                    createFileOrFolder(sc);
                    break;
                case 2:
                    renameFileOrFolder(sc);
                    break;
                case 3:
                    deleteFileOrFolder(sc);
                    break;
                case 4:
                    copyFileOrFolder(sc);
                    break;
                case 5:
                    moveFileOrFolder(sc);
                    break;
                case 6:
                    readFileContent(sc);
                    break;
                case 7:
                    writeFileContent(sc);
                    break;
                case 8:
                    System.out.println("Thoát chương trình.");
                    return;
                default:
                    System.out.println("Lựa chọn không hợp lệ. Vui lòng chọn lại.");
            }
        }
    }

    private static void createFileOrFolder(Scanner sc) throws IOException {
        System.out.print("Nhập đường dẫn file hoặc thư mục cần tạo: ");
        String path = sc.nextLine();
        path = "D:/InternBE/JavaNetwork/src/main/java" + path.trim();

        // Kiểm tra xem file hoặc thư mục đã tồn tại chưa
        java.io.File file = new java.io.File(path);
        if (file.exists()) {
            System.out.println("File hoặc thư mục đã tồn tại.");
            return;
        }

        // Tạo file hoặc thư mục
        boolean created = false;
        if (file.isDirectory()) {
            created = file.mkdirs(); // Tạo thư mục
        } else {
            try {
                created = file.createNewFile(); // Tạo file
            } catch (java.io.IOException e) {
                System.out.println("Lỗi khi tạo file: " + e.getMessage());
            }
        }

        if (created) {
            System.out.println("Tạo thành công.");
            writeLog("Tạo thành công: " + path);
        } else {
            System.out.println("Tạo không thành công. Vui lòng kiểm tra lại.");
            writeLog("Tạo không thành công: " + path);
        }
        return;
    }

    private static void writeFileContent(Scanner sc) throws IOException {
        System.out.print("Nhập đường dẫn file cần ghi: ");
        String path = sc.nextLine();
        path = "D:/InternBE/JavaNetwork/src/main/java" + path.trim();

        // Kiểm tra xem file có tồn tại không
        java.io.File file = new java.io.File(path);
        if (!file.exists() || !file.isFile()) {
            System.out.println("File không tồn tại hoặc không phải là một tập tin.");
            return;
        }

        // Nhập nội dung cần ghi vào file
        System.out.print("Nhập nội dung cần ghi vào file: ");
        String content = sc.nextLine();

        // Ghi nội dung vào file
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file, true))) {
            bw.write(content);
            bw.newLine(); // Thêm dòng mới sau khi ghi
            System.out.println("Ghi nội dung thành công.");
            writeLog("Ghi nội dung thành công: " + file.getAbsolutePath());
        } catch (IOException e) {
            System.out.println("Lỗi khi ghi file: " + e.getMessage());
            writeLog("Lỗi khi ghi file: " + file.getAbsolutePath() + " - " + e.getMessage());
        }
        return;
    }

    private static void readFileContent(Scanner sc) throws IOException {
        System.out.print("Nhập đường dẫn file cần đọc: ");
        String path = sc.nextLine();
        path = "D:/InternBE/JavaNetwork/src/main/java" + path.trim();

        // Kiểm tra xem file có tồn tại không
        java.io.File file = new java.io.File(path);
        if (!file.exists() || !file.isFile()) {
            System.out.println("File không tồn tại hoặc không phải là một tập tin.");
            return;
        }

        // Đọc nội dung file
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
            writeLog("Đọc nội dung thành công: " + file.getAbsolutePath());
        } catch (IOException e) {
            System.out.println("Lỗi khi đọc file: " + e.getMessage());
            writeLog("Lỗi khi đọc file: " + file.getAbsolutePath() + " - " + e.getMessage());
        }
        return;
    }

    private static void moveFileOrFolder(Scanner sc) throws IOException {
        System.out.print("Nhập đường dẫn file hoặc thư mục cần di chuyển: ");
        String sourcePath = sc.nextLine();
        sourcePath = "D:/InternBE/JavaNetwork/src/main/java" + sourcePath.trim();

        // Kiểm tra xem file hoặc thư mục có tồn tại không
        java.io.File sourceFile = new java.io.File(sourcePath);
        if (!sourceFile.exists()) {
            System.out.println("File hoặc thư mục không tồn tại.");
            return;
        }

        // Nhập đường dẫn đích
        System.out.print("Nhập đường dẫn đích để di chuyển: ");
        String destinationPath = sc.nextLine();
        destinationPath = "D:/InternBE/JavaNetwork/src/main/java" + destinationPath.trim();

        java.io.File destinationFile = new java.io.File(destinationPath);
        if (destinationFile.isDirectory()) {
            // Nếu đích là thư mục, tạo file mới trong thư mục đó
            destinationFile = new java.io.File(destinationFile, sourceFile.getName());
        }

        try {
            java.nio.file.Files.move(sourceFile.toPath(), destinationFile.toPath());
            System.out.println("Di chuyển thành công.");
            writeLog("Di chuyển thành công: " + sourceFile.getAbsolutePath() + " -> " + destinationFile.getAbsolutePath());
        } catch (java.io.IOException e) {
            System.out.println("Di chuyển không thành công: " + e.getMessage());
            writeLog("Di chuyển không thành công: " + sourceFile.getAbsolutePath() + " -> " + destinationFile.getAbsolutePath());
        }
    }

    private static void copyFileOrFolder(Scanner sc) throws IOException {
        System.out.print("Nhập đường dẫn file hoặc thư mục cần copy: ");
        String sourcePath = sc.nextLine();
        sourcePath = "D:/InternBE/JavaNetwork/src/main/java" + sourcePath.trim();

        // Kiểm tra xem file hoặc thư mục có tồn tại không
        java.io.File sourceFile = new java.io.File(sourcePath);
        if (!sourceFile.exists()) {
            System.out.println("File hoặc thư mục không tồn tại.");
            return;
        }

        // Nhập đường dẫn đích
        System.out.print("Nhập đường dẫn đích để copy: ");
        String destinationPath = sc.nextLine();
        destinationPath = "D:/InternBE/JavaNetwork/src/main/java" + destinationPath.trim();

        java.io.File destinationFile = new java.io.File(destinationPath);
        if (destinationFile.isDirectory()) {
            // Nếu đích là thư mục, tạo file mới trong thư mục đó
            destinationFile = new java.io.File(destinationFile, sourceFile.getName());
        }

        try {
            java.nio.file.Files.copy(sourceFile.toPath(), destinationFile.toPath());
            System.out.println("Copy thành công.");
            writeLog("Copy thành công: " + sourceFile.getAbsolutePath() + " -> " + destinationFile.getAbsolutePath());
        } catch (java.io.IOException e) {
            System.out.println("Copy không thành công: " + e.getMessage());
            writeLog("Copy không thành công: " + sourceFile.getAbsolutePath() + " -> " + destinationFile.getAbsolutePath());
        }
    }

    private static void deleteFileOrFolder(Scanner sc) throws IOException {
        System.out.print("Nhập đường dẫn file hoặc thư mục cần xóa: ");
        String path = sc.nextLine();
        path = "D:/InternBE/JavaNetwork/src/main/java" + path.trim();

        // Kiểm tra xem file hoặc thư mục có tồn tại không
        java.io.File file = new java.io.File(path);
        if (!file.exists()) {
            System.out.println("File hoặc thư mục không tồn tại.");
            return;
        }

        // Xóa file hoặc thư mục
        boolean deleted = file.delete();
        if (deleted) {
            System.out.println("Xóa thành công.");
            writeLog("Xóa thành công: " + file.getAbsolutePath());
        } else {
            System.out.println("Xóa không thành công. Vui lòng kiểm tra lại.");
            writeLog("Xóa không thành công: " + file.getAbsolutePath());
        }
        return;
    }

    private static void renameFileOrFolder(Scanner sc) throws IOException {
        System.out.print("Nhập đường dẫn file hoặc thư mục cần đổi tên: ");
        String path = sc.nextLine();
        path = "D:/InternBE/JavaNetwork/src/main/java" + path.trim();


        // Kiểm tra xem file hoặc thư mục có tồn tại không
        java.io.File file = new java.io.File(path);
        if (!file.exists()) {
            System.out.println("File hoặc thư mục không tồn tại.");
            return;
        }
        // Nhập tên mới
        System.out.print("Nhập tên mới: ");
        String newName = sc.nextLine().trim();
        if (newName.isEmpty()) {
            System.out.println("Tên mới không được để trống.");
            return;
        }
        // Tạo đường dẫn mới
        String newPath = file.getParent() + File.separator + newName;
        // Đổi tên file hoặc thư mục
        boolean renamed = file.renameTo(new java.io.File(newPath));
        if (renamed) {
            System.out.println("Đổi tên thành công.");
            writeLog("Đổi tên thành công: " + file.getAbsolutePath() + " -> " + newPath);

            return;
        } else {
            System.out.println("Đổi tên không thành công. Vui lòng kiểm tra lại.");
            writeLog("Đổi tên không thành công: " + file.getAbsolutePath() + " -> " + newPath);
            return;
        }

    }

    private static void openFileOrFolder(Scanner sc) {
        System.out.print("Nhập đường dẫn file hoặc thư mục: ");
        String path = sc.nextLine();
        path = "D:/InternBE/JavaNetwork/src/main/java" + path.trim();

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

    // Mở file log (tạo mới nếu không tồn tại)
    private static void openLogFile() {
        if (logFile == null) {
            logFile = new File("D:/InternBE/JavaNetwork/src/main/java/stream/file_log.log");
        }
        if (!logFile.exists()) {
            try {
                logFile.createNewFile();
            } catch (Exception e) {
                System.out.println("Không thể tạo file log: " + e.getMessage());
            }
        }
    }

    // Ghi log vào file
    private static void writeLog(String message) throws IOException {
        FileOutputStream fos = new FileOutputStream(logFile, true);
        OutputStreamWriter osw = new OutputStreamWriter(fos, StandardCharsets.UTF_8);
        BufferedWriter bw = new BufferedWriter(osw);

        // Lấy thời gian hiện tại
        LocalDateTime currentTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        String time = currentTime.format(formatter);
        String user = "USER";
        // Ghi log với định dạng: "dd/MM/yyyy HH:mm:ss - USER: message"
        bw.append(time + " - " + user + ": " + message);
        // Thêm dòng mới
        bw.newLine();
        // Đóng các luồng
        bw.flush();
        // Đóng BufferedWriter
        bw.close();
    }
}
