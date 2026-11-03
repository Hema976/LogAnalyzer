
import java.io.*;
import java.time.*;
import java.time.format.*;
import java.util.*;

public class SecurityAnalyzer {

    public static void analyze(String fileName) {

        Map<String, Integer> attempts = new HashMap<>();

        Set<String> alertedIPs = new HashSet<>();

        DateTimeFormatter formatter =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        try (BufferedReader br =
             new BufferedReader(new FileReader(fileName))) {

            String line;

            while ((line = br.readLine()) != null) {

                String[] data = line.split(",");

                if (data.length != 4) continue;

                LocalDateTime dateTime =
                    LocalDateTime.parse(data[0].trim(), formatter);

                String username = data[1].trim();
                String ip = data[2].trim();
                String status = data[3].trim();

                LogDatabase.saveLog(
                    username, ip, status, dateTime
                );

                if (status.equalsIgnoreCase("FAILED")) {

                    int count = attempts.getOrDefault(ip, 0) + 1;
                    attempts.put(ip, count);

                    if (count == 5) {

                        System.out.println("HIGH RISK ALERT: " + ip);

                        AlertDatabase.saveAlert(
                            username,
                            ip,
                            "REPEATED LOGIN",
                            "Five failed login attempts detected"
                        );

                        alertedIPs.add(ip);
                    }
                }

                LocalTime time = dateTime.toLocalTime();

                if (time.isBefore(LocalTime.of(6, 0))
                    || !time.isBefore(LocalTime.of(22, 0))) {

                    System.out.println("SUSPICIOUS TIME: " + time);

                    AlertDatabase.saveAlert(
                        username,
                        ip,
                        "ODD TIMESTAMP",
                        "Login outside normal hours"
                    );
                }
            }

            System.out.println("Total suspicious IPs: "
                               + alertedIPs.size());

        } catch (Exception e) {
            System.out.println("Analysis Error: " + e.getMessage());
        }
    }
}
