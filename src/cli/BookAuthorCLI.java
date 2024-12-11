package cli;

import dao.BookAuthorDAO;
import dao.BookDAO;
import dao.AuthorDAO;
import model.BookAuthor;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class BookAuthorCLI {

    private Scanner scanner;
    private BookAuthorDAO bookAuthorDAO;
    private BookDAO bookDAO;
    private AuthorDAO authorDAO;

    public BookAuthorCLI(Connection conn) {
        scanner = new Scanner(System.in);
        bookAuthorDAO = new BookAuthorDAO(conn);
        bookDAO = new BookDAO(conn);
        authorDAO = new AuthorDAO(conn);
    }

    public void manageBookAuthors() {
        while (true) {
            System.out.println("\nManage Book Authors:");
            System.out.println("1. Add Book-Author Relationship");
            System.out.println("2. List Book-Author Relationships");
            System.out.println("3. Update Book-Author Relationship");
            System.out.println("4. Delete Book-Author Relationship");
            System.out.println("5. Back to Main Menu");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    addBookAuthor();
                    break;
                case 2:
                    listBookAuthors();
                    break;
                case 3:
                    updateBookAuthor();
                    break;
                case 4:
                    deleteBookAuthor();
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void addBookAuthor() {
        System.out.print("Enter book ID: ");
        int bookId = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter author ID: ");
        int authorId = scanner.nextInt();
        scanner.nextLine();

        // Check if book ID and author ID exist
        try {
            if (!bookDAO.doesBookExist(bookId)) {
                System.out.println("Book ID does not exist. Please enter a valid book ID.");
                return;
            }
            if (!authorDAO.doesAuthorExist(authorId)) {
                System.out.println("Author ID does not exist. Please enter a valid author ID.");
                return;
            }
        } catch (SQLException e) {
            System.out.println("Error during validation: " + e.getMessage());
            return;
        }

        BookAuthor bookAuthor = new BookAuthor(bookId, authorId);
        try {
            bookAuthorDAO.createBookAuthor(bookAuthor);
            System.out.println("Book-Author relationship added successfully.");
        } catch (SQLException e) {
            System.out.println("Error during the creation: " + e.getMessage());
        }
    }

    private void listBookAuthors() {
        try {
            List<BookAuthor> bookAuthors = bookAuthorDAO.readAllBookAuthors();
            for (BookAuthor bookAuthor : bookAuthors) {
                System.out.println(bookAuthor);
            }
        } catch (SQLException e) {
            System.out.println("Error during the reading process: " + e.getMessage());
        }
    }

    private void updateBookAuthor() {
        System.out.print("Enter old book ID: ");
        int oldBookId = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter old author ID: ");
        int oldAuthorId = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter new book ID: ");
        int newBookId = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter new author ID: ");
        int newAuthorId = scanner.nextInt();
        scanner.nextLine();

        try {
            if (!bookDAO.doesBookExist(newBookId)) {
                System.out.println("Book ID does not exist. Please enter a valid book ID.");
                return;
            }
            if (!authorDAO.doesAuthorExist(newAuthorId)) {
                System.out.println("Author ID does not exist. Please enter a valid author ID.");
                return;
            }
        } catch (SQLException e) {
            System.out.println("Error during validation: " + e.getMessage());
            return;
        }

        BookAuthor bookAuthor = new BookAuthor(newBookId, newAuthorId);
        try {
            bookAuthorDAO.updateBookAuthor(bookAuthor, oldBookId, oldAuthorId);
            System.out.println("Book-Author relationship updated successfully.");
        } catch (SQLException e) {
            System.out.println("Error during the update: " + e.getMessage());
        }
    }

    private void deleteBookAuthor() {
        System.out.print("Enter book ID to delete: ");
        int bookId = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter author ID to delete: ");
        int authorId = scanner.nextInt();
        scanner.nextLine();

        try {
            if (!bookDAO.doesBookExist(bookId)) {
                System.out.println("Book ID does not exist. Please enter a valid book ID.");
                return;
            }
            if (!authorDAO.doesAuthorExist(authorId)) {
                System.out.println("Author ID does not exist. Please enter a valid author ID.");
                return;
            }
        } catch (SQLException e) {
            System.out.println("Error during validation: " + e.getMessage());
            return;
        }

        try {
            bookAuthorDAO.deleteBookAuthor(bookId, authorId);
            System.out.println("Book-Author relationship deleted successfully.");
        } catch (SQLException e) {
            System.out.println("Error during the deletion: " + e.getMessage());
        }
    }
}
