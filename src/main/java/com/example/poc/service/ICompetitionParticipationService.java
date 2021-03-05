package com.example.poc.service;

import java.util.List;

import com.exemple.poc.client.dto.response.CompetitionParticipationDto;

public interface ICompetitionParticipationService {
	
	List<CompetitionParticipationDto> findByCompetitionOrganization(int competitionOrganizationId);
	
}
