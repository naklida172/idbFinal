package cli;

import java.sql.Connection;
import java.util.Scanner;

public class CLI {

    private final Scanner scanner;
    private final Connection conn;

    public CLI(Connection conn) {
        scanner = new Scanner(System.in);
        this.conn = conn;
    }

    public void start() {
        System.out.println("Welcome to the Library Management CLI");
        while (true) {
            System.out.println("\nChoose a table to manage:");
            System.out.println("1. Books");
            System.out.println("2. Authors");
            System.out.println("3. Author to book relation");
            System.out.println("4. Customers");
            System.out.println("5. Orders");
            System.out.println("6. Order to book relation");
            System.out.println("7. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> new BookCLI(conn).manageBooks();
                case 2 -> new AuthorCLI(conn).manageAuthors();
                case 3 -> new BookAuthorCLI(conn).manageBookAuthors();
                case 4 -> new CustomerCLI(conn).manageCustomers();
                case 5 -> new OrdersCLI(conn).manageOrders();
                case 6 -> new OrderBookCLI(conn).manageOrderBooks();
                case 7 -> {
                    System.out.println("Goodbye!");
                    return;
                }
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
