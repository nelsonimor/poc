package com.example.poc.dao;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.poc.bo.ContinentBO;

public interface IContinentDAO extends JpaRepository<ContinentBO, Integer> {
   
	List<ContinentBO> findAll();
	
	Optional<ContinentBO> findByName(String name);
	
	Optional<ContinentBO> findByCode(String code);
	
	Optional<ContinentBO> findByNameAndCode(String name, String code);
	
	Optional<ContinentBO> findById(int id);
	
	
	
	

}
