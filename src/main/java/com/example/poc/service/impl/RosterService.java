package com.example.poc.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.poc.bo.PersonBO;
import com.example.poc.bo.RosterBO;
import com.example.poc.bo.RosterItemBO;
import com.example.poc.bo.TeamBO;
import com.example.poc.dao.IPersonDAO;
import com.example.poc.dao.IRosterDAO;
import com.example.poc.dao.IRosterItemDAO;
import com.example.poc.dao.ITeamDAO;
import com.example.poc.mapper.ObjectMapper;
import com.example.poc.service.IRosterService;
import com.exemple.poc.client.dto.response.RosterDto;
import com.exemple.poc.client.dto.response.RosterItemDto;

@Service
public class RosterService implements IRosterService {
	
	@Autowired
	private IRosterDAO rosterDao;
	
	@Autowired
	private ITeamDAO teamDao;
	
	@Autowired
	private IRosterItemDAO rosterItemDao;
	
	@Autowired
	private IPersonDAO personDao;

	@Override
	public RosterDto findById(int id) {
		RosterBO rosterBo = rosterDao.findById(id).get();
		return ObjectMapper.toRosterDto(rosterBo);
	}
	
	@Override
	public List<RosterDto> findByTeamId(int teamId){
		Optional<TeamBO> teamBo = teamDao.findById(teamId);
		if(teamBo.isPresent()) {
			List<RosterBO> rosterBos = rosterDao.findByTeam(teamBo.get());
			List<RosterDto> rosterDtos = new ArrayList<RosterDto>();
			for (Iterator iterator = rosterBos.iterator(); iterator.hasNext();) {
				RosterBO rosterBo = (RosterBO) iterator.next();
				rosterDtos.add(ObjectMapper.toRosterDto(rosterBo));
			}
			return rosterDtos;
		}
		return null;
	}

	@Override
	public List<RosterItemDto> findByPlayerId(int playerId) {
		Optional<PersonBO> personBO = personDao.findById(playerId);
		if(personBO.isPresent()) {
			List<RosterItemBO> rosterItemBo = rosterItemDao.findByPerson(personBO.get());
			List<RosterItemDto> rosterItemDtos = new ArrayList<RosterItemDto>();
			for (Iterator iterator = rosterItemBo.iterator(); iterator.hasNext();) {
				RosterItemBO rosterItemBO = (RosterItemBO) iterator.next();
				rosterItemDtos.add(ObjectMapper.toRosterItemDto(rosterItemBO));
			}
			return rosterItemDtos;
		}
		return null;
	}
	


}
