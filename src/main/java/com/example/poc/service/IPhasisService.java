package com.example.poc.service;

import java.util.List;

import com.exemple.poc.client.dto.response.PhasisDTO;

public interface IPhasisService {

	PhasisDTO findById(int id);
	
	List<PhasisDTO> findAll();

}
