package mukkak.example.core.exception;

import lombok.Getter;

@Getter
public class ServiceException extends Exception {
    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public ServiceException(String message) {
        super(message);
    }
}
