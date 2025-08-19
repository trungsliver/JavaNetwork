package multiThreads;

import java.util.ArrayList;
import java.util.Scanner;

public class Library {
    private static ArrayList<Book> books;

    public static void main(String[] args) {
        // Add books to the library
        initBooks();

        // borrow and return are synchronized methods
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("==================== LIBRARY MENU ====================");
            System.out.println("1. View all books");
            System.out.println("2. Add a new book");
            System.out.println("3. Borrow a book");
            System.out.println("4. Return a book");
            System.out.println("5. Exit");
            System.out.println("=======================================================");
            System.out.print("Choose an option: ");
            int choice = sc.nextInt();
            sc.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    viewBooks();
                    break;
                case 2:
                    addBook(sc);
                    break;
                case 3:
                    borrowBook(sc);
                    break;
                case 4:
                    returnBook(sc);
                    break;
                case 5:
                    System.out.println("Exiting the library system. Goodbye!");
                    sc.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static synchronized void returnBook(Scanner sc) {
        System.out.print("Enter book title to return: ");
        String title = sc.nextLine();
        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(title) && !book.isStatus()) {
                book.setStatus(true);
                System.out.println("You have successfully returned: " + book.getTitle());
                return;
            }
        }
        System.out.println("Book not found or not borrowed.");
    }

    private static synchronized void borrowBook(Scanner sc) {
        System.out.print("Enter book title to borrow: ");
        String title = sc.nextLine();
        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(title) && book.isStatus()) {
                book.setStatus(false);
                System.out.println("You have successfully borrowed: " + book.getTitle());
                return;
            }
        }
        System.out.println("Book not found or already borrowed.");
    }

    private static void addBook(Scanner sc) {
        System.out.print("Enter book title: ");
        String title = sc.nextLine();
        System.out.print("Enter book author: ");
        String author = sc.nextLine();
        books.add(new Book(title, author, true));
        System.out.println("Book added successfully!");
    }

    private static void viewBooks() {
        System.out.println("All books in the library:");
        System.out.printf("%-30s %-25s %-10s%n", "Title", "Author", "Status");
        for (Book book : books) {
            System.out.printf("%-30s %-25s %-10s%n",
                    book.getTitle(),
                    book.getAuthor(),
                    book.isStatus());

        }
    }

    private static void initBooks() {
        books = new ArrayList<>();
        books.add(new Book("The Great Gatsby", "F. Scott Fitzgerald", true));
        books.add(new Book("1984", "George Orwell", true));
        books.add(new Book("To Kill a Mockingbird", "Harper Lee", true));
        books.add(new Book("The Catcher in the Rye", "J.D. Salinger", true));
        books.add(new Book("Pride and Prejudice", "Jane Austen", true));
        books.add(new Book("The Lord of the Rings", "J.R.R. Tolkien", true));
        books.add(new Book("The Hobbit", "J.R.R. Tolkien", true));
    }
}
