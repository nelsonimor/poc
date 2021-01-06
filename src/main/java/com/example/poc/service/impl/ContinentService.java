package com.example.poc.service.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.poc.bo.ContinentBO;
import com.example.poc.client.dto.request.ContinentRequest;
import com.example.poc.dao.IContinentDAO;
import com.example.poc.dao.ICustomContinentDAO;
import com.example.poc.exception.AlreadyExistsException;
import com.example.poc.exception.NotFoundException;
import com.example.poc.mapper.ObjectMapper;
import com.example.poc.service.IContinentService;
import com.example.poc.service.IEventCreatorService;
import com.example.poc.util.ActionCode;
import com.exemple.poc.client.dto.response.ContinentDTO;

@Service
public class ContinentService implements IContinentService {
	
	@Autowired
	private IContinentDAO continentDAO;
	
	@Autowired
	private ICustomContinentDAO customContinentDAO;
	
	@Autowired
	private IEventCreatorService eventCreatorService;
	
	
	public ContinentService() {
		
	}

	@Override
	public List<ContinentDTO> findAllContinents() {
		List<ContinentBO> continentBOs = this.continentDAO.findAll();
		
		List<ContinentDTO> continentDTOs = new ArrayList<ContinentDTO>();
		continentBOs.stream().forEach(c -> {
			continentDTOs.add(ObjectMapper.toContinentDto(c));
		});
		return continentDTOs;
	}

	@Override
	public ContinentDTO findById(int id) throws NotFoundException {
		Optional<ContinentBO> continentBO = this.continentDAO.findById(id);
		System.out.println("-> ContinentService.findById() : id = "+id+" - valuer = "+continentBO);
		if(!continentBO.isPresent()) {
			throw new NotFoundException("No continent found with id = "+id);
		}
		return ObjectMapper.toContinentDto(continentBO.get());
	}

	@Override
	public ContinentDTO findByName(String name) {
		Optional<ContinentBO> continentBOs = this.continentDAO.findByName(name);
		return ObjectMapper.toContinentDto(continentBOs.get());
	}

	@Override
	public ContinentDTO findByCode(String code) {
		Optional<ContinentBO> continentBO = this.continentDAO.findByCode(code);
		return ObjectMapper.toContinentDto(continentBO.get());
	}

	@Override
	public List<ContinentDTO> findByRequest(ContinentRequest continentRequest) {
		List<ContinentBO> continentBOs = this.customContinentDAO.findContinents(continentRequest);
		List<ContinentDTO> continentDTOs = new ArrayList<ContinentDTO>();
		continentBOs.stream().forEach((c) -> continentDTOs.add(ObjectMapper.toContinentDto(c)));
		return continentDTOs;
	}

	@Transactional
	@Override
	public ContinentDTO addContinent(ContinentDTO continent) throws AlreadyExistsException {
		
		Optional<ContinentBO> c = this.continentDAO.findByNameAndCode(continent.getName(), continent.getCode());
		if(c.isPresent()) {
			eventCreatorService.createEventFailure(ActionCode.CONTINENT_ADD_FAILED_ALREADY_EXIST,new Object[] {continent.getName()});
			throw new AlreadyExistsException(ActionCode.CONTINENT_ADD_FAILED_ALREADY_EXIST,new Object[] {continent.getName()});		
		}
		
		ContinentBO continentBO = ObjectMapper.toContinentBo(continent);
		ContinentBO continentBOAdded = (ContinentBO)this.continentDAO.save(continentBO);
		eventCreatorService.createEventSuccess(ActionCode.CONTINENT_ADD_SUCCESS,new Object[] {continentBOAdded.getName(),continentBOAdded.getId()});
		return ObjectMapper.toContinentDto(continentBOAdded);
	}

	@Override
	public String get() {
		return customContinentDAO.get();
	}

	@Override
	public void deleteById(int id) throws NotFoundException {
		Optional<ContinentBO> continentBO = this.continentDAO.findById(id);
		if(!continentBO.isPresent()) {
			throw new NotFoundException("No continent found with id = "+id);
		}
		this.continentDAO.deleteById(id);
	}

	@Override
	public ContinentDTO updateContinent(ContinentDTO continent, int id) throws NotFoundException {
		Optional<ContinentBO> continentBO = this.continentDAO.findById(id);
		if(!continentBO.isPresent()) {
			throw new NotFoundException("No continent found with id = "+id);
		}
		ContinentBO bo = ObjectMapper.toContinentBo(continent);
		bo.setId(id);
		bo.setUpdateDate(new Timestamp(System.currentTimeMillis()));
		ContinentBO newBO = continentDAO.save(bo);
		return ObjectMapper.toContinentDto(newBO);
	}





}
