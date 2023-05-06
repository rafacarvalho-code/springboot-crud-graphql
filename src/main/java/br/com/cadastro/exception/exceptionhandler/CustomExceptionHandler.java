package br.com.cadastro.exception.exceptionhandler;

import graphql.execution.SimpleDataFetcherExceptionHandler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@SuppressWarnings({"unchecked", "rawtypes"})
@ControllerAdvice
public class CustomExceptionHandler extends SimpleDataFetcherExceptionHandler {
    @ExceptionHandler(ServletRequestBindingException.class)
    public final ResponseEntity<Object> handleHeaderException(Exception ex, WebRequest request) {
        return new ResponseEntity("Bad Request", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) {
        return new ResponseEntity("Server Error", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}