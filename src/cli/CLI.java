package cli;

import java.sql.Connection;
import java.util.Scanner;

public class CLI {
    private Scanner scanner;
    private Connection conn;

    public CLI(Connection conn) {
        scanner = new Scanner(System.in);
        this.conn=conn;
    }

    public void start() {
        System.out.println("Welcome to the Library Management CLI");
        while (true) {
            System.out.println("\nChoose a table to manage:");
            System.out.println("1. Books");
            System.out.println("2. Authors");
            System.out.println("3. Customers");
            System.out.println("4. Orders");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (choice) {
                case 1:
                    new BookCLI(conn).manageBooks();
                    break;
                case 2:
                    new AuthorCLI(conn).manageAuthors();
                    break;
                case 3:
                    new CustomerCLI(conn).manageCustomers();
                    break;
                case 4:
                    new OrdersCLI(conn).manageOrders();
                    break;
                case 5:
                    System.out.println("Goodbye!");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}