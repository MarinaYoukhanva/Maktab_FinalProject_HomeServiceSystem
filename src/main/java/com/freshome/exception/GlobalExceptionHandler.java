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

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler extends
        ResponseEntityExceptionHandler {

    //    @Override
//    protected ResponseEntity<Object> handleMethodArgumentNotValid(
//            MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
//        log.info("validation exception triggered");
//        List<String> errorMessages = ex.getBindingResult().getFieldErrors()
//                .stream().map(
//                fe -> fe.getField() + ": " + fe.getDefaultMessage()).toList();
//        return ResponseEntity
//                .badRequest()
//                .body(Map.of("errorMessage", errorMessages));
//    }
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        log.info("Validation exception triggered");
        log.error(ex.getMessage());

        Map<String, List<String>> errorMessages = new HashMap<>();
//        for (FieldError fieldError : ex.getBindingResult().getFieldErrors())
//            errorMessages.put(fieldError.getField(), fieldError.getDefaultMessage());
        for (FieldError fieldError : ex.getBindingResult().getFieldErrors()) {
            errorMessages.computeIfAbsent(fieldError.getField(), key -> new ArrayList<>())
                    .add(fieldError.getDefaultMessage());
        }
        return ResponseEntity
                .badRequest()
                .body(Map.of("errors", errorMessages));
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<Map<String, String>> handleDatabaseException(DataIntegrityViolationException ex) {
        log.error(ex.getMessage());

        if (ex.getRootCause() !=null
                && ex.getRootCause().getMessage().contains("unique constraint")){
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body(Map.of(
                            "message","The value exists! Please try again with different value."));
        }
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(Map.of(
                        "error", "Something went wrong! Please try again later."));
//                        "error", ex.getMessage()));
    }

    @ExceptionHandler(ExistenceException.class)
    public ResponseEntity<Map<String, String>> handleExistenceException(ExistenceException ex) {
        log.error(ex.getMessage());

        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(Map.of(
                        "errorType", "ExistenceException",
                        "message", ex.getMessage()));
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Map<String, String>> handleNotFoundException(NotFoundException ex) {
        log.error(ex.getMessage());

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(Map.of(
                        "errorType", "NotFound",
                        "message", ex.getMessage()));
    }

    @ExceptionHandler(ChangePasswordException.class)
    public ResponseEntity<Map<String, String>> handleChangePasswordException(ChangePasswordException ex) {
        log.error(ex.getMessage());

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(Map.of(
                        "message", ex.getMessage()));
    }

    @ExceptionHandler(NotCompletedOrderException.class)
    public ResponseEntity<Map<String, String>> handleNotCompletedOrderException(NotCompletedOrderException ex) {
        log.error(ex.getMessage());

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(Map.of(
                        "message", ex.getMessage(),
                        "timestamp", LocalDateTime.now().toString()));
    }

    @ExceptionHandler(InvalidPriceException.class)
    public ResponseEntity<Map<String, String>> handleInvalidPriceException(InvalidPriceException ex) {
        log.error(ex.getMessage());

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(Map.of(
                        "message", ex.getMessage(),
                        "timestamp", LocalDateTime.now().toString()));
    }
}
