package dao;

import model.OrderBook;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderBookDAO {
    private Connection conn;

    public OrderBookDAO(Connection conn) {
        this.conn = conn;
    }

    public void createOrderBook(OrderBook orderBook) throws SQLException {
        String sql = "INSERT INTO orderbook (orderid, bookid) VALUES (?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, orderBook.getOrderId());
            stmt.setInt(2, orderBook.getBookId());
            stmt.executeUpdate();
        }
    }

    public List<OrderBook> readAllOrderBooks() throws SQLException {
        String sql = "SELECT * FROM orderbook";
        try (PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            List<OrderBook> orderBooks = new ArrayList<>();
            while (rs.next()) {
                int orderId = rs.getInt("orderid");
                int bookId = rs.getInt("bookid");
                orderBooks.add(new OrderBook(orderId, bookId));
            }
            return orderBooks;
        }
    }

    public void deleteOrderBook(int orderId, int bookId) throws SQLException {
        String sql = "DELETE FROM orderbook WHERE orderid = ? AND bookid = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, orderId);
            stmt.setInt(2, bookId);
            stmt.executeUpdate();
        }
    }
}
