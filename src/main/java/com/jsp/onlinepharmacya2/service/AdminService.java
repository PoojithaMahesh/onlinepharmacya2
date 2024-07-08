package com.jsp.onlinepharmacya2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jsp.onlinepharmacya2.dao.AdminDao;
import com.jsp.onlinepharmacya2.dto.Admin;
import com.jsp.onlinepharmacya2.exception.AdminIdNotFoundException;
import com.jsp.onlinepharmacya2.util.ResponseStructure;

@Service
public class AdminService {

	@Autowired
	private AdminDao dao;

	public ResponseEntity<ResponseStructure<Admin>> signUpAdmin(Admin admin) {
		Admin dbAdmin=dao.saveAdmin(admin);
		
		ResponseStructure<Admin> structure=new ResponseStructure<>();
		structure.setMessage("Admin SignedUp Successfully");
		structure.setHttpStatus(HttpStatus.CREATED.value());
		structure.setData(dbAdmin);
		
		return new ResponseEntity<ResponseStructure<Admin>>(structure,HttpStatus.CREATED);
		
		
		
	}

	public ResponseEntity<ResponseStructure<Admin>> updateAdmin(int adminId, Admin admin) {
		Admin dbAdmin=dao.updateAdmin(adminId,admin);
		if(dbAdmin!=null) {
//			id is present then we updated the data
			ResponseStructure<Admin> structure=new ResponseStructure<>();
			structure.setMessage("Admin updated Successfully");
			structure.setHttpStatus(HttpStatus.OK.value());
			structure.setData(dbAdmin);
			
			return new ResponseEntity<ResponseStructure<Admin>>(structure,HttpStatus.OK);
			
		}else {
//			id is not present
			throw new AdminIdNotFoundException("Sorry failed to update Admin details");
		}
		
		
	}

	public ResponseEntity<ResponseStructure<Admin>> findAdminById(int adminId) {
		Admin dbAdmin=dao.findAdmin(adminId);
		if(dbAdmin!=null) {
//			id is present
			
			ResponseStructure<Admin> structure=new ResponseStructure<>();
			structure.setMessage("Admin fetched Successfully");
			structure.setHttpStatus(HttpStatus.FOUND.value());
			structure.setData(dbAdmin);
			
			return new ResponseEntity<ResponseStructure<Admin>>(structure,HttpStatus.FOUND);
		
		}else {
//			id is not present
//			raise the exception
			throw new AdminIdNotFoundException("Sorry failed to fetch the admin details");
		}
	}

	public ResponseEntity<ResponseStructure<Admin>> deleteAdminById(int adminId) {
		Admin dbAdmin=dao.deleteAdmin(adminId);
		if(dbAdmin!=null) {
//			id is present
			
			ResponseStructure<Admin> structure=new ResponseStructure<>();
			structure.setMessage("Admin deleted Successfully");
			structure.setHttpStatus(HttpStatus.FORBIDDEN.value());
			structure.setData(dbAdmin);
			
			return new ResponseEntity<ResponseStructure<Admin>>(structure,HttpStatus.FORBIDDEN);
		
		}else {
//			id is not present
//			raise the exception
			throw new AdminIdNotFoundException("Sorry failed to delete the admin details");
		}
	}
}
