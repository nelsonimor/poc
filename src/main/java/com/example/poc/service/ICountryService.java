package com.example.poc.service;

import java.util.List;

import com.example.poc.exception.AlreadyExistsException;
import com.example.poc.exception.NotFoundException;
import com.exemple.poc.client.dto.response.CountryDTO;

public interface ICountryService {
	
	List<CountryDTO> findAllCountries();

	CountryDTO findByName(String name);
	
	List<CountryDTO> findByContinent(String continentName) throws NotFoundException;
	
	CountryDTO addCountry(CountryDTO country) throws AlreadyExistsException,NotFoundException;
}
