package com.example.poc.service;

import java.util.List;

import com.exemple.poc.client.dto.response.PhasisOrganizationDto;

public interface IPhasisOrganizationService {

	PhasisOrganizationDto findById(int id);
	
	List<PhasisOrganizationDto> findAll();

}
