package com.jsp.onlinepharmacya2.dao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jsp.onlinepharmacya2.entity.Booking;
import com.jsp.onlinepharmacya2.repo.BookingRepo;

@Repository
public class BookingDao {
@Autowired
private BookingRepo repo;

public Booking savebookings(Booking booking) {
	// TODO Auto-generated method stub
	return repo.save(booking);
}

public Booking findBooking(int bookingId) {
	Optional<Booking> optional=repo.findById(bookingId);
	if(optional.isPresent()) {
		return optional.get();
	}else {
		return null;
	}
}

public Booking deleteBooking(int bookingId) {
	Optional<Booking> optional=repo.findById(bookingId);
	if(optional.isPresent()) {
		repo.deleteById(bookingId);
		return optional.get();
	}else {
		return null;
	}

	
}
}
