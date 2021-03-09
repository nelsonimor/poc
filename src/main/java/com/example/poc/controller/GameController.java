package com.example.poc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.poc.service.IGameService;
import com.exemple.poc.client.dto.response.GameDto;

import io.swagger.annotations.Api;

@Api(description = "Game API for CRUD")
@RestController
public class GameController {

	@Autowired
	private IGameService gameService;
	
	@GetMapping({"/view/games"})
	public List<GameDto> findGames() {
		return gameService.findAll();
	}

	@GetMapping({"/view/games/{id}"})
	public GameDto findGameById(@PathVariable int id) {
		return gameService.findById(id);
	}	
	

	
}
