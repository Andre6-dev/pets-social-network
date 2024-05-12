package com.devandre.petsnetwork.exception.customexceptions;

public class OperationNotPermittedException extends RuntimeException {

    public OperationNotPermittedException(String message) {
        super(message);
    }
}
