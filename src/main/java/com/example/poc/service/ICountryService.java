package com.example.poc.service;

import java.util.List;

import com.example.poc.exception.AlreadyExistsException;
import com.example.poc.exception.NotFoundException;
import com.exemple.poc.client.dto.response.ContinentDto;
import com.exemple.poc.client.dto.response.CountryDto;

public interface ICountryService {
	
	List<CountryDto> findAllCountries();

	CountryDto findByName(String name);
	
	List<CountryDto> findByContinent(String continentName) throws NotFoundException;
	
	CountryDto addCountry(CountryDto country) throws AlreadyExistsException,NotFoundException;
	
	List<CountryDto> getCountriesByLikeName(String name);
}
