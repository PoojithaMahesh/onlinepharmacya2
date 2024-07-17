package com.jsp.onlinepharmacya2.dao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jsp.onlinepharmacya2.entity.Medicines;
import com.jsp.onlinepharmacya2.repo.MedicineRepo;

@Repository
public class MedicineDao {
@Autowired
private MedicineRepo repo;

public Medicines saveMedicine(Medicines medicines) {
	// TODO Auto-generated method stub
	return repo.save(medicines);
}

public Medicines updateMedicine(int medicineId, Medicines medicines) {
	Optional<Medicines> optional=repo.findById(medicineId);
	if(optional.isPresent()) {
		medicines.setMedicineId(medicineId);
		medicines.setMedicalStore(optional.get().getMedicalStore());
		return repo.save(medicines);
	}return null;
}

public Medicines findMedicines(int medicineId) {
	Optional<Medicines> optional=repo.findById(medicineId);
	if(optional.isPresent()) {
		return optional.get();
	}return null;
}

public Medicines deleteMedicine(int medicineId) {
	Optional<Medicines> optional=repo.findById(medicineId);
	if(optional.isPresent()) {
		repo.deleteById(medicineId);
		return optional.get();
	}return null;
}

public Medicines findMedicineByName(String medicineName) {
	Optional<Medicines> optional=repo.findByName(medicineName);
	if(optional.isPresent()) {
		return optional.get();
	}return null;

} 

}
