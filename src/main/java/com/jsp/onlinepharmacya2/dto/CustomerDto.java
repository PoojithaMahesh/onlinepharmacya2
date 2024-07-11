package com.jsp.onlinepharmacya2.dto;

import java.util.List;

import com.jsp.onlinepharmacya2.entity.Address;
import com.jsp.onlinepharmacya2.entity.Booking;

import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerDto {
	private int customerId;
	private String customerName;
	private String customerEmail;
	
	@OneToMany
	private List<BookingDto> bookingDtos;
	
	@OneToMany
	private List<AddressDto> addressDtos;
	
}
