import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionTesting {
    public static void main(String[] args) {
        String url = "jdbc:postgresql://localhost:5432/postgres";
        String user = "postgres";
        String password = "1234";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, user, password);
            if (conn != null && conn.isValid(2)) {
                System.out.println("Connection is active!");
            } else {
                System.out.println("Failed to connect.");
            }

        } catch (SQLException e) {
            System.out.println("Connection failed.");
            e.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
}
