package com.smartosc.product.exceptions;

import javassist.NotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;

/**
 * product
 *
 * @author Tung lam
 * @created_at 08/06/2020 - 15:01
 * @created_by Tung lam
 * @since 08/06/2020
 */
@RestControllerAdvice
public class CustomGlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatus status, WebRequest request) {
        Map<String, String> errorMessageMap = new HashMap<>();

        ex.getBindingResult().getAllErrors().forEach(e -> {
            if (e instanceof FieldError) {
                errorMessageMap.put(((FieldError) e).getField(), e.getDefaultMessage());
            }
        });
        ErrorObject errorObject = new ErrorObject();
        errorObject.setMessage(errorMessageMap);

        return new ResponseEntity<>(errorObject, HttpStatus.BAD_REQUEST);

    }

    public Map<String, String> convertMessageString(String status, String ex) {
        Map<String, String> stringIntegerMap = new HashMap();
        stringIntegerMap.put(status, ex);
        return stringIntegerMap;
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorObject> handlerNotFoundException(NotFoundException ex) {
        ErrorObject err = new ErrorObject(HttpStatus.NOT_FOUND, convertMessageString(HttpStatus.NOT_FOUND.toString(), ex.getMessage()));
        return new ResponseEntity<>(err, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorObject> handlerNotFoundException(Exception ex) {
        ErrorObject err = new ErrorObject(HttpStatus.NOT_FOUND, convertMessageString(HttpStatus.NOT_FOUND.toString(), ex.getMessage()));
        return new ResponseEntity<>(err, HttpStatus.NOT_FOUND);
    }
}
