package com.example.poc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.poc.bo.RosterBO;
import com.example.poc.dao.IRosterDAO;

import io.swagger.annotations.Api;

@Api(description = "Person API for CRUD")
@RestController
public class RosterController {

	@Autowired
	private IRosterDAO rosterDao;

	@GetMapping({"/rosters/{id}"})
	public RosterBO findPersonById(@PathVariable int id) {
		return rosterDao.findById(id).get();
	}


}
