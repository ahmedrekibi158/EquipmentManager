package com.example.equipmentManager.DTO;


import com.example.equipmentManager.Enums.EquipmentStatus;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EquipmentDto {
    @NotBlank(message = "Name is required")
    private String name;
    @NotBlank(message = "Serial number is required")
    private String serialNumber;
    @NotBlank(message = "Local date is required")
    private String localDate;
    @NotNull(message = "Equipment status is required")
    private EquipmentStatus equipmentStatus;
    @NotNull(message = "Site is required")    
    private String site;
}
