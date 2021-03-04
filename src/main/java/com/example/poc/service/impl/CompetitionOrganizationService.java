package com.example.poc.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.poc.bo.CompetitionBO;
import com.example.poc.bo.CompetitionOrganizationBO;
import com.example.poc.dao.ICompetitionDAO;
import com.example.poc.dao.ICompetitionOrganizationDAO;
import com.example.poc.mapper.ObjectMapper;
import com.example.poc.service.ICompetitionOrganizationService;
import com.exemple.poc.client.dto.response.CompetitionDto;
import com.exemple.poc.client.dto.response.CompetitionOrganizationDto;

@Service
public class CompetitionOrganizationService implements ICompetitionOrganizationService {
	
	@Autowired
	private ICompetitionDAO competitionDAO;
	
	@Autowired
	private ICompetitionOrganizationDAO competitionOrganizationDAO;

	@Override
	public List<CompetitionOrganizationDto> findByCompetition(int competitionId) {
		Optional<CompetitionBO> competitionBO = competitionDAO.findById(competitionId);
		if(competitionBO.isPresent()) {
			List<CompetitionOrganizationBO> competitionOrganizationBOs = competitionOrganizationDAO.findByCompetition(competitionBO.get());
			List<CompetitionOrganizationDto> competitionDTOs = new ArrayList<CompetitionOrganizationDto>();
			competitionOrganizationBOs.stream().forEach((c) -> competitionDTOs.add(ObjectMapper.toCompetitionOrganizationDto(c)));
			return competitionDTOs;
		}
		return null;
	}
	





}
