package cli;

import dao.CustomerDAO;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;
import model.Customer;

public class CustomerCLI {

    private Scanner scanner;
    private CustomerDAO customerDAO;

    public CustomerCLI(Connection conn) {
        scanner = new Scanner(System.in);
        customerDAO = new CustomerDAO(conn);
    }

    public void manageCustomers() {
        while (true) {
            System.out.println("\nManage Customers:");
            System.out.println("1. Add Customer");
            System.out.println("2. List Customers");
            System.out.println("3. Update Customer");
            System.out.println("4. Delete Customer");
            System.out.println("5. Back to Main Menu");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    addCustomer();
                    break;
                case 2:
                    listCustomers();
                    break;
                case 3:
                    updateCustomer();
                    break;
                case 4:
                    deleteCustomer();
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void addCustomer() {
        System.out.print("Enter customer name: ");
        String name = scanner.nextLine();
        System.out.print("Enter customer email: ");
        String email = scanner.nextLine();
        System.out.print("Enter customer phone: ");
        String phone = scanner.nextLine();

        Customer customer = new Customer(name, email, phone);
        try {
            customerDAO.createCustomer(customer);
            System.out.println("Customer added successfully.");
        } catch (SQLException e) {
            System.out.println("Error during the creation: " + e.getMessage());
        }
    }

    private void listCustomers() {
        try {
            List<Customer> customers = customerDAO.readAllCustomers();
            for (Customer customer : customers) {
                System.out.println(customer);
            }
        } catch (SQLException e) {
            System.out.println("Error during the reading process: " + e.getMessage());
        }
    }

    private void updateCustomer() {
        System.out.print("Enter customer ID to update: ");
        int customerId = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter new name: ");
        String name = scanner.nextLine();
        System.out.print("Enter new email: ");
        String email = scanner.nextLine();
        System.out.print("Enter new phone: ");
        String phone = scanner.nextLine();

        Customer customer = new Customer(name, email, phone);
        try {
            customerDAO.updateCustomer(customer, customerId);
            System.out.println("Customer updated successfully.");
        } catch (SQLException e) {
            System.out.println("Error during the update: " + e.getMessage());
        }
    }

    private void deleteCustomer() {
        System.out.print("Enter customer ID to delete: ");
        int customerId = scanner.nextInt();
        scanner.nextLine();
        try {
            customerDAO.deleteCustomer(customerId);
            System.out.println("Customer deleted successfully.");
        } catch (SQLException e) {
            System.out.println("Error during the deletion: " + e.getMessage());
        }
    }
}
