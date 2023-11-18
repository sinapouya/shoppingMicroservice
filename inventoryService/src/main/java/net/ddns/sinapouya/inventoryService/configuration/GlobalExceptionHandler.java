package net.ddns.sinapouya.inventoryService.configuration;

import net.ddns.sinapouya.inventoryService.dto.Response;
import net.ddns.sinapouya.inventoryService.exception.NotExistInInventoryException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(NotExistInInventoryException.class)
    public ResponseEntity<Response<String>> handleNotExistInInventoryException(NotExistInInventoryException ex) {
        Response<String> response = new Response<>(0, "Error: " + ex.getMessage(), null);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }
}
