package com.jsp.onlinepharmacya2.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jsp.onlinepharmacya2.dao.MedicalStoreDao;
import com.jsp.onlinepharmacya2.dao.MedicineDao;
import com.jsp.onlinepharmacya2.dto.MedicalStoreDto;
import com.jsp.onlinepharmacya2.dto.MedicineDto;
import com.jsp.onlinepharmacya2.entity.MedicalStore;
import com.jsp.onlinepharmacya2.entity.Medicines;
import com.jsp.onlinepharmacya2.exception.MedicalStoreIdNotPresentException;
import com.jsp.onlinepharmacya2.exception.MedicineIdNotFoundException;
import com.jsp.onlinepharmacya2.exception.MedicineNameNotFoundException;
import com.jsp.onlinepharmacya2.util.ResponseStructure;

@Service
public class MedicineService {
@Autowired
private MedicineDao dao;
@Autowired
private MedicalStoreDao storeDao;
@Autowired
private ModelMapper mapper;

public ResponseEntity<ResponseStructure<MedicineDto>> saveMedicines(int storeId, Medicines medicines) {
	MedicalStore dbMedicalStore=storeDao.findMedicalStoreById(storeId);
	if(dbMedicalStore!=null) {
//		store is present i can add the medicines to this store
		medicines.setMedicalStore(dbMedicalStore);
		Medicines dbMedicines=dao.saveMedicine(medicines);
		
		MedicineDto dto=this.mapper.map(dbMedicines, MedicineDto.class);
		MedicalStoreDto medicalStoreDto=this.mapper.map(dbMedicines.getMedicalStore(), MedicalStoreDto.class);
		dto.setMedicalStoreDto(medicalStoreDto);
		ResponseStructure<MedicineDto> structure=new ResponseStructure<>();
		structure.setMessage("Data saved succesfully");
		structure.setHttpStatus(HttpStatus.CREATED.value());
		structure.setData(dto);
		return new ResponseEntity<ResponseStructure<MedicineDto>>(structure,HttpStatus.CREATED);	
	}else {
		throw new MedicalStoreIdNotPresentException("Sorry failed to add mediciness");
	}
}

public ResponseEntity<ResponseStructure<MedicineDto>> updateMedicine(int medicineId, Medicines medicines) {
	Medicines dbMedicines=dao.updateMedicine(medicineId,medicines);
	if(dbMedicines!=null) {
		MedicineDto dto=this.mapper.map(dbMedicines, MedicineDto.class);
		MedicalStoreDto medicalStoreDto=this.mapper.map(dbMedicines.getMedicalStore(), MedicalStoreDto.class);
		dto.setMedicalStoreDto(medicalStoreDto);
		ResponseStructure<MedicineDto> structure=new ResponseStructure<>();
		structure.setMessage("Data upadated succesfully");
		structure.setHttpStatus(HttpStatus.OK.value());
		structure.setData(dto);
		return new ResponseEntity<ResponseStructure<MedicineDto>>(structure,HttpStatus.OK);	
	}else {
		throw new MedicineIdNotFoundException("Sorry failed to update medicine");
	}
	
}

public ResponseEntity<ResponseStructure<MedicineDto>> findMedicine(int medicineId) {
	Medicines dbMedicines=dao.findMedicines(medicineId);
	if(dbMedicines!=null) {
		MedicineDto dto=this.mapper.map(dbMedicines, MedicineDto.class);
		MedicalStoreDto medicalStoreDto=this.mapper.map(dbMedicines.getMedicalStore(), MedicalStoreDto.class);
		dto.setMedicalStoreDto(medicalStoreDto);
		ResponseStructure<MedicineDto> structure=new ResponseStructure<>();
		structure.setMessage("Data fetched succesfully");
		structure.setHttpStatus(HttpStatus.FOUND.value());
		structure.setData(dto);
		return new ResponseEntity<ResponseStructure<MedicineDto>>(structure,HttpStatus.FOUND);	
	}else {
		throw new MedicineIdNotFoundException("Sorry failed to fetch medicine");
	}
}

public ResponseEntity<ResponseStructure<MedicineDto>> deleteMedicine(int medicineId) {
	Medicines dbMedicines=dao.deleteMedicine(medicineId);
	if(dbMedicines!=null) {
		MedicineDto dto=this.mapper.map(dbMedicines, MedicineDto.class);
		MedicalStoreDto medicalStoreDto=this.mapper.map(dbMedicines.getMedicalStore(), MedicalStoreDto.class);
		dto.setMedicalStoreDto(medicalStoreDto);
		ResponseStructure<MedicineDto> structure=new ResponseStructure<>();
		structure.setMessage("Data deleted succesfully");
		structure.setHttpStatus(HttpStatus.FORBIDDEN.value());
		structure.setData(dto);
		return new ResponseEntity<ResponseStructure<MedicineDto>>(structure,HttpStatus.FORBIDDEN);	
	}else {
		throw new MedicineIdNotFoundException("Sorry failed to delete medicine");
	}
}

public ResponseEntity<ResponseStructure<MedicineDto>> findMedicineByName(String medicineName) {
	Medicines dbMedicines=dao.findMedicineByName(medicineName);
	if(dbMedicines!=null) {
		MedicineDto dto=this.mapper.map(dbMedicines, MedicineDto.class);
		MedicalStoreDto medicalStoreDto=this.mapper.map(dbMedicines.getMedicalStore(), MedicalStoreDto.class);
		dto.setMedicalStoreDto(medicalStoreDto);
		ResponseStructure<MedicineDto> structure=new ResponseStructure<>();
		structure.setMessage("Data fetched succesfully");
		structure.setHttpStatus(HttpStatus.FOUND.value());
		structure.setData(dto);
		return new ResponseEntity<ResponseStructure<MedicineDto>>(structure,HttpStatus.FOUND);	
	}else {
		throw new MedicineNameNotFoundException("Sorry failed to fetch medicine");
	}
}
}
