package com.jsp.onlinepharmacya2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jsp.onlinepharmacya2.dao.AddressDao;
import com.jsp.onlinepharmacya2.dao.AdminDao;
import com.jsp.onlinepharmacya2.dao.MedicalStoreDao;
import com.jsp.onlinepharmacya2.dto.AddressDto;
import com.jsp.onlinepharmacya2.dto.AdminDto;
import com.jsp.onlinepharmacya2.dto.MedicalStoreDto;
import com.jsp.onlinepharmacya2.entity.Address;
import com.jsp.onlinepharmacya2.entity.Admin;
import com.jsp.onlinepharmacya2.entity.MedicalStore;
import com.jsp.onlinepharmacya2.exception.AddressIdNotFoundException;
import com.jsp.onlinepharmacya2.exception.AdminIdNotFoundException;
import com.jsp.onlinepharmacya2.exception.MedicalStoreIdNotPresentException;
import com.jsp.onlinepharmacya2.util.ResponseStructure;

@Service
public class MedicalStoreService {

	@Autowired
	private MedicalStoreDao dao;
	@Autowired
	private AdminDao adminDao;
	@Autowired
	private AddressDao addressDao;

	public ResponseEntity<ResponseStructure<MedicalStoreDto>> saveMedicalStore(int adminId, int addressId,
			MedicalStore medicalStore) {
		Admin dbAdmin=adminDao.findAdmin(adminId);
		if(dbAdmin!=null) {
//			admin ios the valid admin
			medicalStore.setAdmin(dbAdmin);
			Address dbAddress=addressDao.findAddressById(addressId);
			if(dbAddress!=null) {
//				Address is also valid address...
				medicalStore.setAddress(dbAddress);
				
//				now admin and address both are valid and now i can establish the Medicalstore
				MedicalStore dbMedicalStore=dao.saveMedicalStore(medicalStore);
				ResponseStructure<MedicalStoreDto> structure=new ResponseStructure<>();
				structure.setMessage("MedicalStore established successfully");
				structure.setHttpStatus(HttpStatus.CREATED.value());
				
				MedicalStoreDto dto=new MedicalStoreDto();
				dto.setStoreId(dbMedicalStore.getStoreId());
				dto.setPhone(dbMedicalStore.getPhone());
				dto.setName(dbMedicalStore.getName());
				dto.setManagerName(dbMedicalStore.getManagerName());
				
				Address address=dbMedicalStore.getAddress();
				AddressDto addressDto=new AddressDto();
				addressDto.setAddressId(address.getAddressId());
				addressDto.setCity(address.getCity());
				addressDto.setPincode(address.getPincode());
				addressDto.setState(address.getState());
				
				dto.setAddressDto(addressDto);
				
				Admin admin=dbMedicalStore.getAdmin();
				AdminDto adminDto=new AdminDto();
				adminDto.setAdminEmail(admin.getAdminEmail());
				adminDto.setAdminId(admin.getAdminId());
				adminDto.setAdminName(admin.getAdminName());
				
				dto.setAdminDto(adminDto);
				
				
				structure.setData(dto);
				return new ResponseEntity<ResponseStructure<MedicalStoreDto>>(structure,HttpStatus.CREATED);
				
				
			}else {
				throw new AddressIdNotFoundException("Sorry failed to establish the MedicalStore");
			}
		}else{
//			admin is not present it is fake right?? yes
			throw new AdminIdNotFoundException("Sorry Failed to Establish the MedicalStore");
		}
	}

	public ResponseEntity<ResponseStructure<MedicalStoreDto>> updateMedicalStore(int storeId,
			MedicalStore medicalStore) {
		MedicalStore dbMedicalStore=dao.updateMedicalStore(storeId,medicalStore);
		if(dbMedicalStore!=null) {
//			store is present and the data updated successfully
			ResponseStructure<MedicalStoreDto> structure=new ResponseStructure<>();
			structure.setMessage("MedicalStore updated successfully");
			structure.setHttpStatus(HttpStatus.OK.value());
			
			MedicalStoreDto dto=new MedicalStoreDto();
			dto.setStoreId(dbMedicalStore.getStoreId());
			dto.setPhone(dbMedicalStore.getPhone());
			dto.setName(dbMedicalStore.getName());
			dto.setManagerName(dbMedicalStore.getManagerName());
			
			Address address=dbMedicalStore.getAddress();
			AddressDto addressDto=new AddressDto();
			addressDto.setAddressId(address.getAddressId());
			addressDto.setCity(address.getCity());
			addressDto.setPincode(address.getPincode());
			addressDto.setState(address.getState());
			
			dto.setAddressDto(addressDto);
			
			Admin admin=dbMedicalStore.getAdmin();
			AdminDto adminDto=new AdminDto();
			adminDto.setAdminEmail(admin.getAdminEmail());
			adminDto.setAdminId(admin.getAdminId());
			adminDto.setAdminName(admin.getAdminName());
			
			dto.setAdminDto(adminDto);
			
			
			structure.setData(dto);
			return new ResponseEntity<ResponseStructure<MedicalStoreDto>>(structure,HttpStatus.OK);
			
		}else {
			throw new MedicalStoreIdNotPresentException("Sorry failed to updateMedicalStore");
		}
	}

	public ResponseEntity<ResponseStructure<MedicalStoreDto>> findMedicalStore(int storeId) {
		MedicalStore dbMedicalStore=dao.findMedicalStoreById(storeId);
		if(dbMedicalStore!=null) {
//			store is present and the data updated successfully
			ResponseStructure<MedicalStoreDto> structure=new ResponseStructure<>();
			structure.setMessage("MedicalStore fetched successfully");
			structure.setHttpStatus(HttpStatus.FOUND.value());
			
			MedicalStoreDto dto=new MedicalStoreDto();
			dto.setStoreId(dbMedicalStore.getStoreId());
			dto.setPhone(dbMedicalStore.getPhone());
			dto.setName(dbMedicalStore.getName());
			dto.setManagerName(dbMedicalStore.getManagerName());
			
			Address address=dbMedicalStore.getAddress();
			AddressDto addressDto=new AddressDto();
			addressDto.setAddressId(address.getAddressId());
			addressDto.setCity(address.getCity());
			addressDto.setPincode(address.getPincode());
			addressDto.setState(address.getState());
			
			dto.setAddressDto(addressDto);
			
			Admin admin=dbMedicalStore.getAdmin();
			AdminDto adminDto=new AdminDto();
			adminDto.setAdminEmail(admin.getAdminEmail());
			adminDto.setAdminId(admin.getAdminId());
			adminDto.setAdminName(admin.getAdminName());
			
			dto.setAdminDto(adminDto);
			
			
			structure.setData(dto);
			return new ResponseEntity<ResponseStructure<MedicalStoreDto>>(structure,HttpStatus.FOUND);
			
		}else {
			throw new MedicalStoreIdNotPresentException("Sorry failed to findMedicalStore");
		}
	}

	public ResponseEntity<ResponseStructure<MedicalStoreDto>> deleteMedicalStore(int storeId) {
		MedicalStore dbMedicalStore=dao.deleteMedicalStore(storeId);
		if(dbMedicalStore!=null) {
//			store is present and the data updated successfully
			ResponseStructure<MedicalStoreDto> structure=new ResponseStructure<>();
			structure.setMessage("MedicalStore deleted successfully");
			structure.setHttpStatus(HttpStatus.FORBIDDEN.value());
			
			MedicalStoreDto dto=new MedicalStoreDto();
			dto.setStoreId(dbMedicalStore.getStoreId());
			dto.setPhone(dbMedicalStore.getPhone());
			dto.setName(dbMedicalStore.getName());
			dto.setManagerName(dbMedicalStore.getManagerName());
			
			Address address=dbMedicalStore.getAddress();
			AddressDto addressDto=new AddressDto();
			addressDto.setAddressId(address.getAddressId());
			addressDto.setCity(address.getCity());
			addressDto.setPincode(address.getPincode());
			addressDto.setState(address.getState());
			
			dto.setAddressDto(addressDto);
			
			Admin admin=dbMedicalStore.getAdmin();
			AdminDto adminDto=new AdminDto();
			adminDto.setAdminEmail(admin.getAdminEmail());
			adminDto.setAdminId(admin.getAdminId());
			adminDto.setAdminName(admin.getAdminName());
			
			dto.setAdminDto(adminDto);
			
			
			structure.setData(dto);
			return new ResponseEntity<ResponseStructure<MedicalStoreDto>>(structure,HttpStatus.FORBIDDEN);
			
		}else {
			throw new MedicalStoreIdNotPresentException("Sorry failed to findMedicalStore");
		}
	}
	
	
	
	
	
	
	
	
	
}
