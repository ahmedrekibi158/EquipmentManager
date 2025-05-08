package com.example.equipmentManager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class EquipmentManagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(EquipmentManagerApplication.class, args);
		System.out.println("Hello, Equipment Manager!");
	}

}
