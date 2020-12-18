package com.example.poc.service;

import java.util.List;

import com.example.poc.exception.AlreadyExistsException;
import com.example.poc.exception.NotFoundException;
import com.exemple.poc.client.dto.response.PersonDTO;

public interface IPersonService {
	
	List<PersonDTO> findAllPersons();
	
	PersonDTO findPersonById(int id);
	
	PersonDTO addPerson(PersonDTO person) throws AlreadyExistsException,NotFoundException;
	
	PersonDTO updatePerson(PersonDTO person) throws AlreadyExistsException,NotFoundException;
	
	PersonDTO deletePerson(int id) throws AlreadyExistsException,NotFoundException;


}
