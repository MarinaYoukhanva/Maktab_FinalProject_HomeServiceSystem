package com.freshome.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;
import java.util.Map;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler extends
        ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        log.info("validation exception triggered");
        List<String> errorMessages = ex.getBindingResult().getFieldErrors()
                .stream().map(
                fe -> fe.getField() + ": " + fe.getDefaultMessage()).toList();
        return ResponseEntity
                .badRequest()
                .body(Map.of("errorMessage", errorMessages));
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<String> handleDatabaseException(DataIntegrityViolationException ex) {
//        if (ex.getRootCause() !=null
//                && ex.getRootCause().getMessage().contains("unique constraint")){
//            return ResponseEntity
//                    .status(HttpStatus.CONFLICT)
//                    .body("Database constraint violation: " + ex.getMessage());
//        }
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("something went wrong! ");
    }

    @ExceptionHandler(ExistenceException.class)
    public ResponseEntity<String> handleExistenceException(ExistenceException ex) {
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(ex.getMessage());
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<String> handleNotFoundException(NotFoundException ex) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(ex.getMessage());
    }
}
