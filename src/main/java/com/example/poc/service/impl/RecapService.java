package com.example.poc.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.poc.dao.ICompetitionDAO;
import com.example.poc.dao.IGameDAO;
import com.example.poc.dao.IPersonDAO;
import com.example.poc.dao.IPhasisDAO;
import com.example.poc.dao.IRosterDAO;
import com.example.poc.dao.ITeamDAO;
import com.example.poc.service.IRecapService;
import com.exemple.poc.client.dto.response.RecapDto;

@Service
public class RecapService implements IRecapService {

	@Autowired
	private IPhasisDAO phasisDao;

	@Autowired
	private ITeamDAO teamDAO;
	
	@Autowired
	private IPersonDAO personDAO;
	
	@Autowired
	private ICompetitionDAO competitionDAO;
	
	@Autowired
	private IRosterDAO rosterDAO;
	
	@Autowired
	private IGameDAO gameDAO;
	
	@Override
	public RecapDto getRecap() {
		RecapDto recapDto = new RecapDto();
		recapDto.setNbTeams(teamDAO.count());
		recapDto.setNbPlayers(personDAO.count());
		recapDto.setNbCompetitions(competitionDAO.count());
		recapDto.setNbPhasis(phasisDao.count());
		recapDto.setNbRosters(rosterDAO.count());
		recapDto.setNbGames(gameDAO.count());
		return recapDto;
	}

}
