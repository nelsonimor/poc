package com.example.poc.service.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.poc.bo.ContinentBO;
import com.example.poc.client.dto.request.ContinentRequest;
import com.example.poc.dao.IContinentDAO;
import com.example.poc.dao.ICustomContinentDAO;
import com.example.poc.exception.AlreadyExistsException;
import com.example.poc.exception.NotFoundException;
import com.example.poc.mapper.ObjectMapper;
import com.example.poc.service.IContinentService;
import com.exemple.poc.client.dto.response.ContinentDTO;

@Service
public class ContinentService implements IContinentService {
	
	@Autowired
	private IContinentDAO continentDAO;
	
	@Autowired
	private ICustomContinentDAO customContinentDAO;
	
	
	public ContinentService() {
		
	}

	@Override
	public List<ContinentDTO> findAllContinents() {
		List<ContinentBO> continentBOs = this.continentDAO.findAll();
		List<ContinentDTO> continentDTOs = new ArrayList<ContinentDTO>();
		continentBOs.stream().forEach((c) -> continentDTOs.add(ObjectMapper.toContinentDto(c)));
		return continentDTOs;
	}

	@Override
	public ContinentDTO findById(int id) throws NotFoundException {
		ContinentBO continentBO = this.continentDAO.findById(id);
		if(continentBO==null) {
			throw new NotFoundException("No continent found with id = "+id);
		}
		return ObjectMapper.toContinentDto(continentBO);
	}

	@Override
	public List<ContinentDTO> findByName(String name) {
		List<ContinentBO> continentBOs = this.continentDAO.findByName(name);
		List<ContinentDTO> continentDTOs = new ArrayList<ContinentDTO>();
		continentBOs.stream().forEach((c) -> continentDTOs.add(ObjectMapper.toContinentDto(c)));
		return continentDTOs;
	}

	@Override
	public List<ContinentDTO> findByCode(String code) {
		List<ContinentBO> continentBOs = this.continentDAO.findByCode(code);
		List<ContinentDTO> continentDTOs = new ArrayList<ContinentDTO>();
		continentBOs.stream().forEach((c) -> continentDTOs.add(ObjectMapper.toContinentDto(c)));
		return continentDTOs;
	}

	@Override
	public List<ContinentDTO> findByRequest(ContinentRequest continentRequest) {
		List<ContinentBO> continentBOs = this.customContinentDAO.findContinents(continentRequest);
		List<ContinentDTO> continentDTOs = new ArrayList<ContinentDTO>();
		continentBOs.stream().forEach((c) -> continentDTOs.add(ObjectMapper.toContinentDto(c)));
		return continentDTOs;
	}

	@Override
	public ContinentDTO addContinent(ContinentDTO continent) throws AlreadyExistsException {
		
		List<ContinentBO> continents = this.continentDAO.findByNameAndCode(continent.getName(), continent.getCode());
		if(continents!=null && continents.size()>0) {
			throw new AlreadyExistsException("Continent with name : "+continent.getName()+" and code : "+continent.getCode()+" already exists");
		}
		
		ContinentBO continentBO = ObjectMapper.toContinentBo(continent);
		ContinentBO continentBOAdded = (ContinentBO)this.continentDAO.save(continentBO);
		return ObjectMapper.toContinentDto(continentBOAdded);
	}

	@Override
	public String get() {
		return customContinentDAO.get();
	}

	@Override
	public void deleteById(int id) throws NotFoundException {
		ContinentBO continentBO = this.continentDAO.findById(id);
		if(continentBO==null) {
			throw new NotFoundException("No continent found with id = "+id);
		}
		this.continentDAO.deleteById(id);
	}

	@Override
	public ContinentDTO updateContinent(ContinentDTO continent, int id) throws NotFoundException {
		ContinentBO continentBO = this.continentDAO.findById(id);
		if(continentBO==null) {
			throw new NotFoundException("No continent found with id = "+id);
		}
		ContinentBO bo = ObjectMapper.toContinentBo(continent);
		bo.setId(id);
		bo.setUpdateDate(new Timestamp(System.currentTimeMillis()));
		ContinentBO newBO = continentDAO.save(bo);
		return ObjectMapper.toContinentDto(newBO);
	}



}
