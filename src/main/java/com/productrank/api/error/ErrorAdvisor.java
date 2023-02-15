package com.productrank.api.error;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
@RestControllerAdvice
public class ErrorAdvisor extends ResponseEntityExceptionHandler {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception ex) {
        log.error("handleException:", ex);
        ErrorResponse response = new ErrorResponse(ErrorCode.find(ex.getMessage()));
        return ResponseEntity.status(response.getStatus()).body(response);
    }
    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleUsernameNotFoundException(UsernameNotFoundException ex) {
        log.error("handleException:", ex);
        ErrorResponse response = new ErrorResponse(ErrorCode.find(ex.getMessage()));
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @ExceptionHandler({ AuthenticationException.class })
    public ResponseEntity<ErrorResponse> handleAuthenticationException(Exception ex) {

        ErrorResponse re = new ErrorResponse(ErrorCode.find(ex.getMessage()));
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(re);
    }
//    UsernameNotFoundException
}
