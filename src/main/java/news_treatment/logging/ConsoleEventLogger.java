package news_treatment.logging;


public class ConsoleEventLogger implements EventLogger {

    @Override
    public void logEvent(String message) {
        System.out.println(message);
    }
}
