package com.jsp.onlinepharmacya2.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.jsp.onlinepharmacya2.entity.Medicines;

public interface MedicineRepo extends JpaRepository<Medicines, Integer>{
    @Query("select m from Mdeicine m where m.medicineName=?1")
	Optional<Medicines> findByName(String medicineName);

}
