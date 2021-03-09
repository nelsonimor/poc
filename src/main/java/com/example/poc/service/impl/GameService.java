package com.example.poc.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.poc.bo.GameBO;
import com.example.poc.bo.PersonBO;
import com.example.poc.bo.RosterBO;
import com.example.poc.bo.RosterItemBO;
import com.example.poc.bo.TeamBO;
import com.example.poc.dao.IGameDAO;
import com.example.poc.dao.IPersonDAO;
import com.example.poc.dao.IRosterDAO;
import com.example.poc.dao.IRosterItemDAO;
import com.example.poc.dao.ITeamDAO;
import com.example.poc.mapper.ObjectMapper;
import com.example.poc.service.IGameService;
import com.example.poc.service.IRosterService;
import com.exemple.poc.client.dto.response.GameDto;
import com.exemple.poc.client.dto.response.PersonDTO;
import com.exemple.poc.client.dto.response.RosterDto;
import com.exemple.poc.client.dto.response.RosterItemDto;

@Service
public class GameService implements IGameService {
	
	@Autowired
	private IGameDAO gameDao;
	
	@Override
	public GameDto findById(int id) {
		Optional<GameBO> gameBO = gameDao.findById(id);
		return ObjectMapper.toGameDto(gameBO.get());
	}

	@Override
	public List<GameDto> findAll() {
		List<GameBO> gameBOs = gameDao.findAll();
		List<GameDto> gameDTOs = new ArrayList<GameDto>();
		gameBOs.stream().forEach((c) -> gameDTOs.add(ObjectMapper.toGameDto(c)));
		return gameDTOs;
	}
	


}
