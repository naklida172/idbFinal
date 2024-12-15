package cli;

import dao.BookDAO;
import dao.OrderBookDAO;
import dao.OrdersDAO;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;
import model.OrderBook;

public class OrderBookCLI {

    private final Scanner scanner;
    private final OrderBookDAO orderBookDAO;
    private final OrdersDAO orderDAO;
    private final BookDAO bookDAO;

    public OrderBookCLI(Connection conn) {
        scanner = new Scanner(System.in);
        orderBookDAO = new OrderBookDAO(conn);
        orderDAO = new OrdersDAO(conn);
        bookDAO = new BookDAO(conn);
    }

    public void manageOrderBooks() {
        while (true) {
            System.out.println("\nManage Order Books:");
            System.out.println("1. Add Order-Book Relationship");
            System.out.println("2. List Order-Book Relationships");
            System.out.println("3. Update Order-Book Relationship");
            System.out.println("4. Delete Order-Book Relationship");
            System.out.println("5. Back to Main Menu");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> addOrderBook();
                case 2 -> listOrderBooks();
                case 3 -> updateOrderBook();
                case 4 -> deleteOrderBook();
                case 5 -> {
                    return;
                }
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void addOrderBook() {
        System.out.print("Enter order ID: ");
        int orderId = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter book ID: ");
        int bookId = scanner.nextInt();
        scanner.nextLine();


        try {
            if (!orderDAO.doesOrderExist(orderId)) {
                System.out.println("Order ID does not exist. Please enter a valid order ID.");
                return;
            }
            if (!bookDAO.doesBookExist(bookId)) {
                System.out.println("Book ID does not exist. Please enter a valid book ID.");
                return;
            }
        } catch (SQLException e) {
            System.out.println("Error during validation: " + e.getMessage());
            return;
        }

        OrderBook orderBook = new OrderBook(orderId, bookId);
        try {
            orderBookDAO.createOrderBook(orderBook);
            System.out.println("Order-Book relationship added successfully.");
        } catch (SQLException e) {
            System.out.println("Error during the creation: " + e.getMessage());
        }
    }

    private void listOrderBooks() {
        try {
            List<OrderBook> orderBooks = orderBookDAO.readAllOrderBooks();
            for (OrderBook orderBook : orderBooks) {
                System.out.println(orderBook);
            }
        } catch (SQLException e) {
            System.out.println("Error during the reading process: " + e.getMessage());
        }
    }

    private void updateOrderBook() {
        System.out.print("Enter old order ID: ");
        int oldOrderId = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter old book ID: ");
        int oldBookId = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter new order ID: ");
        int newOrderId = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter new book ID: ");
        int newBookId = scanner.nextInt();
        scanner.nextLine();

        // Check if new order ID and book ID exist
        try {
            if (!orderDAO.doesOrderExist(newOrderId)) {
                System.out.println("Order ID does not exist. Please enter a valid order ID.");
                return;
            }
            if (!bookDAO.doesBookExist(newBookId)) {
                System.out.println("Book ID does not exist. Please enter a valid book ID.");
                return;
            }
        } catch (SQLException e) {
            System.out.println("Error during validation: " + e.getMessage());
            return;
        }

        OrderBook orderBook = new OrderBook(newOrderId, newBookId);
        try {
            orderBookDAO.updateOrderBook(orderBook, oldOrderId, oldBookId);
            System.out.println("Order-Book relationship updated successfully.");
        } catch (SQLException e) {
            System.out.println("Error during the update: " + e.getMessage());
        }
    }

    private void deleteOrderBook() {
        System.out.print("Enter order ID to delete: ");
        int orderId = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter book ID to delete: ");
        int bookId = scanner.nextInt();
        scanner.nextLine();


        try {
            if (!orderDAO.doesOrderExist(orderId)) {
                System.out.println("Order ID does not exist. Please enter a valid order ID.");
                return;
            }
            if (!bookDAO.doesBookExist(bookId)) {
                System.out.println("Book ID does not exist. Please enter a valid book ID.");
                return;
            }
        } catch (SQLException e) {
            System.out.println("Error during validation: " + e.getMessage());
            return;
        }

        try {
            orderBookDAO.deleteOrderBook(orderId, bookId);
            System.out.println("Order-Book relationship deleted successfully.");
        } catch (SQLException e) {
            System.out.println("Error during the deletion: " + e.getMessage());
        }
    }
}
