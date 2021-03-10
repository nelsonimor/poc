package com.example.poc.service;

import java.util.List;

import com.example.poc.exception.AlreadyExistsException;
import com.example.poc.exception.NotFoundException;
import com.exemple.poc.client.dto.response.CityDto;

public interface ICityService {
	
	List<CityDto> findAllCities();
	
	CityDto addCity(CityDto city) throws AlreadyExistsException,NotFoundException;


}
