package com.example.poc.service;

import java.util.List;

import com.exemple.poc.client.dto.response.CompetitionOrganizationDto;

public interface ICompetitionOrganizationService {
	
	List<CompetitionOrganizationDto> findByCompetition(int competitionId);
	
}
