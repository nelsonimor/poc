package com.example.poc.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.poc.exception.AlreadyExistsException;
import com.example.poc.exception.NotFoundException;
import com.example.poc.service.ICityService;
import com.exemple.poc.client.dto.response.CityDTO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(description = "City API for CRUD")
@RestController
public class CityController {

	static Logger logger = LoggerFactory.getLogger(CityController.class);

	@Autowired
	private ICityService cityService;

	@ApiOperation("Retrieve all cities")
	@RequestMapping(value = {"/Cities"},method = {RequestMethod.GET})
	public List<CityDTO> getAllCities() {
		return cityService.findAllCities();
	}
	
	@ApiOperation("Add new city")
	@PostMapping({"/Cities"})
	public ResponseEntity<Void> addCity(@RequestBody @Valid CityDTO city) throws AlreadyExistsException,NotFoundException  {
		CityDTO newCityDTO = cityService.addCity(city);
		URI location = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(new Object[]{newCityDTO.getId()})
				.toUri();
		return ResponseEntity.created(location).build();
	}




}
