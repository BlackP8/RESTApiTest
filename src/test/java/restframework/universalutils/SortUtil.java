package restframework.universalutils;

import java.util.Iterator;
import java.util.List;

/**
 * @author Pavel Romanov 13.03.2023
 */

public class SortUtil {
    public static boolean isResponseSorted(List<String> responseList) {
        boolean result = false;
        Iterator<String> iterator = responseList.iterator();
        String current, previous = iterator.next();

        while (iterator.hasNext()) {
            current = iterator.next();
            if (previous.compareTo(current) < 0) {
                result = true;
            }
            previous = current;
        }
        return result;
    }
}
