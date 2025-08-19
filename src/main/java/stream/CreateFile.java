package stream;

import javax.swing.*;
import java.io.File;

public class CreateFile {
    public static void main(String[] args) {
        // Nhập folder & file từ người dùng
        java.util.Scanner sc = new java.util.Scanner(System.in);
        System.out.print("Nhập tên folder cần tạo: ");
        String folderName = sc.nextLine();
        System.out.println("Nhập tên file cần tạo: ");
        String fileName = sc.nextLine();
        sc.close();

        // Tạo folder
        java.io.File folder = new java.io.File(folderName);
        if (!folder.exists()) {
            boolean folderCreated = folder.mkdir();
            if (folderCreated) {
                System.out.println("Folder '" + folderName + "' đã được tạo.");
            } else {
                System.out.println("Không thể tạo folder '" + folderName + "'.");
                return;
            }
        } else {
            System.out.println("Folder '" + folderName + "' đã tồn tại.");
        }
        //Chọn folder để tạo file
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
//        int result = fileChooser.showOpenDialog(null);
//        if (result == JFileChooser.APPROVE_OPTION) {
//            String path = fileChooser.getSelectedFile().getAbsolutePath();
//            System.out.println("Đường dẫn đã chọn: " + path);
//        }
        // Lấy đường dẫn thư mục vừa tạo
        String folderPath = folder.getAbsolutePath();
        System.out.println("Đường dẫn thư mục đã tạo: " + folderPath);

        // Tạo file trong folder đã chọn
        String pathFile = folderPath + File.separator + fileName;
        System.out.println("Đường dẫn file sẽ tạo: " + pathFile);
        File myFile = new File(pathFile);

        if ((fileName.trim().length() == 0)) {
            System.out.println("Tên file không được để trống.");
            return;
        }
        // Kiểm tra file tồn tại
        if(myFile.exists()) {
            System.out.println("File '" + fileName + "' đã tồn tại trong thư mục '" + folderName + "'.");
        } else {
            try {
                // Tạo file mới
                boolean fileCreated = myFile.createNewFile();
                if (fileCreated) {
                    System.out.println("File '" + fileName + "' đã được tạo trong thư mục '" + folderName + "'.");
                } else {
                    System.out.println("Không thể tạo file '" + fileName + "' trong thư mục '" + folderName + "'.");
                }
            } catch (Exception e) {
                System.out.println("Lỗi khi tạo file: " + e.getMessage());
            }
        }
    }
}
