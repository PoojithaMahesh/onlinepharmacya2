package com.jsp.onlinepharmacya2.dto;

import java.time.LocalDate;
import java.util.List;

import com.jsp.onlinepharmacya2.entity.Customer;
import com.jsp.onlinepharmacya2.entity.Medicines;

import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookingDto {
	private int bookingId;
	private LocalDate orderDate;
	private int quantity;
	private String paymentMode;
	private LocalDate expectedDate;
	
	@ManyToMany
	private List<MedicineDto> medicineDtos;
	
	@ManyToOne
	private CustomerDto customerDto;
	
}
