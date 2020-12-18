package com.example.poc.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.poc.bo.CityBO;
import com.example.poc.bo.CountryBO;

public interface ICityDAO extends JpaRepository<CityBO, Integer> {
   
	List<CityBO> findAll();
	
	CityBO findByNameAndCountry(String name,CountryBO country);
	

}
