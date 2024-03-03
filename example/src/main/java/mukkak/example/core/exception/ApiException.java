package mukkak.example.core.exception;

import lombok.Getter;

@Getter
public class ApiException extends RuntimeException {
    public static final String SERVICE_ERROR = "SERVICE_ERROR";
    public static final String ENTITY_NOT_FOUND = "ENTITY_NOT_FOUND";
    public static final String INPUT_VALIDATION_FAILED = "INPUT_VALIDATION_FAILED";

    private final String code;

    public ApiException(String code, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
    }

    public ApiException(String code, String message) {
        super(message);
        this.code = code;
    }

    public ApiException(String message, ServiceException e) {
        this(SERVICE_ERROR, message, e);
    }
}
