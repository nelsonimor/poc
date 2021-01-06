package com.example.poc.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.poc.bo.CityBO;
import com.example.poc.bo.CountryBO;
import com.example.poc.bo.TeamBO;
import com.example.poc.dao.ICityDAO;
import com.example.poc.dao.ICountryDAO;
import com.example.poc.dao.ITeamDAO;
import com.example.poc.exception.AlreadyExistsException;
import com.example.poc.exception.NotFoundException;
import com.example.poc.mapper.ObjectMapper;
import com.example.poc.service.IEventCreatorService;
import com.example.poc.service.ITeamService;
import com.example.poc.util.ActionCode;
import com.exemple.poc.client.dto.response.TeamDTO;

@Service
public class TeamService implements ITeamService {
	
	@Autowired
	private ITeamDAO teamDAO;
	
	@Autowired
	private ICityDAO cityDAO;
	
	@Autowired
	private ICountryDAO countryDAO;
	
	@Autowired
	private IEventCreatorService eventCreatorService;

	@Override
	public List<TeamDTO> findAllTeams() {
		List<TeamBO> teamBOs = this.teamDAO.findAll();
		
		List<TeamDTO> teamDTOs = new ArrayList<TeamDTO>();
		teamBOs.stream().forEach(c -> {
			teamDTOs.add(ObjectMapper.toTeamDto(c));
		});
		return teamDTOs;
	}

	@Override
	public TeamDTO addTeam(TeamDTO teamDto) throws AlreadyExistsException, NotFoundException {
		Optional<TeamBO> t = teamDAO.findByName(teamDto.getName());
		if(t.isPresent()) {
			eventCreatorService.createEventFailure(ActionCode.TEAM_ADD_FAILED_ALREADY_EXIST,new Object[] {teamDto.getName()});
			throw new AlreadyExistsException(ActionCode.TEAM_ADD_FAILED_ALREADY_EXIST,new Object[] {teamDto.getName()});	
		}
		
		Optional<CityBO> optCity1 = Optional.empty();
		if(teamDto.getCityName1()!=null && teamDto.getCountryOfCity1()!=null) {
			Optional<CountryBO> optCountry1 = countryDAO.findByName(teamDto.getCountryOfCity1());
			if(!optCountry1.isPresent()) {
				eventCreatorService.createEventFailure(ActionCode.TEAM_ADD_FAILED_COUNTRY_OF_CITY1_UNKOWN,new Object[] {teamDto.getCountryOfCity1()});
				throw new NotFoundException(ActionCode.TEAM_ADD_FAILED_COUNTRY_OF_CITY1_UNKOWN,new Object[] {teamDto.getCountryOfCity1()});
			}
			else {
				optCity1 = cityDAO.findByNameAndCountry(teamDto.getCityName1(), optCountry1.get());
				if(!optCity1.isPresent()) {
					eventCreatorService.createEventFailure(ActionCode.TEAM_ADD_FAILED_CITY1_UNKOWN,new Object[] {teamDto.getCityName1()});
					throw new NotFoundException(ActionCode.TEAM_ADD_FAILED_CITY1_UNKOWN,new Object[] {teamDto.getCityName1()});
				}
			}
		}
		
		Optional<CityBO> optCity2 = Optional.empty();
		if(teamDto.getCityName2()!=null && teamDto.getCountryOfCity2()!=null) {
			Optional<CountryBO> optCountry2 = countryDAO.findByName(teamDto.getCountryOfCity2());
			if(!optCountry2.isPresent()) {
				eventCreatorService.createEventFailure(ActionCode.TEAM_ADD_FAILED_COUNTRY_OF_CITY2_UNKOWN,new Object[] {teamDto.getCountryOfCity2()});
				throw new NotFoundException(ActionCode.TEAM_ADD_FAILED_COUNTRY_OF_CITY2_UNKOWN,new Object[] {teamDto.getCountryOfCity2()});
			}
			else {
				optCity2 = cityDAO.findByNameAndCountry(teamDto.getCityName2(), optCountry2.get());
				if(!optCity2.isPresent()) {
					eventCreatorService.createEventFailure(ActionCode.TEAM_ADD_FAILED_CITY2_UNKOWN,new Object[] {teamDto.getCityName2()});
					throw new NotFoundException(ActionCode.TEAM_ADD_FAILED_CITY2_UNKOWN,new Object[] {teamDto.getCityName2()});
				}
			}
		}
		
		Optional<CityBO> optCity3 = Optional.empty();
		if(teamDto.getCityName3()!=null && teamDto.getCountryOfCity3()!=null) {
			Optional<CountryBO> optCountry3 = countryDAO.findByName(teamDto.getCountryOfCity3());
			if(!optCountry3.isPresent()) {
				eventCreatorService.createEventFailure(ActionCode.TEAM_ADD_FAILED_COUNTRY_OF_CITY3_UNKOWN,new Object[] {teamDto.getCountryOfCity3()});
				throw new NotFoundException(ActionCode.TEAM_ADD_FAILED_COUNTRY_OF_CITY3_UNKOWN,new Object[] {teamDto.getCountryOfCity3()});
			}
			else {
				optCity3 = cityDAO.findByNameAndCountry(teamDto.getCityName3(), optCountry3.get());
				if(!optCity3.isPresent()) {
					eventCreatorService.createEventFailure(ActionCode.TEAM_ADD_FAILED_CITY3_UNKOWN,new Object[] {teamDto.getCityName3()});
					throw new NotFoundException(ActionCode.TEAM_ADD_FAILED_CITY3_UNKOWN,new Object[] {teamDto.getCityName3()});
				}
			}
		}
		
		Optional<CountryBO> optCountry = Optional.empty();
		if(teamDto.getCountryName()!=null) {
			optCountry = countryDAO.findByName(teamDto.getCountryOfCity3());
			if(!optCountry.isPresent()) {
				eventCreatorService.createEventFailure(ActionCode.TEAM_ADD_FAILED_COUNTRY_UNKOWN,new Object[] {teamDto.getCountryName()});
				throw new NotFoundException(ActionCode.TEAM_ADD_FAILED_COUNTRY_UNKOWN,new Object[] {teamDto.getCountryOfCity3()});
			}
		}

		TeamBO teamBOAdded = (TeamBO)this.teamDAO.save(ObjectMapper.toTeamBO(teamDto,optCity1,optCity2,optCity3,optCountry));
		TeamDTO teamDTO = ObjectMapper.toTeamDto(teamBOAdded);
		eventCreatorService.createEventSuccess(ActionCode.TEAM_ADD_SUCCESS,new Object[] {teamBOAdded.getName(),teamBOAdded.getId()});
		return teamDTO;
	}

}
