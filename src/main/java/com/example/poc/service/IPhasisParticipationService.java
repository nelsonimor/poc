package com.example.poc.service;

import java.util.List;

import com.exemple.poc.client.dto.response.PhasisOrganizationDto;
import com.exemple.poc.client.dto.response.PhasisParticipationDto;

public interface IPhasisParticipationService {

	PhasisParticipationDto findById(int id);
	
	List<PhasisParticipationDto> findAll();

}
