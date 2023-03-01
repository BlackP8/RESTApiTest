package universaltools;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author Pavel Romanov 17.02.2023
 */

public class DateUtil {
    private static final String DATE_PATTERN = "yyyy-MM-dd HH:mm:ss";

    public static String getCurrentDatetime() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern(DATE_PATTERN));
    }
}
