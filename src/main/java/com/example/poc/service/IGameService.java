package com.example.poc.service;

import java.util.List;

import com.exemple.poc.client.dto.response.GameDto;

public interface IGameService {

	GameDto findById(int id);
	
	List<GameDto> findAll();
	
}
