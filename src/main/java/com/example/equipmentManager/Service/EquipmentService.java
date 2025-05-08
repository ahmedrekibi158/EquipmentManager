package com.example.equipmentManager.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.equipmentManager.DTO.EquipmentDto;
import com.example.equipmentManager.Enums.EquipmentStatus;
import com.example.equipmentManager.Model.Equipment;
import com.example.equipmentManager.Repository.EquipmentRepository;
@Service
public class EquipmentService {
    private final EquipmentRepository equipmentRepository;
    private EquipmentService(EquipmentRepository equipmentRepository) {
        this.equipmentRepository = equipmentRepository;
    }

    public List<Equipment> getAllEquipment(){
        List<Equipment> equipmentList = equipmentRepository.findAll();
        //equipmentRepository.findAll().forEach(equipmentList::add);
        return equipmentList;
    }

    public Equipment getEquipmentById(long id) {
        Optional<Equipment> equipment = equipmentRepository.findById(id);
        if (equipment.isPresent())
            return equipment.get();
        else
            return null;
    }

    public void deleteEquipment(long id) {
        equipmentRepository.deleteById(id);
    }

    public String addEquipment(EquipmentDto equip){
        Equipment equipment = new Equipment();
        equipment.setName(equip.getName());
        equipment.setSerialNumber(equip.getSerialNumber());  
        LocalDate localDate = LocalDate.parse(equip.getLocalDate());  
        equipment.setLocalDate(localDate);
        equipment.setEquipmentStatus(equip.getEquipmentStatus());
        equipment.setSite(equip.getSite());
        equipmentRepository.save(equipment);
        return "Equipment added successfully";
    }
    public String updateEquipment(EquipmentDto equip, long id){
        Optional<Equipment> existingEquipment = equipmentRepository.findById(id);
        if (!existingEquipment.isPresent()) {
            return "Equipment not found";
        }
        Equipment existingEquip = existingEquipment.get();
        existingEquip.setName(equip.getName());
        existingEquip.setSerialNumber(equip.getSerialNumber()); 
        LocalDate localDate = LocalDate.parse(equip.getLocalDate());
        existingEquip.setLocalDate(localDate);
        existingEquip.setEquipmentStatus(equip.getEquipmentStatus());
        existingEquip.setSite(equip.getSite());
        equipmentRepository.save(existingEquip);
        return "Equipment updated successfully";
    }

    public List<Equipment> searchEquipment(EquipmentStatus status, String site){
        List<Equipment> equipmentList = equipmentRepository.findByEquipmentStatusAndSite(status, site);
        return equipmentList;
    }
    
    
}
