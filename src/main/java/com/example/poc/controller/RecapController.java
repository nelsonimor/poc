package com.example.poc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.poc.service.IRecapService;
import com.exemple.poc.client.dto.response.RecapDto;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(description = "Recap API")
@RestController
public class RecapController {

	@Autowired
	private IRecapService recapService;

	@ApiOperation("Retrieve teams")
	@RequestMapping(value = {"/admin/recap"},method = {RequestMethod.GET})
	public RecapDto recap() {
		return recapService.getRecap();
	}




}
