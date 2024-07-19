package com.jsp.onlinepharmacya2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jsp.onlinepharmacya2.dto.BookingDto;
import com.jsp.onlinepharmacya2.entity.Booking;
import com.jsp.onlinepharmacya2.service.BookingService;
import com.jsp.onlinepharmacya2.util.ResponseStructure;

@RestController
@RequestMapping
public class BookingController {

	@Autowired
	private BookingService service;
	
	@PostMapping
	public ResponseEntity<ResponseStructure<BookingDto>> addBooking(@RequestParam int customerId,@RequestParam int medicineId,
			@RequestBody Booking booking){
		return service.addBooking(customerId,medicineId,booking);
	}
	@DeleteMapping
	public ResponseEntity<ResponseStructure<BookingDto>> cancelBooking(@RequestParam int bookingId){
		return service.cancelBooking(bookingId);
		
	}
	
}
