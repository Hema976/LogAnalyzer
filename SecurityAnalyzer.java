

import java.io.*;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class SecurityAnalyzer {

    public static void analyze(String fileName) {

        Map<String, Integer> failedAttempts = new HashMap<>();

        try (BufferedReader br =
             new BufferedReader(new FileReader(fileName))) {

            String line;

            while ((line = br.readLine()) != null) {

                String[] data = line.split(",");

                if (data.length != 4) continue;

                LocalDateTime dateTime =
                    LocalDateTime.parse(
                        data[0].trim(),
                        DateTimeFormatter.ofPattern(
                            "yyyy-MM-dd HH:mm:ss"
                        )
                    );

                String username = data[1].trim();
                String ip = data[2].trim();
                String status = data[3].trim();

                if (status.equals("FAILED")) {
                    failedAttempts.put(
                        ip,
                        failedAttempts.getOrDefault(ip, 0) + 1
                    );
                }

                LocalTime time = dateTime.toLocalTime();

                if (time.isBefore(LocalTime.of(6, 0))
                    || !time.isBefore(LocalTime.of(22, 0))) {

                    System.out.println(
                        "SUSPICIOUS TIME ALERT: " + username
                    );

                    System.out.println("IP: " + ip);
                    System.out.println("Time: " + time);
                }
            }

            System.out.println("\n===== SECURITY ALERTS =====");

            for (String ip : failedAttempts.keySet()) {

                int attempts = failedAttempts.get(ip);

                if (attempts >= 5) {
                    System.out.println("HIGH RISK ALERT");
                    System.out.println("IP: " + ip);
                    System.out.println("Attempts: " + attempts);
                }
            }

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
