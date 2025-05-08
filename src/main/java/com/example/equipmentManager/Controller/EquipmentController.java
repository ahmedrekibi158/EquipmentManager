package com.example.equipmentManager.Controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.equipmentManager.Model.Equipment;
import com.example.equipmentManager.Service.EquipmentService;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.equipmentManager.DTO.EquipmentDto;
import com.example.equipmentManager.Enums.EquipmentStatus;


@RestController
public class EquipmentController {
    private final EquipmentService equipmentService;
    private EquipmentController(EquipmentService equipmentService) {
        this.equipmentService = equipmentService;
    }

    @GetMapping("/equipment")
    public List<Equipment> getEquipment() {
        List<Equipment> equipmentList = equipmentService.getAllEquipment();
        return equipmentList;
    }   

    @GetMapping("/equipment/{id}")
    public Equipment getEquipmentById(@PathVariable long id) {
        Equipment equipment = equipmentService.getEquipmentById(id);
        return equipment;
    } 
    @DeleteMapping("/equipment/{id}") 
    public void deleteEquipment(@PathVariable long id) {
        equipmentService.deleteEquipment(id);
    }
    @PostMapping("/addEquipment")
    public String addEquipment(@RequestBody EquipmentDto equipmentDto) {
        equipmentService.addEquipment(equipmentDto);
        return "Equipment added successfully";
    }
    @PostMapping("/updateEquipment/{id}")
    public String updateEquipment(@RequestBody EquipmentDto equipmentDto, @PathVariable long id) {
        equipmentService.updateEquipment(equipmentDto, id);
        return "Equipment updated successfully";
    }

    @GetMapping("/search")
    public List<Equipment> searchEquipment(@RequestParam("status") EquipmentStatus status, @RequestParam(value="site", required=false) String site){
        System.out.println("Status: " + status);
        System.out.println("Site: " + site);
        List<Equipment> equipmentList = equipmentService.searchEquipment(status, site);
        return equipmentList;
    }

}
