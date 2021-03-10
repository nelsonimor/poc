package com.example.poc.service;

import java.util.List;

import com.example.poc.exception.AlreadyExistsException;
import com.example.poc.exception.NotFoundException;
import com.exemple.poc.client.dto.response.PersonDto;

public interface IPersonService {
	
	List<PersonDto> findAllPersons();
	
	PersonDto findPersonById(int id);
	
	PersonDto addPerson(PersonDto person) throws AlreadyExistsException,NotFoundException;
	
	PersonDto updatePerson(PersonDto person) throws AlreadyExistsException,NotFoundException;
	
	PersonDto deletePerson(int id) throws AlreadyExistsException,NotFoundException;


}
