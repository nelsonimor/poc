package com.example.poc.service;

import java.util.List;

import com.exemple.poc.client.dto.response.PhasisDto;

public interface IPhasisService {

	PhasisDto findById(int id);
	
	List<PhasisDto> findAll();

}
