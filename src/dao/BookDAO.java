package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.Book;

public class BookDAO {

    private final Connection conn;

    public BookDAO(Connection conn) {
        this.conn = conn;
    }

    public void createBook(Book book) throws SQLException {
        String sql = "INSERT INTO book (title, genre, price, stock) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, book.getTitle());
            stmt.setString(2, book.getGenre());
            stmt.setDouble(3, book.getPrice());
            stmt.setDouble(4, book.getStock());
            stmt.executeUpdate();

            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    book.setBookId(generatedKeys.getInt(1));
                }
            }
        }
    }

    public List<Book> readAllBooks() throws SQLException {
        List<Book> books = new ArrayList<>();
        String sql = "SELECT * FROM book";
        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Book book = new Book(
                        rs.getInt("bookId"),
                        rs.getString("title"),
                        rs.getString("genre"),
                        rs.getDouble("price"),
                        rs.getInt("stock")
                );
                books.add(book);
            }
        }
        return books;
    }

    public void updateBook(Book book, int bookid) throws SQLException {
        String sql = "UPDATE book SET title = ?, genre = ?, price = ?, stock = ? WHERE bookid = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, book.getTitle());
            pstmt.setString(2, book.getGenre());
            pstmt.setDouble(3, book.getPrice());
            pstmt.setDouble(4, book.getStock());
            pstmt.setInt(5, bookid);
            pstmt.executeUpdate();
        }
    }

    public void deleteBook(int bookId) throws SQLException {
        String sql = "DELETE FROM book WHERE bookid = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, bookId);
            pstmt.executeUpdate();
        }
    }

    public boolean doesBookExist(int bookId) throws SQLException {
        String sql = "SELECT COUNT(*) FROM book WHERE bookid = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, bookId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        }
        return false;
    }

}
