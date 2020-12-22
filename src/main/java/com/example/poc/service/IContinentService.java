package com.example.poc.service;

import java.util.List;

import com.example.poc.client.dto.request.ContinentRequest;
import com.example.poc.exception.AlreadyExistsException;
import com.example.poc.exception.NotFoundException;
import com.exemple.poc.client.dto.response.ContinentDTO;

public interface IContinentService {
	
	List<ContinentDTO> findAllContinents();
	
	ContinentDTO findById(int id) throws NotFoundException;
	
	ContinentDTO findByName(String name);
	
	ContinentDTO findByCode(String code);
	
	List<ContinentDTO> findByRequest(ContinentRequest continentRequest);
	
	ContinentDTO addContinent(ContinentDTO continent) throws AlreadyExistsException;
	
	String get();
	
	void deleteById(int id) throws NotFoundException;
	
	ContinentDTO updateContinent(ContinentDTO continent,int id) throws NotFoundException;
	
}
