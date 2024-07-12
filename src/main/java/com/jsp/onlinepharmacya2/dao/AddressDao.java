package com.jsp.onlinepharmacya2.dao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jsp.onlinepharmacya2.entity.Address;
import com.jsp.onlinepharmacya2.repo.AddressRepo;

@Repository
public class AddressDao {
@Autowired
private AddressRepo repo;

public Address saveAddress(Address address) {
	return repo.save(address);
}

public Address updateAddress(int addressId, Address address) {
//	find that id is present or not
	Optional<Address> optional=repo.findById(addressId);
	if(optional.isPresent()) {
//		id is present
//		i can update the data
//		what are all the things that i need to set
		address.setAddressId(addressId);
//		you need to set the relationship entities also
		address.setMedicalStore(optional.get().getMedicalStore());
		address.setCustomer(optional.get().getCustomer());
		return repo.save(address);
		
	}else {
		return null;
	}
}

public Address findAddressById(int addressId) {
	Optional<Address> optional=repo.findById(addressId);
	if(optional.isPresent()) {
		return optional.get();
	}else {
		return null;
	}
}

public Address deleteAddress(int addressId) {
	Optional<Address> optional=repo.findById(addressId);
	if(optional.isPresent()) {
		repo.delete(optional.get());
		return optional.get();
	}else {
		return null;
	}
}

}
