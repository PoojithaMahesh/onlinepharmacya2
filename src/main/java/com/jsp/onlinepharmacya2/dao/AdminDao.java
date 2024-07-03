package com.jsp.onlinepharmacya2.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jsp.onlinepharmacya2.repo.AdminRepo;

@Repository
public class AdminDao {

	@Autowired
	private AdminRepo repo;
	
}
