
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class AlertDatabase {

    public static void saveAlert(
        String username,
        String ip,
        String alertType,
        String description
    ) {

        String sql = "INSERT INTO security_alerts " +
            "(username, ip_address, alert_type, description, alert_time) " +
            "VALUES (?, ?, ?, ?, ?)";

        try (Connection con = DatabaseConnection.getConnection()) {

            if (con == null) return;

            try (PreparedStatement ps = con.prepareStatement(sql)) {

                ps.setString(1, username);
                ps.setString(2, ip);
                ps.setString(3, alertType);
                ps.setString(4, description);
                ps.setTimestamp(
                    5,
                    java.sql.Timestamp.valueOf(LocalDateTime.now())
                );

                ps.executeUpdate();

                System.out.println("Security Alert Saved!");

            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
