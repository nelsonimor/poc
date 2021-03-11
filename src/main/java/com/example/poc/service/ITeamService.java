package com.example.poc.service;

import com.example.poc.exception.AlreadyExistsException;
import com.example.poc.exception.NotFoundException;
import com.exemple.poc.client.dto.response.TeamDto;
import com.exemple.poc.client.dto.response.TeamListDto;

public interface ITeamService {
	
	TeamListDto findAllTeams();
	
	TeamDto addTeam(TeamDto team) throws AlreadyExistsException,NotFoundException;


}
