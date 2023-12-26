package net.ddns.sinapouya.userservice.configuration;

import net.ddns.sinapouya.userservice.dto.Response;
import net.ddns.sinapouya.userservice.exception.UserException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(UserException.class)
    public ResponseEntity<Response<String>> handleNotExistInInventoryException(UserException ex) {
        Response<String> response = new Response<>(0, "Error: " + ex.getMessage(), null);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
