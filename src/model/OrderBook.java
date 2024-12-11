package model;

public class OrderBook {
    private int orderId;
    private int bookId;

    public OrderBook(int orderId, int bookId) {
        this.orderId = orderId;
        this.bookId = bookId;
    }

    // Getters and Setters
    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }
}
