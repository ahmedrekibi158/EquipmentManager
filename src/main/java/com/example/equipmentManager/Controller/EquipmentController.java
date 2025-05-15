package com.example.equipmentManager.Controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.equipmentManager.DTO.ApiResponse;
import com.example.equipmentManager.DTO.EquipmentDto;
import com.example.equipmentManager.Enums.EquipmentStatus;
import com.example.equipmentManager.Model.Equipment;
import com.example.equipmentManager.Service.EquipmentService;

import jakarta.validation.Valid;


@RestController
public class EquipmentController {
    private final EquipmentService equipmentService;
    private EquipmentController(EquipmentService equipmentService) {
        this.equipmentService = equipmentService;
    }

    @GetMapping("/equipment")
    public ResponseEntity<ApiResponse> getEquipment() {
        ApiResponse apiResponse = equipmentService.getAllEquipment();
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }   

    @GetMapping("/equipment/{id}")
    public ResponseEntity<ApiResponse> getEquipmentById(@PathVariable long id) {
        ApiResponse apiResponse = equipmentService.getEquipmentById(id);
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    } 

    @DeleteMapping("/equipment/{id}") 
    public ResponseEntity<ApiResponse> deleteEquipment(@PathVariable long id) {
        equipmentService.deleteEquipment(id);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse(true, "Equipment deleted successfully", null));
    }


    @PostMapping("/addEquipment")
    public ResponseEntity<ApiResponse> addEquipment(@Valid @RequestBody EquipmentDto equipmentDto) {
        ApiResponse apiResponse = equipmentService.addEquipment(equipmentDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(apiResponse);
    }

    @PostMapping("/updateEquipment/{id}")
    public ResponseEntity<ApiResponse> updateEquipment(@Valid @RequestBody EquipmentDto equipmentDto, @PathVariable long id) {
        ApiResponse apiResponse = equipmentService.updateEquipment(equipmentDto, id);
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }

    @GetMapping("/search")
    public ResponseEntity<ApiResponse> searchEquipment(@RequestParam("status") EquipmentStatus status, @RequestParam("site") String site){
        ApiResponse apiResponse = equipmentService.searchEquipment(status, site);
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }

}
