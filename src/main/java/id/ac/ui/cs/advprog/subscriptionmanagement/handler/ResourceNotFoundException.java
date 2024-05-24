package id.ac.ui.cs.advprog.subscriptionmanagement.handler;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String message) {
        super(message);
    }
}