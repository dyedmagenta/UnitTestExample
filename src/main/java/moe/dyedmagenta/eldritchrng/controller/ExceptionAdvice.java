package moe.dyedmagenta.eldritchrng.controller;

import lombok.extern.slf4j.Slf4j;
import moe.dyedmagenta.eldritchrng.error.NotFoundException;
import moe.dyedmagenta.eldritchrng.error.ValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class ExceptionAdvice {


    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<String> handleValidationException(ValidationException validationException) {
        log.error("Handled exception", validationException);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(validationException.getMessage());
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<String> handleNotFoundException(NotFoundException notFoundException) {
        log.error("Handled exception", notFoundException);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(notFoundException.getMessage());
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> handleUnexpectedException(RuntimeException runtimeException) {
        log.error("Handled exception", runtimeException);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Unexpected error occurred");
    }
}
