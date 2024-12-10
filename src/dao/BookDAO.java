package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.Book;

public class BookDAO {
    private Connection conn;

    public BookDAO(Connection conn) {
        this.conn = conn;
    }

    public void createBook(Book book) throws SQLException {
        String sql = "INSERT INTO book (title, genre, price) VALUES (?, ?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, book.getTitle());
            pstmt.setString(2, book.getGenre());
            pstmt.setDouble(3, book.getPrice());
            pstmt.executeUpdate();
        }
    }

    public List<Book> readAllBooks() throws SQLException {
        List<Book> books = new ArrayList<>();
        String sql = "SELECT * FROM book";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Book book = new Book(
                    rs.getInt("bookid"),
                    rs.getString("title"),
                    rs.getString("genre"),
                    rs.getDouble("price")
                );
                books.add(book);
            }
        }
        return books;
    }

    public void updateBook(Book book) throws SQLException {
        String sql = "UPDATE book SET title = ?, genre = ?, price = ? WHERE bookid = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, book.getTitle());
            pstmt.setString(2, book.getGenre());
            pstmt.setDouble(3, book.getPrice());
            pstmt.setInt(4, book.getBookId());
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
}

