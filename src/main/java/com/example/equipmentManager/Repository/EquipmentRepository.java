package com.example.equipmentManager.Repository;
import java.util.Optional;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.equipmentManager.Enums.EquipmentStatus;
import com.example.equipmentManager.Model.Equipment;
import org.springframework.data.jpa.repository.Query;

@Repository
public interface EquipmentRepository extends CrudRepository<Equipment, Long>{
    
    Optional<Equipment> findBySerialNumber(String serialNumber);
    List<Equipment> findAll();
    //List<Equipment> findByEquipmentStatusAndSite(String status, String site);

    @Query("SELECT e from Equipment e where (e.equipmentStatus = null or e.equipmentStatus = :status) and (e.site = null or e.site = :site)")
    List<Equipment> searchByStatusAndSite(EquipmentStatus status, String site);

}
