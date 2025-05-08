package com.example.equipmentManager.Repository;
import java.util.Optional;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.equipmentManager.Enums.EquipmentStatus;
import com.example.equipmentManager.Model.Equipment;

@Repository
public interface EquipmentRepository extends CrudRepository<Equipment, Long>{
    
    Optional<Equipment> findBySerialNumber(String serialNumber);
    List<Equipment> findAll();
    //List<Equipment> findByEquipmentStatusAndSite(String status, String site);
    List<Equipment> findByEquipmentStatusAndSite(EquipmentStatus status, String site);

}
