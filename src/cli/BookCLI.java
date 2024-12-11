package cli;

import dao.BookDAO;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;
import model.Book;

public class BookCLI {

    private Scanner scanner;
    private BookDAO bookDAO;

    public BookCLI(Connection conn) {
        scanner = new Scanner(System.in);
        bookDAO = new BookDAO(conn);
    }

    public void manageBooks() {
        while (true) {
            System.out.println("\nManage Books:");
            System.out.println("1. Add Book");
            System.out.println("2. List Books");
            System.out.println("3. Update Book");
            System.out.println("4. Delete Book");
            System.out.println("5. Back to Main Menu");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    addBook();
                    break;
                case 2:
                    listBooks();
                    break;
                case 3:
                    updateBook();
                    break;
                case 4:
                    deleteBook();
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void addBook() {
        System.out.print("Enter book title: ");
        String title = scanner.nextLine();
        System.out.print("Enter book genre: ");
        String genre = scanner.nextLine();
        System.out.print("Enter book price: ");
        double price = scanner.nextDouble();
        scanner.nextLine();

        Book book = new Book(title, genre, price);
        try {
            bookDAO.createBook(book);

            System.out.println("Book added successfully.");
        } catch (SQLException e) {
            System.out.println("Error during the creation");
        }
    }

    private void listBooks() {
        try {
            List<Book> books = bookDAO.readAllBooks();
            for (Book book : books) {
                System.out.println(book);
            }
        } catch (SQLException e) {
            System.out.println("Error during the reading proccess");
        }
    }

    private void updateBook() {
        System.out.print("Enter book ID to update: ");
        int bookId = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter new title: ");
        String title = scanner.nextLine();
        System.out.print("Enter new genre: ");
        String genre = scanner.nextLine();
        System.out.print("Enter new price: ");
        double price = scanner.nextDouble();
        scanner.nextLine();

        Book book = new Book(title, genre, price);
        try {
            bookDAO.updateBook(book, bookId);

            System.out.println("Book updated successfully.");
        } catch (SQLException e) {
            System.out.println("Error during the update");
        }
    }

    private void deleteBook() {
        System.out.print("Enter book ID to delete: ");
        int bookId = scanner.nextInt();
        scanner.nextLine();
        try {
            bookDAO.deleteBook(bookId);
        } catch (SQLException e) {
            System.out.println("Error during the deletion");
        }
        System.out.println("Book deleted successfully.");
    }
}
