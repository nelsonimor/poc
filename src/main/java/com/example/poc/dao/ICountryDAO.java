package com.example.poc.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.poc.bo.ContinentBO;
import com.example.poc.bo.CountryBO;

public interface ICountryDAO extends JpaRepository<CountryBO, Integer> {
   
	List<CountryBO> findAll();
	
	CountryBO findByName(String name);
	
	List<CountryBO> findByContinent(ContinentBO continent);
}
