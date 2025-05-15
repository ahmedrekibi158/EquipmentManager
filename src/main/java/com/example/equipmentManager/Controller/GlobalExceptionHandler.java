package com.example.equipmentManager.Controller;
import java.time.format.DateTimeParseException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.example.equipmentManager.DTO.ApiResponse;
import com.example.equipmentManager.Exception.InvalidInputException;
import com.example.equipmentManager.Exception.CustomException;
import com.example.equipmentManager.Exception.EquipmentNotExistException;
import com.example.equipmentManager.Exception.EquipmentNotFoundException;

import com.example.equipmentManager.Exception.SerialNumberExistsException;
@ControllerAdvice
public class GlobalExceptionHandler extends RuntimeException {

    public GlobalExceptionHandler( ) {
        super();
    }
    
    @ExceptionHandler(EquipmentNotExistException.class)
    public ResponseEntity<ApiResponse> handlerEquipmentNotExistClass(EquipmentNotExistException ex) {
        ApiResponse apiResponse = new ApiResponse(true, ex.getMessage(), null);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiResponse);
    }

    @ExceptionHandler(EquipmentNotFoundException.class)
    public ResponseEntity<ApiResponse> handlerEquipmentNotFoundClass(EquipmentNotFoundException ex) {
        ApiResponse apiResponse = new ApiResponse(true, ex.getMessage(), null);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(apiResponse);
    }

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ApiResponse> handlerCustomException(CustomException ex) {
        ApiResponse apiResponse = new ApiResponse(false, ex.getMessage(), null);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiResponse);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
        String errorMessage = ex.getBindingResult().getFieldError().getDefaultMessage();
        ApiResponse apiResponse = new ApiResponse(false, errorMessage, null);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiResponse);
    }

    @ExceptionHandler(DateTimeParseException.class)
    public ResponseEntity<ApiResponse> handleDateTimeParseException(DateTimeParseException ex){
        ApiResponse apiResponse = new ApiResponse(false, ex.getMessage(), null);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiResponse);
    }

    @ExceptionHandler(SerialNumberExistsException.class)
    public ResponseEntity<ApiResponse> handleSerialNumberExistsException(SerialNumberExistsException ex) {
        ApiResponse apiResponse = new ApiResponse(false, ex.getMessage(), null);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiResponse);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ApiResponse> handleIllegalArgumentException(IllegalArgumentException ex) {
        ApiResponse apiResponse = new ApiResponse(false, ex.getMessage(), null);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiResponse);
    }

    @ExceptionHandler(InvalidInputException.class)
    public ResponseEntity<ApiResponse> handleInvalidInputException(InvalidInputException ex) {
        ApiResponse apiResponse = new ApiResponse(false, ex.getMessage(), null);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiResponse);
    }

}