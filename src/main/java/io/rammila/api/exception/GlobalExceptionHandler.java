package io.rammila.api.exception;


import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import java.util.Date;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({NullPointerException.class})
    public final ResponseEntity<ErrorDetails<Object>> handleAllException(Exception ex, WebRequest request){
        ex.printStackTrace();

        ErrorDetails<Object> errorDetails = ErrorDetails
                .builder()
                .timestamp(new Date())
                .message(ex.getMessage())
                //.trace(ex.getStackTrace()[0])
                .details(request.getDescription(false))
                .code(HttpStatus.INTERNAL_SERVER_ERROR.value()).build();
        return new ResponseEntity<>(errorDetails,HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
