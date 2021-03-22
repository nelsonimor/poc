package com.example.poc.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.poc.bo.BoxlineBO;
import com.example.poc.service.IBoxlineService;
import com.example.poc.service.IPersonalStatService;
import com.exemple.poc.client.dto.response.BoxlineDto;
import com.exemple.poc.client.dto.response.PersonalStatDto;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(description = "Stats API for CRUD")
@RestController
public class StatController {

	static Logger logger = LoggerFactory.getLogger(StatController.class);

	@Autowired
	private IPersonalStatService personalStatService;
	
	@Autowired
	private IBoxlineService boxlineService;

	@ApiOperation("Retrieve stat personal")
	@RequestMapping(value = {"/view/stats/{playerId}"},method = {RequestMethod.GET})
	public PersonalStatDto getPersonalStats(@PathVariable int playerId) {
		return personalStatService.getPersonalStats(playerId);
	}
	
	@ApiOperation("Retrieve top points performance")
	@RequestMapping(value = {"/view/stats/topPerformance/{stats}"},method = {RequestMethod.GET})
	public List<BoxlineDto> getPersonalStats(@PathVariable String stats) {
		return boxlineService.findByPointsDesc();
	}
	





}
