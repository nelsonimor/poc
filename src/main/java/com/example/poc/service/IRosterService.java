package com.example.poc.service;

import java.util.List;

import com.exemple.poc.client.dto.response.RosterDto;
import com.exemple.poc.client.dto.response.RosterItemDto;

public interface IRosterService {

	RosterDto findById(int id);
	
	List<RosterDto> findByTeamId(int teamId);
	
	List<RosterItemDto> findByPlayerId(int playerId);
}
