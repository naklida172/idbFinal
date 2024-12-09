package dao;

import java.sql.*;

public class BookDAO {
    
    private Connection conn;

    public BookDAO(Connection conn) {
         this.conn = conn;
         }

    public static void createBook(Connection conn, String name) throws SQLException {
        String sql = "INSERT INTO Books (name) VALUES (?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, name);
            pstmt.executeUpdate();
            System.out.println("Book created.");
        }
    }

    public static void readAllBooks(Connection conn) throws SQLException {
        String sql = "SELECT * FROM Books";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("id") + ", Name: " + rs.getString("name"));
            }
        }
    }

    public static void updateBook(Connection conn, int id, String newName) throws SQLException {
        String sql = "UPDATE Books SET name = ? WHERE id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, newName);
            pstmt.setInt(2, id);
            pstmt.executeUpdate();
            System.out.println("Book updated.");
        }
    }

    public static void deleteBook(Connection conn, int id) throws SQLException {
        String sql = "DELETE FROM Books WHERE id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
            System.out.println("Book deleted.");
        }
    }
}
