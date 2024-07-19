package com.jsp.onlinepharmacya2.dao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jsp.onlinepharmacya2.entity.Customer;
import com.jsp.onlinepharmacya2.repo.CustomerRepo;

@Repository
public class CustomerDao {

	@Autowired
	private CustomerRepo repo;

	public Customer saveCustomer(Customer customer) {
		return repo.save(customer);
	}

	public Customer findCustomerByid(int customerId) {
		Optional<Customer> optional=repo.findById(customerId);
		if(optional.isPresent()) {
			return optional.get();
		}else {
			return null;
		}
	}

	public Customer updateCustomer(int customerId, Customer dbCustomer) {
		Optional<Customer> optional=repo.findById(customerId);
		if(optional.isPresent()) {
			dbCustomer.setCustomerId(customerId);
			dbCustomer.setBookings(optional.get().getBookings());
			dbCustomer.setAddresses(optional.get().getAddresses());
			return repo.save(dbCustomer);
		}else {
			return null;
		}
	}
	
}
