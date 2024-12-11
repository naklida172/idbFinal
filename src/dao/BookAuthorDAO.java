package dao;

import model.BookAuthor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookAuthorDAO {
    private Connection conn;

    public BookAuthorDAO(Connection conn) {
        this.conn = conn;
    }

    public void createBookAuthor(BookAuthor bookAuthor) throws SQLException {
        String sql = "INSERT INTO bookauthor (bookid, authorid) VALUES (?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, bookAuthor.getBookId());
            stmt.setInt(2, bookAuthor.getAuthorId());
            stmt.executeUpdate();
        }
    }

    public List<BookAuthor> readAllBookAuthors() throws SQLException {
        String sql = "SELECT * FROM bookauthor";
        try (PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            List<BookAuthor> bookAuthors = new ArrayList<>();
            while (rs.next()) {
                int bookId = rs.getInt("bookid");
                int authorId = rs.getInt("authorid");
                bookAuthors.add(new BookAuthor(bookId, authorId));
            }
            return bookAuthors;
        }
    }

    public void deleteBookAuthor(int bookId, int authorId) throws SQLException {
        String sql = "DELETE FROM bookauthor WHERE bookid = ? AND authorid = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, bookId);
            stmt.setInt(2, authorId);
            stmt.executeUpdate();
        }
    }
}
