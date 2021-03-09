package com.example.poc.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.poc.bo.PhasisBO;
import com.example.poc.bo.PhasisOrganizationBO;
import com.example.poc.dao.IPhasisDAO;
import com.example.poc.dao.IPhasisOrganizationDAO;
import com.example.poc.mapper.ObjectMapper;
import com.example.poc.service.IPhasisOrganizationService;
import com.example.poc.service.IPhasisService;
import com.exemple.poc.client.dto.response.PhasisDTO;
import com.exemple.poc.client.dto.response.PhasisOrganizationDto;

@Service
public class PhasisOrganizationService implements IPhasisOrganizationService {

	@Autowired
	private IPhasisOrganizationDAO phasisOrganizationDAO;


	@Override
	public PhasisOrganizationDto findById(int id) {
		PhasisOrganizationBO phasisOrganizationBo = phasisOrganizationDAO.findById(id).get();
		return ObjectMapper.toPhasisOrganizationDto(phasisOrganizationBo);
	}

	@Override
	public List<PhasisOrganizationDto> findAll(){
		List<PhasisOrganizationBO> phasisOrganizationBos = phasisOrganizationDAO.findAll();
		List<PhasisOrganizationDto> phasisOrganizationDtos = new ArrayList<PhasisOrganizationDto>();
		for (Iterator iterator = phasisOrganizationBos.iterator(); iterator.hasNext();) {
			PhasisOrganizationBO phasisOrganizationBO = (PhasisOrganizationBO) iterator.next();
			phasisOrganizationDtos.add(ObjectMapper.toPhasisOrganizationDto(phasisOrganizationBO));
		}
		return phasisOrganizationDtos;

	}


}
