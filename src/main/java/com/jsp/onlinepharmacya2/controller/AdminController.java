package com.jsp.onlinepharmacya2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.jsp.onlinepharmacya2.dto.Admin;
import com.jsp.onlinepharmacya2.service.AdminService;

@RestController
public class AdminController {
	
	@Autowired
	private AdminService service;

	@PostMapping("/save")
	public void signupAdmin(@RequestBody Admin admin) {
		
	}
}
