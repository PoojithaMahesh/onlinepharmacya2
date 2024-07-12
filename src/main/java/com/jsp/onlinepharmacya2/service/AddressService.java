package com.jsp.onlinepharmacya2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jsp.onlinepharmacya2.dao.AddressDao;
import com.jsp.onlinepharmacya2.dto.AddressDto;
import com.jsp.onlinepharmacya2.entity.Address;
import com.jsp.onlinepharmacya2.exception.AddressIdNotFoundException;
import com.jsp.onlinepharmacya2.util.ResponseStructure;

@Service
public class AddressService {

	@Autowired
	private AddressDao dao;
	@Autowired
	private AddressDto addressDto;

	public ResponseEntity<ResponseStructure<AddressDto>> saveAddress(Address address) {
		Address dbAddress=dao.saveAddress(address);
		addressDto.setAddressId(dbAddress.getAddressId());
		addressDto.setCity(dbAddress.getCity());
		addressDto.setPincode(dbAddress.getPincode());
		addressDto.setState(dbAddress.getState());
		addressDto.setStreetName(dbAddress.getStreetName());
		
		
		ResponseStructure<AddressDto> structure=new ResponseStructure<>();
		structure.setMessage("Address data saved successfully");
		structure.setHttpStatus(HttpStatus.CREATED.value());
		structure.setData(addressDto);
     	return new ResponseEntity<ResponseStructure<AddressDto>>(structure,HttpStatus.CREATED);
	}

	public ResponseEntity<ResponseStructure<AddressDto>> updateAddress(int addressId, Address address) {
		Address dbAddress=dao.updateAddress(addressId,address);
		if(dbAddress!=null) {
//			id is present
			addressDto.setAddressId(dbAddress.getAddressId());
			addressDto.setCity(dbAddress.getCity());
			addressDto.setPincode(dbAddress.getPincode());
			addressDto.setState(dbAddress.getState());
			addressDto.setStreetName(dbAddress.getStreetName());
			ResponseStructure<AddressDto> structure=new ResponseStructure<>();
			structure.setMessage("Address data updated successfully");
			structure.setHttpStatus(HttpStatus.OK.value());
			structure.setData(addressDto);
	     	return new ResponseEntity<ResponseStructure<AddressDto>>(structure,HttpStatus.OK);
		}else {
			throw new AddressIdNotFoundException("Sorry failed to update Address");
		}
	}

	public ResponseEntity<ResponseStructure<AddressDto>> findAddress(int addressId) {
		Address dbAddress=dao.findAddressById(addressId);
		if(dbAddress!=null) {
//			id is present
			addressDto.setAddressId(dbAddress.getAddressId());
			addressDto.setCity(dbAddress.getCity());
			addressDto.setPincode(dbAddress.getPincode());
			addressDto.setState(dbAddress.getState());
			addressDto.setStreetName(dbAddress.getStreetName());
			ResponseStructure<AddressDto> structure=new ResponseStructure<>();
			structure.setMessage("Address data fetched successfully");
			structure.setHttpStatus(HttpStatus.FOUND.value());
			structure.setData(addressDto);
	     	return new ResponseEntity<ResponseStructure<AddressDto>>(structure,HttpStatus.FOUND);
		}else {
			throw new AddressIdNotFoundException("Sorry failed to fetch Address");
		}
	}

	public ResponseEntity<ResponseStructure<AddressDto>> deleteAddress(int addressId) {
		Address dbAddress=dao.deleteAddress(addressId);
		if(dbAddress!=null) {
//			id is present
			addressDto.setAddressId(dbAddress.getAddressId());
			addressDto.setCity(dbAddress.getCity());
			addressDto.setPincode(dbAddress.getPincode());
			addressDto.setState(dbAddress.getState());
			addressDto.setStreetName(dbAddress.getStreetName());
			ResponseStructure<AddressDto> structure=new ResponseStructure<>();
			structure.setMessage("Address data deleted successfully");
			structure.setHttpStatus(HttpStatus.FORBIDDEN.value());
			structure.setData(addressDto);
	     	return new ResponseEntity<ResponseStructure<AddressDto>>(structure,HttpStatus.FORBIDDEN);
		}else {
			throw new AddressIdNotFoundException("Sorry failed to delete Address");
		}
	}
	
	
}
