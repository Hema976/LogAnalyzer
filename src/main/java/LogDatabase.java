
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class LogDatabase {

    public static void saveLog(
        String username,
        String ip,
        String eventType,
        LocalDateTime eventTime
    ) {

        String sql = "INSERT INTO login_logs " +
            "(username, ip_address, event_type, event_time) " +
            "VALUES (?, ?, ?, ?)";

        try (Connection con = DatabaseConnection.getConnection()) {

            if (con == null) return;

            try (PreparedStatement ps = con.prepareStatement(sql)) {

                ps.setString(1, username);
                ps.setString(2, ip);
                ps.setString(3, eventType);
                ps.setTimestamp(
                    4, java.sql.Timestamp.valueOf(eventTime)
                );

                ps.executeUpdate();

                System.out.println("Login record saved!");

            }

        } catch (SQLException e) {
            System.out.println("Database Error: " + e.getMessage());
        }
    }
}
