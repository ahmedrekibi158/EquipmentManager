package com.example.equipmentManager.Model;

import java.time.LocalDate;


import com.example.equipmentManager.Enums.EquipmentStatus;

import jakarta.validation.constraints.*;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Equipment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank
    private String name;

    @NotBlank
    private String serialNumber;
    private LocalDate localDate;
    
    @Enumerated(EnumType.STRING)
    private EquipmentStatus equipmentStatus;
    @NotBlank
    private String site;
}