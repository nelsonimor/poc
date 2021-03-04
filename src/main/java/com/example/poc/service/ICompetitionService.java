package com.example.poc.service;

import java.util.List;

import com.exemple.poc.client.dto.response.CompetitionDto;

public interface ICompetitionService {
	
	List<CompetitionDto> findAll();
	
}
