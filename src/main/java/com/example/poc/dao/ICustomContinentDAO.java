package com.example.poc.dao;

import java.util.List;

import com.example.poc.bo.ContinentBO;
import com.example.poc.client.dto.request.ContinentRequest;


public interface ICustomContinentDAO  {

	List<ContinentBO> findContinents(ContinentRequest continentRequest);
	
	String get();
	
}
