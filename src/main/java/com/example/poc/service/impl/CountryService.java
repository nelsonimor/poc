package com.example.poc.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.poc.bo.ContinentBO;
import com.example.poc.bo.CountryBO;
import com.example.poc.dao.IContinentDAO;
import com.example.poc.dao.ICountryDAO;
import com.example.poc.exception.AlreadyExistsException;
import com.example.poc.exception.NotFoundException;
import com.example.poc.mapper.ObjectMapper;
import com.example.poc.service.ICountryService;
import com.example.poc.service.IEventCreatorService;
import com.example.poc.util.ActionCode;
import com.exemple.poc.client.dto.response.CountryDto;

@Service
public class CountryService implements ICountryService {
	
	@Autowired
	private ICountryDAO countryDAO;
	
	@Autowired
	private IContinentDAO continentDAO;
	
	@Autowired
	private IEventCreatorService eventCreatorService;
	
	public CountryService() {
		
	}

	@Override
	public List<CountryDto> findAllCountries() {
		List<CountryBO> countryBos = this.countryDAO.findAll();
		List<CountryDto> countryDTOs = new ArrayList<CountryDto>();
		countryBos.stream().forEach((c) -> countryDTOs.add(ObjectMapper.toCountryDto(c)));
		return countryDTOs;
	}

	@Override
	public CountryDto findByName(String name) {
		Optional<CountryBO> countryBO = this.countryDAO.findByName(name);
		return ObjectMapper.toCountryDto(countryBO.get());
	}

	@Transactional
	@Override
	public CountryDto addCountry(CountryDto country) throws AlreadyExistsException, NotFoundException {
		
		Optional<CountryBO> c = this.countryDAO.findByName(country.getName());
		if(c.isPresent()) {
			eventCreatorService.createEventFailure(ActionCode.COUNTRY_ADD_FAILED_ALREADY_EXIST,new Object[] {country.getName()});
			throw new AlreadyExistsException(ActionCode.COUNTRY_ADD_FAILED_ALREADY_EXIST,new Object[] {country.getName()});
		}
		
		Optional<ContinentBO> continentBO = continentDAO.findByName(country.getContinentName());
		if(!continentBO.isPresent()) {
			eventCreatorService.createEventFailure(ActionCode.COUNTRY_ADD_FAILED_UNKNOWN_CONTINENT,new Object[] {country.getContinentName()});
			throw new NotFoundException(ActionCode.COUNTRY_ADD_FAILED_UNKNOWN_CONTINENT,new Object[] {country.getContinentName()});
		}
		

		CountryBO countryBO = ObjectMapper.toCountryBO(country,continentBO.get());	
		CountryBO countryBOAdded = (CountryBO)this.countryDAO.save(countryBO);
		eventCreatorService.createEventSuccess(ActionCode.COUNTRY_ADD_SUCCESS,new Object[] {countryBOAdded.getName(),countryBOAdded.getId()});
		return ObjectMapper.toCountryDto(countryBOAdded);
	}

	@Override
	public List<CountryDto> findByContinent(String continentName) throws NotFoundException {
		
		Optional<ContinentBO> continent = continentDAO.findByName(continentName);
		if(!continent.isPresent()) {
			throw new NotFoundException("Continent with name "+continentName+" dos not exist");
		}
		
		List<CountryBO> countryBos = countryDAO.findByContinent(continent.get());
		List<CountryDto> countryDTOs = new ArrayList<CountryDto>();
		countryBos.stream().forEach((c) -> countryDTOs.add(ObjectMapper.toCountryDto(c)));
		return countryDTOs;
	}
	
	@Override
	public List<CountryDto> getCountriesByLikeName(String name) {
		List<CountryBO> countryBOs = this.countryDAO.findAll();

		List<CountryDto> countryDTOs = new ArrayList<CountryDto>();
		countryBOs
		.stream()
		.filter(c -> c.getName().contains(name))
		.forEach(c -> countryDTOs.add(ObjectMapper.toCountryDto(c)));
		return countryDTOs;
	}
}
