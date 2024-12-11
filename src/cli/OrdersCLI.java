package cli;

import dao.CustomerDAO;
import dao.OrdersDAO;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;
import model.Orders;

public class OrdersCLI {

    private Scanner scanner;
    private OrdersDAO orderDAO;
    private CustomerDAO customerDAO;

    public OrdersCLI(Connection conn) {
        scanner = new Scanner(System.in);
        orderDAO = new OrdersDAO(conn);
        customerDAO = new CustomerDAO(conn);
    }

    public void manageOrders() {
        while (true) {
            System.out.println("\nManage Orders:");
            System.out.println("1. Add Order");
            System.out.println("2. List Orders");
            System.out.println("3. Update Order");
            System.out.println("4. Delete Order");
            System.out.println("5. Back to Main Menu");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (choice) {
                case 1:
                    addOrder();
                    break;
                case 2:
                    listOrders();
                    break;
                case 3:
                    updateOrder();
                    break;
                case 4:
                    deleteOrder();
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void addOrder() {
        System.out.print("Enter customer ID: ");
        int customerId = scanner.nextInt();
        scanner.nextLine();  // Consume newline

        // Check if customer ID exists
        try {
            if (!customerDAO.doesCustomerExist(customerId)) {
                System.out.println("Customer ID does not exist. Please enter a valid customer ID.");
                return;
            }
        } catch (SQLException e) {
            System.out.println("Error during customer ID validation: " + e.getMessage());
            return;
        }

        System.out.print("Enter order date (YYYY-MM-DD): ");
        String orderDate = scanner.nextLine();
        System.out.print("Enter total amount: ");
        double totalAmount = scanner.nextDouble();
        scanner.nextLine();  // Consume newline

        Orders order = new Orders(customerId, java.sql.Date.valueOf(orderDate), totalAmount);
        try {
            orderDAO.createOrder(order);
            System.out.println("Order added successfully.");
        } catch (SQLException e) {
            System.out.println("Error during the creation: " + e.getMessage());
        }
    }

    private void listOrders() {
        try {
            List<Orders> orders = orderDAO.readAllOrders();
            for (Orders order : orders) {
                System.out.println(order);
            }
        } catch (SQLException e) {
            System.out.println("Error during the reading process: " + e.getMessage());
        }
    }

    private void updateOrder() {
        System.out.print("Enter order ID to update: ");
        int orderId = scanner.nextInt();
        scanner.nextLine();  // Consume newline

        System.out.print("Enter new customer ID: ");
        int customerId = scanner.nextInt();
        scanner.nextLine();  // Consume newline

        // Check if new customer ID exists
        try {
            if (!customerDAO.doesCustomerExist(customerId)) {
                System.out.println("Customer ID does not exist. Please enter a valid customer ID.");
                return;
            }
        } catch (SQLException e) {
            System.out.println("Error during customer ID validation: " + e.getMessage());
            return;
        }

        System.out.print("Enter new order date (YYYY-MM-DD): ");
        String orderDate = scanner.nextLine();
        System.out.print("Enter new total amount: ");
        double totalAmount = scanner.nextDouble();
        scanner.nextLine();  // Consume newline

        Orders order = new Orders(customerId, java.sql.Date.valueOf(orderDate), totalAmount);
        try {
            orderDAO.updateOrder(order, orderId);
            System.out.println("Order updated successfully.");
        } catch (SQLException e) {
            System.out.println("Error during the update: " + e.getMessage());
        }
    }

    private void deleteOrder() {
        System.out.print("Enter order ID to delete: ");
        int orderId = scanner.nextInt();
        scanner.nextLine();  // Consume newline
        try {
            orderDAO.deleteOrder(orderId);
            System.out.println("Order deleted successfully.");
        } catch (SQLException e) {
            System.out.println("Error during the deletion: " + e.getMessage());
        }
    }
}
