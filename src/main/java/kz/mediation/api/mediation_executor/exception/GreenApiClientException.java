package kz.mediation.api.mediation_executor.exception;

public class GreenApiClientException extends RuntimeException {

    public GreenApiClientException() {
        super("Green-api client unknown exception.");
    }

    public GreenApiClientException(String message) {
        super(message);
    }

    public GreenApiClientException(String message, Throwable cause) {
        super(message, cause);
    }
}

