package com.jsp.onlinepharmacya2.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.jsp.onlinepharmacya2.dto.Admin;

public interface AdminRepo extends JpaRepository<Admin, Integer>{
    @Query("select a from Admin a where a.adminEmail=?1")
	Optional<Admin> findByEmail(String email);

}
