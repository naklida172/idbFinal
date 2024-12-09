package model;

public class Book {

    private int bookId;
    private String title;
    private String genre;
    private double price;

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

    // Constructors
    public Book(int bookId, String title, String genre, double price) {
        this.bookId = bookId;
        this.title = title;
        this.genre = genre;
        this.price = price;
    }

}
