package com.xtra.demo.errors;



import com.xtra.demo.errors.http.HttpException;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Comparator;
import java.util.List;

@Slf4j
@ControllerAdvice
public class ErrorHandler {

    private static final String AN_UNHANDLED_EXCEPTION_HAS_OCCURRED = "An unhandled exception has occurred";
    private static final Logger log = LoggerFactory.getLogger(ErrorHandler.class);

    @ExceptionHandler(HttpException.class)
    public ResponseEntity<ErrorResponse> handleHttpExceptions(HttpException e) {
        if (e.getStatus().is5xxServerError()) {
            log.error(AN_UNHANDLED_EXCEPTION_HAS_OCCURRED, e);
        } else {
            log.debug("Request failed", e);
        }

        return ResponseEntity.status(e.getStatus()).body(ErrorResponse.from(e));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleUnexpectedExceptions(Exception e) {
        log.error(AN_UNHANDLED_EXCEPTION_HAS_OCCURRED, e);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ErrorResponse.from("An unexpected error occurred."));
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ErrorResponse> handleDataExceptions(DataIntegrityViolationException e) {
        log.error(AN_UNHANDLED_EXCEPTION_HAS_OCCURRED, e);

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ErrorResponse.from("An unexpected error occurred."));
    }


    @ExceptionHandler({HttpMessageNotReadableException.class})
    public ResponseEntity<ErrorResponse> handleFormatRequest(HttpMessageNotReadableException e) {
        return ResponseEntity.badRequest()
                .body(ErrorResponse.from("Invalid JSON format request."));
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationException(MethodArgumentNotValidException e) {
        BindingResult bindingResult = e.getBindingResult();
        List<FieldError> fieldErrors = bindingResult.getFieldErrors();
        List<String> errorMessages = fieldErrors.stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .filter(Strings::isNotBlank)
                .sorted(Comparator.naturalOrder())
                .toList();
        return ResponseEntity.badRequest()
                .body(ErrorResponse.from(String.join(" ", errorMessages)));
    }

}
