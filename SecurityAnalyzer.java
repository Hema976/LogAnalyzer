
import java.io.*;
import java.util.*;

public class SecurityAnalyzer {

    public static void analyze(String fileName) {

        Map<String, Integer> failedAttempts = new HashMap<>();

        try (BufferedReader br =
             new BufferedReader(new FileReader(fileName))) {

            String line;

            while ((line = br.readLine()) != null) {

                String[] data = line.split(",");

                if (data.length != 4) {
                    continue;
                }

                String username = data[1].trim();
                String ip = data[2].trim();
                String status = data[3].trim();

                if (status.equals("FAILED")) {

                    System.out.println(
                        "Failed Login: " + username
                    );

                    failedAttempts.put(
                        ip,
                        failedAttempts.getOrDefault(ip, 0) + 1
                    );
                }
            }

            System.out.println("\n===== SECURITY ALERTS =====");

            for (String ip : failedAttempts.keySet()) {

                int attempts = failedAttempts.get(ip);

                if (attempts >= 5) {
                    System.out.println("HIGH RISK ALERT!");
                    System.out.println("IP: " + ip);
                    System.out.println("Failed Attempts: " + attempts);
                }
            }

        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
