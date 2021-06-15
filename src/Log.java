import java.util.logging.Level;
import java.util.logging.Logger;

public class Log {
    private static final Logger logger = Logger.getLogger(Logger.class.getName());

    public static void logInfo(String message) {
        logger.info(message);
    }

    public static void logError(String message) {
        logger.log(Level.WARNING, message);
    }

}
