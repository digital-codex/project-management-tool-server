package dev.digitalcodex.web.application.exception;

public class ProcessingException extends RuntimeException {
    public static final String MAIL_EXCEPTION_MSG_FORMAT = "Exception occurred while sending mail to %s";

    public ProcessingException(String message) {
        super(message);
    }

    public ProcessingException(String message, Throwable cause) {
        super(message, cause);
    }
}
