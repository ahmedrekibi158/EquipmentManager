package com.example.equipmentManager.Exception;


public class EquipmentNotExistException extends RuntimeException {
    public EquipmentNotExistException(String message) {
        super(message);
    }
}