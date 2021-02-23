package com.example.poc.service;

import com.exemple.poc.client.dto.response.PersonalStatDto;

public interface IPersonalStatService {
	
	PersonalStatDto getPersonalStats(Integer playerId);
	



}
