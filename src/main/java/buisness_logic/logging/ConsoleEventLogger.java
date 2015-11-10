package buisness_logic.logging;


public class ConsoleEventLogger implements EventLogger {
    @Override
    public void logEvent(String message) {
        System.out.println(message);
    }
}
