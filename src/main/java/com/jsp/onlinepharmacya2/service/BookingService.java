package com.jsp.onlinepharmacya2.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jsp.onlinepharmacya2.dao.BookingDao;
import com.jsp.onlinepharmacya2.dao.CustomerDao;
import com.jsp.onlinepharmacya2.dao.MedicineDao;
import com.jsp.onlinepharmacya2.dto.BookingDto;
import com.jsp.onlinepharmacya2.dto.CustomerDto;
import com.jsp.onlinepharmacya2.dto.MedicineDto;
import com.jsp.onlinepharmacya2.entity.Booking;
import com.jsp.onlinepharmacya2.entity.Customer;
import com.jsp.onlinepharmacya2.entity.Medicines;
import com.jsp.onlinepharmacya2.enums.BookingStatus;
import com.jsp.onlinepharmacya2.exception.BookingAlreadyCancelledException;
import com.jsp.onlinepharmacya2.exception.BookingAlreadyDeliveredException;
import com.jsp.onlinepharmacya2.exception.CantCancelledException;
import com.jsp.onlinepharmacya2.exception.CustomerIdNotFoundException;
import com.jsp.onlinepharmacya2.exception.MedicineIdNotFoundException;
import com.jsp.onlinepharmacya2.util.ResponseStructure;

@Service
public class BookingService {

	@Autowired
	private BookingDao dao;
	@Autowired
	private CustomerDao customerDao;
	
	@Autowired
	private MedicineDao medicineDao;
	@Autowired
	private ModelMapper mapper;

	public ResponseEntity<ResponseStructure<BookingDto>> addBooking(int customerId, int medicineId, Booking booking) {
		Customer dbCustomer=customerDao.findCustomerByid(customerId);
		if(dbCustomer!=null) {
			Medicines dbMedicines=medicineDao.findMedicines(medicineId);
			if(dbMedicines!=null) {
				LocalDate orderdate=LocalDate.now();
				booking.setOrderDate(orderdate);
				LocalDate expectedDate=LocalDate.now().plusDays(7);
				booking.setExpectedDate(expectedDate);
				booking.setBookingStatus(BookingStatus.ACTIVE);
				
				booking.setCustomer(dbCustomer);
				List<Medicines> list=new ArrayList<Medicines>();
				list.add(dbMedicines);
				booking.setMedicines(list);
				
				Booking dbBooking=dao.savebookings(booking);
//				Update the customer details
				List<Booking> bookings=dbCustomer.getBookings();
				bookings.add(dbBooking);
				dbCustomer.setBookings(bookings);
				customerDao.updateCustomer(dbCustomer.getCustomerId(),dbCustomer);
				BookingDto bookingDto=this.mapper.map(dbBooking, BookingDto.class);
				
				List<Medicines> medicines=new ArrayList<Medicines>();
				List<MedicineDto> medicineDtos=new ArrayList<MedicineDto>();
				for(Medicines med:medicines) {
					MedicineDto medicineDto=this.mapper.map(med, MedicineDto.class);
					medicineDtos.add(medicineDto);
				}
				
				bookingDto.setMedicineDtos(medicineDtos);
				bookingDto.setCustomerDto(this.mapper.map(dbBooking.getCustomer(), CustomerDto.class));
				
				
				ResponseStructure<BookingDto> structure=new ResponseStructure<>();
				structure.setMessage("Booking done successfully");
				structure.setHttpStatus(HttpStatus.CREATED.value());
				structure.setData(bookingDto);
				return new ResponseEntity<ResponseStructure<BookingDto>>(structure,HttpStatus.CREATED);
				
				
				
			}else {
				
				throw new MedicineIdNotFoundException("Sorry failed to add Booking");
			}
			
		}else {
			throw new CustomerIdNotFoundException("Sorry failed to add Boooking");
		}
	}

	public ResponseEntity<ResponseStructure<BookingDto>> cancelBooking(int bookingId) {
		Booking dbBooking=dao.findBooking(bookingId);
		LocalDate cantcancelDate=dbBooking.getExpectedDate().minusDays(2);
		if(dbBooking.getBookingStatus().equals(BookingStatus.CANCEL)) {
			throw new BookingAlreadyCancelledException("Sorry failed to cancel booking");
		} else if(dbBooking.getBookingStatus().equals(BookingStatus.DELIVERED)){
			
			throw new BookingAlreadyDeliveredException("Sorry failed to cancel booking");
		} else if(LocalDate.now().equals(cantcancelDate)||LocalDate.now().isAfter(cantcancelDate)) {
			throw new CantCancelledException("Sorry failed to cancel Booking");
		} else {
			dao.deleteBooking(bookingId);
			BookingDto bookingDto=this.mapper.map(dbBooking, BookingDto.class);
			
			List<Medicines> medicines=new ArrayList<Medicines>();
			List<MedicineDto> medicineDtos=new ArrayList<MedicineDto>();
			for(Medicines med:medicines) {
				MedicineDto medicineDto=this.mapper.map(med, MedicineDto.class);
				medicineDtos.add(medicineDto);
			}
			
			bookingDto.setMedicineDtos(medicineDtos);
			bookingDto.setCustomerDto(this.mapper.map(dbBooking.getCustomer(), CustomerDto.class));
			
			
			ResponseStructure<BookingDto> structure=new ResponseStructure<>();
			structure.setMessage("Booking Cancelled successfully");
			structure.setHttpStatus(HttpStatus.FORBIDDEN.value());
			structure.setData(bookingDto);
			return new ResponseEntity<ResponseStructure<BookingDto>>(structure,HttpStatus.FORBIDDEN);
			
		}
	}
}
