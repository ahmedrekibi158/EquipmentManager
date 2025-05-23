package com.example.equipmentManager.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class ApiResponse<T>{
    private boolean success;
    private String message;
    private T data;

}