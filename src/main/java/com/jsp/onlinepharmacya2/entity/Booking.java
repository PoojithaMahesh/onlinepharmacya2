package com.jsp.onlinepharmacya2.entity;

import java.time.LocalDate;
import java.util.List;

import com.jsp.onlinepharmacya2.enums.BookingStatus;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int bookingId;
	private LocalDate orderDate;
	private int quantity;
	private String paymentMode;
	private LocalDate expectedDate;
	private BookingStatus bookingStatus;
	
	@ManyToMany
	private List<Medicines> medicines;
	
	
	@ManyToOne
	private Customer customer;
	

}
