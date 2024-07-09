package com.jsp.onlinepharmacya2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jsp.onlinepharmacya2.dto.Admin;
import com.jsp.onlinepharmacya2.service.AdminService;
import com.jsp.onlinepharmacya2.util.ResponseStructure;

@RestController
public class AdminController {
	
	@Autowired
	private AdminService service;

	@PostMapping("/save")
	public ResponseEntity<ResponseStructure<Admin>> signupAdmin(@RequestBody Admin admin) {
		return service.signUpAdmin(admin);
	}
	
	@PutMapping("/update")
	public ResponseEntity<ResponseStructure<Admin>> updateAdmin(@RequestParam int adminId,@RequestBody Admin admin){
		return service.updateAdmin(adminId,admin);
	}
	
	@GetMapping("/find")
	public ResponseEntity<ResponseStructure<Admin>> findAdmin(@RequestParam int adminId){
		return service.findAdminById(adminId);
	}
	
	@DeleteMapping("/delete")
	public ResponseEntity<ResponseStructure<Admin>> deleteAdmin(@RequestParam int adminId){
		return service.deleteAdminById(adminId);
	}
	
	@PostMapping("/login")
    public ResponseEntity<ResponseStructure<Admin>> loginAdmin
    (@RequestParam String email,@RequestParam String password){
		return service.loginAdmin(email,password);
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
