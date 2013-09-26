package org.nr.roulette.exceptions;

public class ValidationException extends Exception {

    private static final long serialVersionUID = 4955584541889982508L;

    public ValidationException() {
        super();
    }

    public ValidationException(String message) {
        super(message);
    }

    public ValidationException(String message, Throwable cause) {
        super(message, cause);
    }

    public ValidationException(Throwable cause) {
        super(cause);
    }

}
