package universaltools;

/**
 * @author Pavel Romanov 14.02.2023
 */

public class DataGeneratorUtil {
    private static final int MIN_RANGE = 1;
    private static final String numericString = "0123456789";

    public static int generateRandomNumber(int max) {
        return (int)(Math.random() * (max - MIN_RANGE + 1) + MIN_RANGE);
    }

    public static String generateRandomNumericString(int stringLength) {
        return generateRandomString(stringLength, numericString);
    }

    public static String generateRandomString(int stringLength, String chars) {
        StringBuilder stringBuilder = new StringBuilder(stringLength);
        for (int i = 0; i < stringLength; i++) {
            int index = generateRandomNumber(chars.length() - 1);
            stringBuilder.append(chars.charAt(index));
        }
        return stringBuilder.toString();
    }
}
