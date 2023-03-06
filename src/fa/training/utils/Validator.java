package fa.training.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {

    // Phone number validation regex
    private static final String VALID_PHONE_REGEX = "^(0|84)([35789])[0-9]{8}$";

    // Order number length
    private static final String VALID_ORDER_NUMBER_REGEX = "^\\d{10}$";

    /**
     * Check phone number format is valid
     *
     */
    public static boolean isPhone(String phone) {
        Pattern pattern = Pattern.compile(VALID_PHONE_REGEX);
        Matcher matcher = pattern.matcher(phone);
        return matcher.matches();
    }

    /**
     * Check order number format is valid
     *
     */
    public static boolean isOrderNumber(String orderNumber) {
        Pattern pattern = Pattern.compile(VALID_ORDER_NUMBER_REGEX);
        Matcher matcher = pattern.matcher(orderNumber);
        return matcher.matches();
    }
}
