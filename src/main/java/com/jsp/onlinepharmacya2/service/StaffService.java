package com.jsp.onlinepharmacya2.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jsp.onlinepharmacya2.dao.AdminDao;
import com.jsp.onlinepharmacya2.dao.MedicalStoreDao;
import com.jsp.onlinepharmacya2.dao.StaffDao;
import com.jsp.onlinepharmacya2.dto.AdminDto;
import com.jsp.onlinepharmacya2.dto.MedicalStoreDto;
import com.jsp.onlinepharmacya2.dto.StaffDto;
import com.jsp.onlinepharmacya2.entity.Admin;
import com.jsp.onlinepharmacya2.entity.MedicalStore;
import com.jsp.onlinepharmacya2.entity.Staff;
import com.jsp.onlinepharmacya2.exception.AdminIdNotFoundException;
import com.jsp.onlinepharmacya2.exception.MedicalStoreIdNotPresentException;
import com.jsp.onlinepharmacya2.exception.StaffIdNotFoundException;
import com.jsp.onlinepharmacya2.util.ResponseStructure;

@Service
public class StaffService {

	@Autowired
	private StaffDao dao;
	@Autowired
	private AdminDao adminDao;
	@Autowired
	private ModelMapper mapper;

	@Autowired
	private MedicalStoreDao storeDao;
	public ResponseEntity<ResponseStructure<StaffDto>> signupStaff(int adminId, int storeId, Staff staff) {
		Admin dbAdmin=adminDao.findAdmin(adminId);
		if(dbAdmin!=null) {
			MedicalStore dbMedicalStore=storeDao.findMedicalStoreById(storeId);
			
			if(dbMedicalStore!=null) {
//				you can call signup staff method because admin is present and also medicalStore is present
				staff.setAdmin(dbAdmin);
				staff.setMedicalStore(dbMedicalStore);
				Staff dbStaff=dao.saveStaff(staff);
				
				StaffDto staffDto=this.mapper.map(dbStaff, StaffDto.class);
				
				AdminDto adminDto=this.mapper.map(dbStaff.getAdmin(), AdminDto.class);
				
				staffDto.setAdminDto(adminDto);
				
				MedicalStoreDto medicalStoreDto=this.mapper.map(dbStaff.getMedicalStore(), MedicalStoreDto.class);
				
				staffDto.setMedicalStoreDto(medicalStoreDto);
		
				ResponseStructure<StaffDto> structure=new ResponseStructure<>();
				structure.setMessage("staff signedUp successfully");
				structure.setHttpStatus(HttpStatus.CREATED.value());
				structure.setData(staffDto);
				return new ResponseEntity<ResponseStructure<StaffDto>>(structure,HttpStatus.CREATED);
			}else {
				
				throw new MedicalStoreIdNotPresentException("Sorry failed to signup");
				
			}
		}else {
			throw new AdminIdNotFoundException("Sorry failed to signup staff");
		}
	}
	public ResponseEntity<ResponseStructure<StaffDto>> updateStaff(int staffId, Staff staff) {
		Staff dbStaff=dao.updateStaff(staffId,staff);
		if(dbStaff!=null) {
			StaffDto staffDto=this.mapper.map(dbStaff, StaffDto.class);
			
			AdminDto adminDto=this.mapper.map(dbStaff.getAdmin(), AdminDto.class);
			staffDto.setAdminDto(adminDto);
			MedicalStoreDto medicalStoreDto=this.mapper.map(dbStaff.getMedicalStore(), MedicalStoreDto.class);
			staffDto.setMedicalStoreDto(medicalStoreDto);
			ResponseStructure<StaffDto> structure=new ResponseStructure<>();
			structure.setMessage("staff updated successfully");
			structure.setHttpStatus(HttpStatus.OK.value());
			structure.setData(staffDto);
			return new ResponseEntity<ResponseStructure<StaffDto>>(structure,HttpStatus.OK);
			
		}else {
//			id is not present
			throw new StaffIdNotFoundException("Sorry failed to update the staff"); 
		}
	}
	public ResponseEntity<ResponseStructure<StaffDto>> findStaff(int staffId) {
		Staff dbStaff=dao.findStaff(staffId);
		if(dbStaff!=null) {
			StaffDto staffDto=this.mapper.map(dbStaff, StaffDto.class);
			
			AdminDto adminDto=this.mapper.map(dbStaff.getAdmin(), AdminDto.class);
			staffDto.setAdminDto(adminDto);
			MedicalStoreDto medicalStoreDto=this.mapper.map(dbStaff.getMedicalStore(), MedicalStoreDto.class);
			staffDto.setMedicalStoreDto(medicalStoreDto);
			ResponseStructure<StaffDto> structure=new ResponseStructure<>();
			structure.setMessage("staff fetched successfully");
			structure.setHttpStatus(HttpStatus.FOUND.value());
			structure.setData(staffDto);
			return new ResponseEntity<ResponseStructure<StaffDto>>(structure,HttpStatus.FOUND);
			
		}else {
//			id is not present
			throw new StaffIdNotFoundException("Sorry failed to fetch the staff"); 
		}
	}
	public ResponseEntity<ResponseStructure<StaffDto>> deleteStaff(int staffId) {
		Staff dbStaff=dao.deleteStaff(staffId);
		if(dbStaff!=null) {
			StaffDto staffDto=this.mapper.map(dbStaff, StaffDto.class);
			
			AdminDto adminDto=this.mapper.map(dbStaff.getAdmin(), AdminDto.class);
			staffDto.setAdminDto(adminDto);
			MedicalStoreDto medicalStoreDto=this.mapper.map(dbStaff.getMedicalStore(), MedicalStoreDto.class);
			staffDto.setMedicalStoreDto(medicalStoreDto);
			ResponseStructure<StaffDto> structure=new ResponseStructure<>();
			structure.setMessage("staff deleted successfully");
			structure.setHttpStatus(HttpStatus.FORBIDDEN.value());
			structure.setData(staffDto);
			return new ResponseEntity<ResponseStructure<StaffDto>>(structure,HttpStatus.FORBIDDEN);
			
		}else {
//			id is not present
			throw new StaffIdNotFoundException("Sorry failed to delete the staff"); 
		}
	}
	
	
	
	
	
	
	
	
	
	
	
}
