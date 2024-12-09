package dao;

import java.sql.*;

public class Author {

    public static void createAuthor(Connection conn, String name) throws SQLException {
        String sql = "INSERT INTO authors (name) VALUES (?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, name);
            pstmt.executeUpdate();
            System.out.println("Author created.");
        }
    }

    public static void readAuthors(Connection conn) throws SQLException {
        String sql = "SELECT * FROM authors";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("id") + ", Name: " + rs.getString("name"));
            }
        }
    }

    public static void updateAuthor(Connection conn, int id, String newName) throws SQLException {
        String sql = "UPDATE authors SET name = ? WHERE id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, newName);
            pstmt.setInt(2, id);
            pstmt.executeUpdate();
            System.out.println("Author updated.");
        }
    }

    public static void deleteAuthor(Connection conn, int id) throws SQLException {
        String sql = "DELETE FROM authors WHERE id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
            System.out.println("Author deleted.");
        }
    }
}
