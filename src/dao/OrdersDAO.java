package dao;

import java.sql.*;

public class OrdersDAO {

    public static void createOrders(Connection conn, String name) throws SQLException {
        String sql = "INSERT INTO Orders (name) VALUES (?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, name);
            pstmt.executeUpdate();
            System.out.println("Orders created.");
        }
    }

    public static void readOrderss(Connection conn) throws SQLException {
        String sql = "SELECT * FROM Orderss";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("id") + ", Name: " + rs.getString("name"));
            }
        }
    }

    public static void updateOrders(Connection conn, int id, String newName) throws SQLException {
        String sql = "UPDATE Orderss SET name = ? WHERE id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, newName);
            pstmt.setInt(2, id);
            pstmt.executeUpdate();
            System.out.println("Orders updated.");
        }
    }

    public static void deleteOrders(Connection conn, int id) throws SQLException {
        String sql = "DELETE FROM Orderss WHERE id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
            System.out.println("Orders deleted.");
        }
    }
}


