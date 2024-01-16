package dev.codex.web.application.exception;

public class ProcessingException extends RuntimeException {
    public static final String NULL_POINTER_EXCEPTION_MSG_FORMAT = "%s can not be null";
    public static final String MAIL_EXCEPTION_MSG_FORMAT = "Exception occurred while sending mail to %s";
    public static final String KEYSTORE_EXCEPTION_MSG_FORMAT = "Exception occurred while %s keystore";

    public static final String BLANK_STRING_EXCEPTION_MSG_FORMAT = "Field %s cannot be null or blank in %s";
    public static final String RESOURCE_NOT_FOUND_EXCEPTION_MSG_FORMAT = "Resource %s not found";
    public static final String INVALID_RESULT_COUNT_EXCEPTION_MSG_FORMAT = "Excepted %d updates, but was %d";

    public ProcessingException(String message) {
        super(message);
    }

    public ProcessingException(String message, Throwable cause) {
        super(message, cause);
    }
}