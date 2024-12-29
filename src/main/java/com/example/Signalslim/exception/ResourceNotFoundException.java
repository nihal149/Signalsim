package com.example.Signalslim.exception;


public class ResourceNotFoundException extends RuntimeException {

    // Constructeur avec message d'erreur et un code d'erreur optionnel
    public ResourceNotFoundException(String message) {
        super(message);
    }

    // Constructeur avec un message et un code d'erreur supplémentaire
    public ResourceNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
