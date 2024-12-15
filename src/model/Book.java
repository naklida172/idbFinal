package model;

public class Book {

    private int bookId;
    private String title;
    private String genre;
    private double price;
    private int stock;

    // Getters and Setters
    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    @Override
    public String toString() {
        return "Book{" +
                "bookId=" + bookId +
                ", title='" + title + '\'' +
                ", genre='" + genre + '\'' +
                ", price=" + price +
                ", stock=" + stock +
                '}';
    }

    // Constructors
    public Book( String title, String genre, double price,int stock) {
        this.title = title;
        this.genre = genre;
        this.price = price;
    }

    public Book(int bookID, String title, String genre, double price, int stock) {
        this.bookId=bookID;
        this.title = title;
        this.genre = genre;
        this.price = price;
        this.stock = stock;
    }
    

}
