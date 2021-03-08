package com.example.poc.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.poc.bo.PhasisBO;

public interface IPhasisDAO extends JpaRepository<PhasisBO, Integer> {
   
	List<PhasisBO> findAll();
	
	Optional<PhasisBO> findById(int id);
	
}
