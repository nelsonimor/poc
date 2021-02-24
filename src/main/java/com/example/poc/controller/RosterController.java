package com.example.poc.controller;

import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.poc.bo.RosterBO;
import com.example.poc.bo.RosterItemBO;
import com.example.poc.dao.IRosterDAO;
import com.exemple.poc.client.dto.response.RosterDto;
import com.exemple.poc.client.dto.response.RosterItemDto;

import io.swagger.annotations.Api;

@Api(description = "Person API for CRUD")
@RestController
public class RosterController {

	@Autowired
	private IRosterDAO rosterDao;

	@GetMapping({"/rosters/{id}"})
	public RosterDto findPersonById(@PathVariable int id) {
		
		RosterBO rosterBO = rosterDao.findById(id).get();
		
		RosterDto rosterDto = new RosterDto();
		rosterDto.setTeamName(rosterBO.getTeam().getName());
		
		for (Iterator iterator = rosterBO.getRosterItems().iterator(); iterator.hasNext();) {
			RosterItemBO type = (RosterItemBO) iterator.next();
			RosterItemDto itemDto = new RosterItemDto();
			itemDto.setLastname(type.getPerson().getLastname());
			rosterDto.addItem(itemDto);
		}
		
		
		return rosterDto;
	}


}
