package fa.training.utils;

import java.io.Serial;

public class InvalidNumberException extends Exception {
    @Serial
    private static final long serialVersionUID = 1L;

    public InvalidNumberException(String message) {
        super(message);
    }
}
