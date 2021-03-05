package com.example.poc.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.poc.bo.CompetitionOrganizationBO;
import com.example.poc.bo.CompetitionParticipationBO;
import com.example.poc.dao.ICompetitionOrganizationDAO;
import com.example.poc.dao.ICompetitionParticipationDAO;
import com.example.poc.mapper.ObjectMapper;
import com.example.poc.service.ICompetitionParticipationService;
import com.exemple.poc.client.dto.response.CompetitionOrganizationDto;
import com.exemple.poc.client.dto.response.CompetitionParticipationDto;

@Service
public class CompetitionParticipationService implements ICompetitionParticipationService {
	
	@Autowired
	private ICompetitionOrganizationDAO competitionOrganizationDAO;
	
	@Autowired
	private ICompetitionParticipationDAO competitionParticipationDAO;

	@Override
	public List<CompetitionParticipationDto> findByCompetitionOrganization(int competitionOrganizationId) {
		Optional<CompetitionOrganizationBO> competitionOrganizationBO = competitionOrganizationDAO.findById(competitionOrganizationId);
		if(competitionOrganizationBO.isPresent()) {
			List<CompetitionParticipationBO> competitionParticipationBOs = competitionParticipationDAO.findByCompetitionOrganization(competitionOrganizationBO.get());
			List<CompetitionParticipationDto> competitionParticipationDTOs = new ArrayList<CompetitionParticipationDto>();
			competitionParticipationBOs.stream().forEach((c) -> competitionParticipationDTOs.add(ObjectMapper.toCompetitionParticipationDto(c)));
			return competitionParticipationDTOs;
		}
		return null;
	}
	





}
