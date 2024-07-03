package com.jsp.onlinepharmacya2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jsp.onlinepharmacya2.dao.AdminDao;

@Service
public class AdminService {

	@Autowired
	private AdminDao dao;
}
