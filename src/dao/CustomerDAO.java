package dao;

import java.sql.*;

public class CustomerDAO {

    public static void createCustomer(Connection conn, String name) throws SQLException {
        String sql = "INSERT INTO Customers (name) VALUES (?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, name);
            pstmt.executeUpdate();
            System.out.println("Customer created.");
        }
    }

    public static void readCustomers(Connection conn) throws SQLException {
        String sql = "SELECT * FROM Customers";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("id") + ", Name: " + rs.getString("name"));
            }
        }
    }

    public static void updateCustomer(Connection conn, int id, String newName) throws SQLException {
        String sql = "UPDATE Customers SET name = ? WHERE id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, newName);
            pstmt.setInt(2, id);
            pstmt.executeUpdate();
            System.out.println("Customer updated.");
        }
    }

    public static void deleteCustomer(Connection conn, int id) throws SQLException {
        String sql = "DELETE FROM Customers WHERE id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
            System.out.println("Customer deleted.");
        }
    }
}
