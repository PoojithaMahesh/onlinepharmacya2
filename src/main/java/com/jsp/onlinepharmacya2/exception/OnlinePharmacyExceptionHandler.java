package com.jsp.onlinepharmacya2.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.jsp.onlinepharmacya2.util.ResponseStructure;

@RestControllerAdvice
public class OnlinePharmacyExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler
	public ResponseEntity<ResponseStructure<String>> handleAdminIdNotFoundException(AdminIdNotFoundException exception){
		
		ResponseStructure<String> structure=new ResponseStructure<>();
		structure.setMessage("Admin id is not present");
		structure.setHttpStatus(HttpStatus.NOT_FOUND.value());
		structure.setData(exception.getMessage());
		
		return new ResponseEntity<ResponseStructure<String>>(structure,HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler
	public ResponseEntity<ResponseStructure<String>> handleBookingalreadycancelledexception(BookingAlreadyCancelledException exception){
		
		ResponseStructure<String> structure=new ResponseStructure<>();
		structure.setMessage("Booking Already Cancelled");
		structure.setHttpStatus(HttpStatus.NOT_FOUND.value());
		structure.setData(exception.getMessage());
		
		return new ResponseEntity<ResponseStructure<String>>(structure,HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler
	public ResponseEntity<ResponseStructure<String>> handleBookingalreadydelieveredexception(BookingAlreadyDeliveredException exception){
		
		ResponseStructure<String> structure=new ResponseStructure<>();
		structure.setMessage("Booking Already Delivered");
		structure.setHttpStatus(HttpStatus.NOT_FOUND.value());
		structure.setData(exception.getMessage());
		
		return new ResponseEntity<ResponseStructure<String>>(structure,HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler
	public ResponseEntity<ResponseStructure<String>> handlecantcancelDateexception(CantCancelledException exception){
		
		ResponseStructure<String> structure=new ResponseStructure<>();
		structure.setMessage("ITS A TIME");
		structure.setHttpStatus(HttpStatus.NOT_FOUND.value());
		structure.setData(exception.getMessage());
		
		return new ResponseEntity<ResponseStructure<String>>(structure,HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler
	public ResponseEntity<ResponseStructure<String>> handleCustomerIdNotFoundException(CustomerIdNotFoundException exception){
		
		ResponseStructure<String> structure=new ResponseStructure<>();
		structure.setMessage("CUstomer id is not present");
		structure.setHttpStatus(HttpStatus.NOT_FOUND.value());
		structure.setData(exception.getMessage());
		
		return new ResponseEntity<ResponseStructure<String>>(structure,HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler
	public ResponseEntity<ResponseStructure<String>> handleMedicineIdNotFoundException(MedicineIdNotFoundException exception){
		
		ResponseStructure<String> structure=new ResponseStructure<>();
		structure.setMessage("Medicine id is not present");
		structure.setHttpStatus(HttpStatus.NOT_FOUND.value());
		structure.setData(exception.getMessage());
		
		return new ResponseEntity<ResponseStructure<String>>(structure,HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler
	public ResponseEntity<ResponseStructure<String>> handleMedicinNameNotFoundException(MedicineNameNotFoundException exception){
		
		ResponseStructure<String> structure=new ResponseStructure<>();
		structure.setMessage("Medicine name is not present");
		structure.setHttpStatus(HttpStatus.NOT_FOUND.value());
		structure.setData(exception.getMessage());
		
		return new ResponseEntity<ResponseStructure<String>>(structure,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler
	public ResponseEntity<ResponseStructure<String>> handleStaffIdNotFoundException(StaffIdNotFoundException exception){
		
		ResponseStructure<String> structure=new ResponseStructure<>();
		structure.setMessage("Staff id is not present");
		structure.setHttpStatus(HttpStatus.NOT_FOUND.value());
		structure.setData(exception.getMessage());
		
		return new ResponseEntity<ResponseStructure<String>>(structure,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler
	public ResponseEntity<ResponseStructure<String>> handleStoreIdNotFoundException(MedicalStoreIdNotPresentException exception){
		
		ResponseStructure<String> structure=new ResponseStructure<>();
		structure.setMessage("MEDICALSTORE id is not present");
		structure.setHttpStatus(HttpStatus.NOT_FOUND.value());
		structure.setData(exception.getMessage());
		
		return new ResponseEntity<ResponseStructure<String>>(structure,HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler
	public ResponseEntity<ResponseStructure<String>> handleLoginFailure(LoginFailureException exception){
		
		ResponseStructure<String> structure=new ResponseStructure<>();
		structure.setMessage("SORRY FAILED TO LOGIN");
		structure.setHttpStatus(HttpStatus.NOT_FOUND.value());
		structure.setData(exception.getMessage());
		
		return new ResponseEntity<ResponseStructure<String>>(structure,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler
	public ResponseEntity<ResponseStructure<String>> handleresetPassword(FailedToResetThePasswordException exception){
		
		ResponseStructure<String> structure=new ResponseStructure<>();
		structure.setMessage("SORRY FAILED TO RESETThePASSWORD");
		structure.setHttpStatus(HttpStatus.NOT_FOUND.value());
		structure.setData(exception.getMessage());
		
		return new ResponseEntity<ResponseStructure<String>>(structure,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler
	public ResponseEntity<ResponseStructure<String>> handleAddressIdNotFoundException(AddressIdNotFoundException exception){
		
		ResponseStructure<String> structure=new ResponseStructure<>();
		structure.setMessage("Address ID is not found");
		structure.setHttpStatus(HttpStatus.NOT_FOUND.value());
		structure.setData(exception.getMessage());
		
		return new ResponseEntity<ResponseStructure<String>>(structure,HttpStatus.NOT_FOUND);
	}
	
}
