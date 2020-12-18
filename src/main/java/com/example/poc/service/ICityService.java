package com.example.poc.service;

import java.util.List;

import com.example.poc.exception.AlreadyExistsException;
import com.example.poc.exception.NotFoundException;
import com.exemple.poc.client.dto.response.CityDTO;

public interface ICityService {
	
	List<CityDTO> findAllCities();
	
	CityDTO addCity(CityDTO city) throws AlreadyExistsException,NotFoundException;


}
