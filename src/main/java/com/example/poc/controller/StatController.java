package com.example.poc.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.poc.service.IPersonalStatService;
import com.exemple.poc.client.dto.response.PersonalStatDto;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(description = "Stats API for CRUD")
@RestController
public class StatController {

	static Logger logger = LoggerFactory.getLogger(StatController.class);

	@Autowired
	private IPersonalStatService personalStatService;

	@ApiOperation("Retrieve stat personal")

	@RequestMapping(value = {"/stats/{playerId}"},method = {RequestMethod.GET})
	public PersonalStatDto getPersonalStats(@PathVariable int playerId) {
		return personalStatService.getPersonalStats(playerId);
	}
	





}
