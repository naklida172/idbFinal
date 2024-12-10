
import dao.AuthorDAO;
import dao.BookDAO;
import dao.CustomerDAO;
import dao.OrdersDAO;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class App {

    private static final String URL = "jdbc:postgresql://localhost:5432/postgres";
    private static final String USER = "postgres";
    private static final String PASSWORD = "1234";

    public static void main(String[] args) {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
            if (conn != null) {
                System.out.println("Connected to the database.");
                AuthorDAO authorconn = new AuthorDAO(conn);
                BookDAO bookconn = new BookDAO(conn);
                CustomerDAO customerconn = new CustomerDAO(conn);
                OrdersDAO ordersconn = new OrdersDAO(conn);
            } else {
                System.out.println("Failed to connect to the database.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
