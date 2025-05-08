package com.example.equipmentManager.DTO;


import com.example.equipmentManager.Enums.EquipmentStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EquipmentDto {
    private String name;
    private String serialNumber;
    private String localDate;
    private EquipmentStatus equipmentStatus;
    private String site;
}
