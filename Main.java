
public class Main {

    public static void main(String[] args) {

        System.out.println("================================");
        System.out.println("        LOG ANALYZER");
        System.out.println("================================");

        System.out.println("\nReading Server Logs...");

        LogReader.readLogs("server.log");

        System.out.println("\nAnalyzing Security Events...");

        SecurityAnalyzer.analyze("server.log");

        System.out.println("\nAnalysis Completed!");

        System.out.println("================================");
    }
}
