package com.jsp.onlinepharmacya2.dao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jsp.onlinepharmacya2.dto.Admin;
import com.jsp.onlinepharmacya2.repo.AdminRepo;

@Repository
public class AdminDao {

	@Autowired
	private AdminRepo repo;

	public Admin saveAdmin(Admin admin) {
		
		return repo.save(admin);
	}

	public Admin updateAdmin(int adminId, Admin admin) {
//	to update u need to check whether that pk is present or not 
//		if it is present then you can update the data
//		if it is not present then you can return null;
		
		Optional<Admin> optional=repo.findById(adminId);
		
		if(optional.isPresent()) {	
//			id is present i need to update the data
			admin.setAdminId(adminId);
		    return repo.save(admin);
		}else {
			return null;
		}
	}

	public Admin findAdmin(int adminId) {
		Optional<Admin> optional=repo.findById(adminId);
		if(optional.isPresent()) {
//			id is present i need to return that admin
			Admin dbAdmin=optional.get();
			return dbAdmin;
		}else {
//			id is not present
			return null;
		}
	}

	public Admin deleteAdmin(int adminId) {
		Optional<Admin> optional=repo.findById(adminId);
		if(optional.isPresent()) {
			Admin admin=optional.get();
			repo.deleteById(adminId);
			return  admin;
		}else {
			return null;
		}
	}

	public Admin findAdminByEmail(String email) {
		Optional<Admin> optional=repo.findByEmail(email);
		if(optional.isPresent()) {
//			that admin is present in this email
			 
			return optional.get();
		}else {
//			admin is not present
			return null;
		}
	}
	
}
