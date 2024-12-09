import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class App {
    public static void main(String[] args) {
        String url = "jdbc:postgresql://localhost:5432/postgres";
        String user = "postgres";
        String password = "1234";
        Connection conn = null;
        try{
            conn = DriverManager.getConnection(url, user, password);
        }
        catch(SQLException a){
            System.out.println("Connection failed.");
        }
    }
}