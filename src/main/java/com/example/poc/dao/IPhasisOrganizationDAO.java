package com.example.poc.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.poc.bo.PhasisOrganizationBO;

public interface IPhasisOrganizationDAO extends JpaRepository<PhasisOrganizationBO, Integer> {
   
	List<PhasisOrganizationBO> findAll();
	
	Optional<PhasisOrganizationBO> findById(int id);
	
}
