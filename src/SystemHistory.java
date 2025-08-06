import java.util.ArrayList;
import java.util.List;

public class SystemHistory {
    private List<String> logs = new ArrayList<>();

    public void log(String entry) {
        logs.add(entry);
        System.out.println("[היסטוריה] " + entry);
    }

    public List<String> getLogs() {
        return logs;
    }

    public void printLogs() {
        for (String log : logs) {
            System.out.println(log);
        }
    }
}
