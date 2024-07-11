package com.jsp.onlinepharmacya2.dto;

import java.time.LocalDate;

import com.jsp.onlinepharmacya2.entity.MedicalStore;

import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MedicineDto {
	private int medicineId;
	private String medicineName;
	private double cost;
	private LocalDate expiryDate;
	private int stockQuantity;
	private String manufacturer;
	private String description;
	
	@ManyToOne
	private MedicalStoreDto medicalStoreDto;
	
}
