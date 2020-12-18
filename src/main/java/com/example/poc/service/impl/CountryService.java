package com.example.poc.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.poc.bo.ContinentBO;
import com.example.poc.bo.CountryBO;
import com.example.poc.dao.IContinentDAO;
import com.example.poc.dao.ICountryDAO;
import com.example.poc.exception.AlreadyExistsException;
import com.example.poc.exception.NotFoundException;
import com.example.poc.mapper.ObjectMapper;
import com.example.poc.service.ICountryService;
import com.exemple.poc.client.dto.response.ContinentDTO;
import com.exemple.poc.client.dto.response.CountryDTO;

@Service
public class CountryService implements ICountryService {
	
	@Autowired
	private ICountryDAO countryDAO;
	
	@Autowired
	private IContinentDAO continentDAO;
	
	public CountryService() {
		
	}

	@Override
	public List<CountryDTO> findAllCountries() {
		List<CountryBO> countryBos = this.countryDAO.findAll();
		List<CountryDTO> countryDTOs = new ArrayList<CountryDTO>();
		countryBos.stream().forEach((c) -> countryDTOs.add(ObjectMapper.toCountryDto(c)));
		return countryDTOs;
	}

	@Override
	public CountryDTO findByName(String name) {
		CountryBO countryBO = this.countryDAO.findByName(name);
		return ObjectMapper.toCountryDto(countryBO);
	}

	@Override
	public CountryDTO addCountry(CountryDTO country) throws AlreadyExistsException, NotFoundException {
		
		CountryBO c = this.countryDAO.findByName(country.getName());
		if(c!=null) {
			throw new AlreadyExistsException("Country with name : "+country.getName()+" already exists");
		}
		
		List<ContinentBO> continentBOs = continentDAO.findByName(country.getContinentName());
		if(continentBOs==null || continentBOs.size()==0) {
			throw new NotFoundException("Continent with name "+country.getContinentName()+" does not exist");
		}
		
		
		CountryBO countryBO = ObjectMapper.toCountryBO(country,continentBOs.get(0));
		CountryBO countryBOAdded = (CountryBO)this.countryDAO.save(countryBO);
		return ObjectMapper.toCountryDto(countryBOAdded);
	}

	@Override
	public List<CountryDTO> findByContinent(String continentName) throws NotFoundException {
		
		List<ContinentBO> continents = continentDAO.findByName(continentName);
		if(continents==null || continents.size()==0) {
			throw new NotFoundException("Continent with name "+continentName+" dos not exist");
		}
		
		List<CountryBO> countryBos = countryDAO.findByContinent(continents.get(0));
		List<CountryDTO> countryDTOs = new ArrayList<CountryDTO>();
		countryBos.stream().forEach((c) -> countryDTOs.add(ObjectMapper.toCountryDto(c)));
		return countryDTOs;
	}









}
