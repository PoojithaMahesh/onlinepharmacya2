package com.jsp.onlinepharmacya2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jsp.onlinepharmacya2.dao.AdminDao;
import com.jsp.onlinepharmacya2.dto.AdminDto;
import com.jsp.onlinepharmacya2.entity.Admin;
import com.jsp.onlinepharmacya2.exception.AdminIdNotFoundException;
import com.jsp.onlinepharmacya2.exception.FailedToResetThePasswordException;
import com.jsp.onlinepharmacya2.exception.LoginFailureException;
import com.jsp.onlinepharmacya2.util.ResponseStructure;

@Service
public class AdminService {

	@Autowired
	private AdminDao dao;

	public ResponseEntity<ResponseStructure<AdminDto>> signUpAdmin(Admin admin) {
		Admin dbAdmin=dao.saveAdmin(admin);
		
		AdminDto adminDto=new AdminDto();
		adminDto.setAdminId(dbAdmin.getAdminId());
		adminDto.setAdminName(dbAdmin.getAdminName());
		adminDto.setAdminEmail(dbAdmin.getAdminEmail());
		
		
		ResponseStructure<AdminDto> structure=new ResponseStructure<>();
		structure.setMessage("Admin SignedUp Successfully");
		structure.setHttpStatus(HttpStatus.CREATED.value());
		structure.setData(adminDto);
		
		return new ResponseEntity<ResponseStructure<AdminDto>>(structure,HttpStatus.CREATED);
		
		
		
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

	public ResponseEntity<ResponseStructure<Admin>> loginAdmin(String email, String password) {
		Admin dbAdmin=dao.findAdminByEmail(email);
		if(dbAdmin!=null) {
//			admin is present
			
//			now check your password
			if(password.equals(dbAdmin.getPassword())) {
//				it is a valid email LOGIN SUccess
				
				ResponseStructure<Admin> structure=new ResponseStructure<>();
				structure.setMessage("Admin LOGGEDIN Successfully");
				structure.setHttpStatus(HttpStatus.OK.value());
				structure.setData(dbAdmin);
				
				return new ResponseEntity<ResponseStructure<Admin>>(structure,HttpStatus.OK);
				
				
			}else {
//				admin password is incorrect
//				invalid password
				throw new LoginFailureException("INVALID PASSWORD!!!!!!!!!");
			}
		}else {
//			admin is not present with this email
//			invalid email
			throw new LoginFailureException("INVALID EMAILLLLLLL!!!!");
		}
	
	}

	public ResponseEntity<ResponseStructure<Admin>> resetPassword(String email, String newpassword, long phone) {
		Admin dbAdmin=dao.findAdminByEmail(email);
		if(dbAdmin!=null) {
//			admin is present with this email
			
			if(dbAdmin.getPhoneNumber()==phone) {
//				yes its the exact admin
				
//				we can update the password
				dbAdmin.setPassword(newpassword);
				Admin updatedAdminDetails=dao.updateAdmin(dbAdmin.getAdminId(), dbAdmin);
				ResponseStructure<Admin> structure=new ResponseStructure<>();
				structure.setMessage("Admin Password resets Successfully");
				structure.setHttpStatus(HttpStatus.OK.value());
				structure.setData(updatedAdminDetails);
				
				return new ResponseEntity<ResponseStructure<Admin>>(structure,HttpStatus.OK);
			}else {
				
				throw new FailedToResetThePasswordException("INVALID PHONENUMBER");
			}
		}else {
//			admin email is not present
			throw new FailedToResetThePasswordException("INVALID EMAIL");
		}
		
		
		
		
		
		
	}
}
