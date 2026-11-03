
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    private static final String URL =
        "jdbc:mysql://localhost:3306/log_analyzer";

    private static final String USER = "root";

    private static final String PASSWORD = "Hema@123";

    public static Connection getConnection() {

        try {
            Connection con = DriverManager.getConnection(
                URL, USER, PASSWORD
            );

            System.out.println("Database Connected Successfully!");

            return con;

        } catch (SQLException e) {

            System.out.println("Database Connection Failed!");
            e.printStackTrace();

            return null;
        }
    }
}
