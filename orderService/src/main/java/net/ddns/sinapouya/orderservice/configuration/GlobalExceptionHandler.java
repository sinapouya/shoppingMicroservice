package net.ddns.sinapouya.orderservice.configuration;

import net.ddns.sinapouya.orderservice.dto.Response;
import net.ddns.sinapouya.orderservice.exception.OrderException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(OrderException.class)
    public ResponseEntity<Response<String>> handleNotExistInInventoryException(OrderException ex) {
        Response<String> response = new Response<>(0, "Error: " + ex.getMessage(), null);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
