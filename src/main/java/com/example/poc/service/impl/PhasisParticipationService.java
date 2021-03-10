package com.example.poc.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.poc.bo.PhasisBO;
import com.example.poc.bo.PhasisOrganizationBO;
import com.example.poc.bo.PhasisParticipationBO;
import com.example.poc.dao.IPhasisDAO;
import com.example.poc.dao.IPhasisOrganizationDAO;
import com.example.poc.dao.IPhasisParticipationDAO;
import com.example.poc.mapper.ObjectMapper;
import com.example.poc.service.IPhasisOrganizationService;
import com.example.poc.service.IPhasisParticipationService;
import com.example.poc.service.IPhasisService;
import com.exemple.poc.client.dto.response.PhasisDto;
import com.exemple.poc.client.dto.response.PhasisOrganizationDto;
import com.exemple.poc.client.dto.response.PhasisParticipationDto;

@Service
public class PhasisParticipationService implements IPhasisParticipationService {

	@Autowired
	private IPhasisParticipationDAO phasisParticipationDAO;


	@Override
	public PhasisParticipationDto findById(int id) {
		PhasisParticipationBO phasisParticipationBo = phasisParticipationDAO.findById(id).get();
		return ObjectMapper.toPhasisParticipationDto(phasisParticipationBo);
	}

	@Override
	public List<PhasisParticipationDto> findAll(){
		List<PhasisParticipationBO> phasisParticipationBos = phasisParticipationDAO.findAll();
		List<PhasisParticipationDto> phasisParticipationDtos = new ArrayList<PhasisParticipationDto>();
		for (Iterator iterator = phasisParticipationBos.iterator(); iterator.hasNext();) {
			PhasisParticipationBO phasisParticipationBO = (PhasisParticipationBO) iterator.next();
			phasisParticipationDtos.add(ObjectMapper.toPhasisParticipationDto(phasisParticipationBO));
		}
		return phasisParticipationDtos;

	}


}
