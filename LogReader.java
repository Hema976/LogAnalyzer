
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class LogReader {

    public static void readLogs(String fileName) {

        try (BufferedReader br =
             new BufferedReader(new FileReader(fileName))) {

            String line;

            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }

        } catch (IOException e) {
            System.out.println("Error reading log file: "
                               + e.getMessage());
        }
    }
}
