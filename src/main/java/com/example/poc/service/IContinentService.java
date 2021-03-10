package com.example.poc.service;

import java.util.List;

import com.example.poc.client.dto.request.ContinentRequest;
import com.example.poc.exception.AlreadyExistsException;
import com.example.poc.exception.NotFoundException;
import com.exemple.poc.client.dto.response.ContinentDto;

public interface IContinentService {
	
	List<ContinentDto> findAllContinents();
	
	ContinentDto findById(int id) throws NotFoundException;
	
	ContinentDto findByName(String name);
	
	ContinentDto findByCode(String code);
	
	List<ContinentDto> findByRequest(ContinentRequest continentRequest);
	
	ContinentDto addContinent(ContinentDto continent) throws AlreadyExistsException;
	
	String get();
	
	void deleteById(int id) throws NotFoundException;
	
	ContinentDto updateContinent(ContinentDto continent,int id) throws NotFoundException;
	
}
