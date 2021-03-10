package com.example.poc.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.poc.bo.CityBO;
import com.example.poc.bo.CompetitionBO;
import com.example.poc.dao.ICompetitionDAO;
import com.example.poc.mapper.ObjectMapper;
import com.example.poc.service.ICompetitionService;
import com.exemple.poc.client.dto.response.CityDto;
import com.exemple.poc.client.dto.response.CompetitionDto;

@Service
public class CompetitionService implements ICompetitionService {
	
	@Autowired
	private ICompetitionDAO competitionDAO;
	
	@Autowired

	
	public CompetitionService() {
		
	}

	@Transactional
	@Override
	public List<CompetitionDto> findAll() {
		List<CompetitionBO> competitionBos = this.competitionDAO.findAll();
		List<CompetitionDto> competitionDTOs = new ArrayList<CompetitionDto>();
		competitionBos.stream().forEach((c) -> competitionDTOs.add(ObjectMapper.toCompetitionDto(c)));
		return competitionDTOs;
	}


}
