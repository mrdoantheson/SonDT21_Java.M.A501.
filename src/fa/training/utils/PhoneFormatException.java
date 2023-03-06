package fa.training.utils;

import java.io.Serial;

public class PhoneFormatException extends Exception {
    @Serial
    private static final long serialVersionUID = 1L;

    public PhoneFormatException(String message) {
        super(message);
    }
}
