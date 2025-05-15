package com.example.equipmentManager.Exception;


public class SerialNumberExistsException extends RuntimeException {
    public SerialNumberExistsException(String message) {
        super(message);
    }
}