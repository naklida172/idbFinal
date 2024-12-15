package cli;

import dao.AuthorDAO;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;
import model.Author;

public class AuthorCLI {

    private final Scanner scanner;
    private final AuthorDAO authorDAO;

    public AuthorCLI(Connection conn) {
        scanner = new Scanner(System.in);
        authorDAO = new AuthorDAO(conn);
    }

    public void manageAuthors() {
        while (true) {
            System.out.println("\nManage Authors:");
            System.out.println("1. Add Author");
            System.out.println("2. List Authors");
            System.out.println("3. Update Author");
            System.out.println("4. Delete Author");
            System.out.println("5. Back to Main Menu");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> addAuthor();
                case 2 -> listAuthors();
                case 3 -> updateAuthor();
                case 4 -> deleteAuthor();
                case 5 -> {
                    return;
                }
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void addAuthor() {
        System.out.print("Enter author name: ");
        String name = scanner.nextLine();
        System.out.print("Enter author bio: ");
        String bio = scanner.nextLine();
        try {
            Author author = new Author(name, bio);
            authorDAO.createAuthor(author);
            System.out.println("Author added successfully.");
        } catch (SQLException e) {
            System.out.println("Error during the creation");
        }
    }

    private void listAuthors() {
        try {
            List<Author> authors = authorDAO.readAllAuthors();
            for (Author author : authors) {
                System.out.println(author);
            }
        } catch (SQLException e) {
            System.out.println("Error during the reading");
        }
    }

    private void updateAuthor() {
        System.out.print("Enter author ID to update: ");
        int authorId = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter new name: ");
        String name = scanner.nextLine();
        System.out.print("Enter new bio: ");
        String bio = scanner.nextLine();

        Author author = new Author(name, bio);
        try {
            authorDAO.updateAuthor(author, authorId);
            System.out.println("Author updated successfully.");
        } catch (SQLException e) {
            System.out.println("Error during the update");
        }
    }

    private void deleteAuthor() {
        System.out.print("Enter author ID to delete: ");
        int authorId = scanner.nextInt();
        scanner.nextLine();
        try {
            authorDAO.deleteAuthor(authorId);
            System.out.println("Author deleted successfully.");
        } catch (SQLException e) {
            System.out.println("Error during the deletion");
        }
    }
}
