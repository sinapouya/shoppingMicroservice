package net.ddns.sinapouya.inventoryService.exception;

public class NotExistInInventoryException extends RuntimeException{
    public NotExistInInventoryException() {
        super();
    }

    public NotExistInInventoryException(String message) {
        super(message);
    }
}