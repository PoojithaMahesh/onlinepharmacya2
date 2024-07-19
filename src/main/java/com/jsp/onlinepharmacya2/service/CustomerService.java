package com.jsp.onlinepharmacya2.service;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jsp.onlinepharmacya2.dao.AddressDao;
import com.jsp.onlinepharmacya2.dao.CustomerDao;
import com.jsp.onlinepharmacya2.dto.AddressDto;
import com.jsp.onlinepharmacya2.dto.CustomerDto;
import com.jsp.onlinepharmacya2.entity.Address;
import com.jsp.onlinepharmacya2.entity.Customer;
import com.jsp.onlinepharmacya2.exception.AddressIdNotFoundException;
import com.jsp.onlinepharmacya2.util.ResponseStructure;

@Service
public class CustomerService {

	@Autowired
	private CustomerDao dao;
	@Autowired
	private AddressDao addressDao;
	@Autowired
	private ModelMapper mapper;

	public ResponseEntity<ResponseStructure<CustomerDto>> signUpCustomer(int addressId, Customer customer) {
		Address dbAddress=addressDao.findAddressById(addressId);
		if(dbAddress!=null) {
//			address is present then i cam save the customer
			List<Address> addresses=new ArrayList<Address>();
			addresses.add(dbAddress);
			customer.setAddresses(addresses);
			Customer dbCustomer=dao.saveCustomer(customer);
			
			CustomerDto customerDto=this.mapper.map(dbCustomer, CustomerDto.class);
			
			
			List<Address> list=dbCustomer.getAddresses();
			
			List<AddressDto> addressDtos=new ArrayList<AddressDto>();
			for(Address address:list) {
				AddressDto addressDto=this.mapper.map(address, AddressDto.class);
				addressDtos.add(addressDto);
			}
			
			ResponseStructure<CustomerDto> structure=new ResponseStructure<>();
			structure.setMessage("Data saved successfully");
			structure.setHttpStatus(HttpStatus.CREATED.value());
			structure.setData(customerDto);
			return new ResponseEntity<ResponseStructure<CustomerDto>>(structure,HttpStatus.CREATED);
		
		}else {
//			address is not present
			throw new AddressIdNotFoundException("Sorry failed to signup Customer");
		}
	}
}
