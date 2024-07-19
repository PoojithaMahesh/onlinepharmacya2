package com.jsp.onlinepharmacya2.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jsp.onlinepharmacya2.entity.Customer;

public interface CustomerRepo extends JpaRepository<Customer, Integer> {

}
