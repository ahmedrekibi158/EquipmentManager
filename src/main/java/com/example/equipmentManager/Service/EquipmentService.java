package com.example.equipmentManager.Service;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.equipmentManager.DTO.ApiResponse;
import com.example.equipmentManager.DTO.EquipmentDto;
import com.example.equipmentManager.Enums.EquipmentStatus;
import com.example.equipmentManager.Exception.CustomException;
import com.example.equipmentManager.Exception.EquipmentNotExistException;
import com.example.equipmentManager.Exception.EquipmentNotFoundException;
import com.example.equipmentManager.Exception.InvalidInputException;
import com.example.equipmentManager.Exception.SerialNumberExistsException;
import com.example.equipmentManager.Model.Equipment;
import com.example.equipmentManager.Repository.EquipmentRepository;

@Service
public class EquipmentService {
    private final EquipmentRepository equipmentRepository;
    private EquipmentService(EquipmentRepository equipmentRepository) {
        this.equipmentRepository = equipmentRepository;
    }

    public ApiResponse<List<Equipment>> getAllEquipment(){
        List<Equipment> equipmentList = equipmentRepository.findAll();
        //equipmentRepository.findAll().forEach(equipmentList::add);
        if(equipmentList.isEmpty()){
            System.out.println("No equipment found");
            throw new EquipmentNotFoundException("No equipment found");
        }
        else{
            System.out.println("Equipments found: " + equipmentList);
            return new ApiResponse<>(true, "Equipments found", equipmentList);
        }
    }

    public ApiResponse<Equipment> getEquipmentById(long id) {
        Optional<Equipment> equipment = equipmentRepository.findById(id);
        if (equipment.isPresent())
            return new ApiResponse<>(true, "Equipment found", equipment.get());
        else
            throw new CustomException("Equipment not found");
    }


    public void deleteEquipment(long id) {
        Optional<Equipment> equipment = equipmentRepository.findById(id);
        if (!equipment.isPresent()) {
            throw new CustomException("Equipment not found");
        }
        else{
            equipmentRepository.deleteById(id);
            System.out.println("Equipment deleted successfully");
        }
    }

    public ApiResponse addEquipment(EquipmentDto equip){
        Equipment equipment = new Equipment();
        equipment.setName(equip.getName());
        equipment.setSerialNumber(equip.getSerialNumber());  
        if(equipmentRepository.findBySerialNumber(equip.getSerialNumber()).isPresent()){
            throw new SerialNumberExistsException("Serial number already exists");
        }
        try{
            LocalDate localDate = LocalDate.parse(equip.getLocalDate()); 
            if (localDate.isAfter(LocalDate.now())) {
                throw new InvalidInputException("Date cannot be in the future");
            }
            equipment.setLocalDate(localDate);
        }
        catch (DateTimeParseException e){
            throw new DateTimeParseException("Invalid date format", equip.getLocalDate(), 0);
        }
        equipment.setEquipmentStatus(equip.getEquipmentStatus());
        equipment.setSite(equip.getSite());
        equipmentRepository.save(equipment);
        return new ApiResponse(true, "Equipment added successfully", equipment);
    }
    
    public ApiResponse updateEquipment(EquipmentDto equip, long id) {
    // Check if equipment with given ID exists
    Equipment existingEquipment = equipmentRepository.findById(id)
            .orElseThrow(() -> new EquipmentNotExistException("Equipment with ID " + id + " not exists"));

    // Validate serial number uniqueness (ensure it's not taken by another equipment)
    Optional<Equipment> serialConflict = equipmentRepository.findBySerialNumber(equip.getSerialNumber());
    if (serialConflict.isPresent() && serialConflict.get().getId() != id) {
        throw new SerialNumberExistsException("Serial number already exists for another equipment");
    }

    // Parse and validate date
    try {
        LocalDate localDate = LocalDate.parse(equip.getLocalDate());
        if (localDate.isAfter(LocalDate.now())) {
            throw new InvalidInputException("Date cannot be in the future");
        }
        existingEquipment.setLocalDate(localDate);
    } catch (DateTimeParseException e) {
        throw new InvalidInputException("Invalid date format: " + equip.getLocalDate());
    }

    // Set other fields
    existingEquipment.setName(equip.getName());
    existingEquipment.setSerialNumber(equip.getSerialNumber());
    existingEquipment.setEquipmentStatus(equip.getEquipmentStatus());
    existingEquipment.setSite(equip.getSite());

    // Save updates
    equipmentRepository.save(existingEquipment);

    return new ApiResponse(true, "Equipment updated successfully", existingEquipment);
}


    public ApiResponse searchEquipment(EquipmentStatus status, String site){
        if (status == null || site == null) {
            throw new InvalidInputException("At least one search parameter (status or site) must be provided");
        }
        site = site.trim();
        List<Equipment> equipmentList = equipmentRepository.searchByStatusAndSite(status, site);
        if (equipmentList.isEmpty()) {
            throw new EquipmentNotFoundException("No equipment found with the given criteria");
        }
        return new ApiResponse(true, "Equipment found", equipmentList);
    }
    
    
}
