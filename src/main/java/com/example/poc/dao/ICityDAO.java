package com.example.poc.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.poc.bo.CityBO;
import com.example.poc.bo.CountryBO;

public interface ICityDAO extends JpaRepository<CityBO, Integer> {
   
	List<CityBO> findAll();
	
	Optional<CityBO> findByNameAndCountry(String name,CountryBO country);
	

}
