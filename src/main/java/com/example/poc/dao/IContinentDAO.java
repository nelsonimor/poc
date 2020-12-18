package com.example.poc.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.poc.bo.ContinentBO;

public interface IContinentDAO extends JpaRepository<ContinentBO, Integer> {
   
	List<ContinentBO> findAll();
	
	List<ContinentBO> findByName(String name);
	
	List<ContinentBO> findByCode(String code);
	
	List<ContinentBO> findByNameAndCode(String name, String code);
	
	ContinentBO findById(int id);
	
	
	
	

}
