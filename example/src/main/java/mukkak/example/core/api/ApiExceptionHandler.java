package mukkak.example.core.api;

import mukkak.example.core.exception.ApiException;
import mukkak.example.core.model.ApiErrorResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(ApiException.class)
    protected ResponseEntity<Object> handleApiException(ApiException ex, WebRequest request) {
        ApiErrorResponse response = new ApiErrorResponse(ex.getCode(), ex.getMessage());
        return handleExceptionInternal(ex, response, new HttpHeaders(), HttpStatus.valueOf(420), request);
    }
}
