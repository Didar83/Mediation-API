package kz.mediation.api.mediation_executor.exception;

public class BotStateException extends RuntimeException {
    public BotStateException() {
        super("State is not found.");
    }

    public BotStateException(String message) {
        super(message);
    }

    public BotStateException(String message, Throwable cause) {
        super(message, cause);
    }
}
