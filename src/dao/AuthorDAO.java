package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.Author;

public class AuthorDAO {
    private final Connection conn;

    public AuthorDAO(Connection conn) {
        this.conn = conn;
    }

    public void createAuthor(Author author) throws SQLException {
        String sql = "INSERT INTO author (name, bio) VALUES (?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, author.getName());
            pstmt.setString(2, author.getBio());
            pstmt.executeUpdate();
        }
    }

    public List<Author> readAllAuthors() throws SQLException {
        List<Author> authors = new ArrayList<>();
        String sql = "SELECT * FROM author";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Author author = new Author(
                    rs.getInt("authorId"),
                    rs.getString("name"),
                    rs.getString("bio")
                );
                authors.add(author);
            }
        }
        return authors;
    }

    public void updateAuthor(Author author, int authorid) throws SQLException {
        String sql = "UPDATE author SET name = ?, bio = ? WHERE authorid = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, author.getName());
            pstmt.setString(2, author.getBio());
            pstmt.setInt(3, authorid);
            pstmt.executeUpdate();
        }
    }

    public void deleteAuthor(int authorId) throws SQLException {
        String sql = "DELETE FROM author WHERE authorid = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, authorId);
            pstmt.executeUpdate();
        }
    }

    public boolean doesAuthorExist(int authorId) throws SQLException {
        String sql = "SELECT COUNT(*) FROM author WHERE authorid = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, authorId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        }
        return false;
    }
}
