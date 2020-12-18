package com.example.poc.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.poc.bo.CityBO;
import com.example.poc.bo.CountryBO;
import com.example.poc.dao.ICityDAO;
import com.example.poc.dao.ICountryDAO;
import com.example.poc.exception.AlreadyExistsException;
import com.example.poc.exception.NotFoundException;
import com.example.poc.mapper.ObjectMapper;
import com.example.poc.service.ICityService;
import com.example.poc.service.IGeolocationService;
import com.exemple.poc.client.dto.response.CityDTO;

import model.Address;

@Service
public class CityService implements ICityService {
	
	@Autowired
	private ICityDAO cityDAO;
	
	@Autowired
	private ICountryDAO countryDAO;
	
	@Autowired
	private IGeolocationService geolocationService;
	
	public CityService() {
		
	}

	@Override
	public List<CityDTO> findAllCities() {
		List<CityBO> cityBos = this.cityDAO.findAll();
		List<CityDTO> cityDTOs = new ArrayList<CityDTO>();
		cityBos.stream().forEach((c) -> cityDTOs.add(ObjectMapper.toCityDto(c)));
		return cityDTOs;
	}

	@Override
	public CityDTO addCity(CityDTO city) throws AlreadyExistsException, NotFoundException {
		CountryBO countryBO = countryDAO.findByName(city.getCountryName());
		if(countryBO==null) {
			throw new NotFoundException("Country with name "+city.getCountryName()+" does not exist");
		}
		
		
		CityBO c = this.cityDAO.findByNameAndCountry(city.getName(),countryBO);
		if(c!=null) {
			throw new AlreadyExistsException("City with name : "+city.getName()+" for the country "+city.getCountryName()+" already exists");
		}
		
		CityBO cityBO = null;

		if(city.getLatitude()==null || city.getLongitude()==null) {
			Address address = geolocationService.geolocate(city.getName(), city.getCountryName());
			cityBO = ObjectMapper.toCityBO(address, city.getName(), countryBO);
		}
		else {
			cityBO = ObjectMapper.toCityBO(city,countryBO);
		}
		
		CityBO cityBOAdded = (CityBO)this.cityDAO.save(cityBO);
		CityDTO cityDTO = ObjectMapper.toCityDto(cityBOAdded);
		return cityDTO;
	}










}
