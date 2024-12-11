package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.Orders;

public class OrdersDAO {
    private Connection conn;

    public OrdersDAO(Connection conn) {
        this.conn = conn;
    }

    public void createOrder(Orders order) throws SQLException {
        String sql = "INSERT INTO orders (customerid, orderdate, totalamount) VALUES (?, ?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, order.getCustomerId());
            pstmt.setDate(2, order.getOrderDate());
            pstmt.setDouble(3, order.getTotalAmount());
            pstmt.executeUpdate();
        }
    }

    public List<Orders> readAllOrders() throws SQLException {
        List<Orders> orders = new ArrayList<>();
        String sql = "SELECT * FROM orders";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Orders order = new Orders(
                    rs.getInt("orderId"),
                    rs.getInt("customerid"),
                    rs.getDate("orderdate"),
                    rs.getDouble("totalamount")
                );
                orders.add(order);
            }
        }
        return orders;
    }

    public void updateOrder(Orders order, int orderid) throws SQLException {
        String sql = "UPDATE orders SET customerid = ?, orderdate = ?, totalamount = ? WHERE orderid = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, order.getCustomerId());
            pstmt.setDate(2, order.getOrderDate());
            pstmt.setDouble(3, order.getTotalAmount());
            pstmt.setInt(4, orderid);
            pstmt.executeUpdate();
        }
    }

    public void deleteOrder(int orderId) throws SQLException {
        String sql = "DELETE FROM orders WHERE orderid = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, orderId);
            pstmt.executeUpdate();
        }
    }

    public boolean doesOrderExist(int orderId) throws SQLException {
        String sql = "SELECT COUNT(*) FROM orders WHERE orderid = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, orderId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        }
        return false;
    }

}


