package com.example.poc.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.poc.bo.PhasisBO;
import com.example.poc.dao.IPhasisDAO;
import com.example.poc.mapper.ObjectMapper;
import com.example.poc.service.IPhasisService;
import com.exemple.poc.client.dto.response.PhasisDTO;

@Service
public class PhasisService implements IPhasisService {

	@Autowired
	private IPhasisDAO phasisDao;


	@Override
	public PhasisDTO findById(int id) {
		PhasisBO phasisBo = phasisDao.findById(id).get();
		return ObjectMapper.toPhasisDto(phasisBo);
	}

	@Override
	public List<PhasisDTO> findAll(){
		List<PhasisBO> phasisBos = phasisDao.findAll();
		List<PhasisDTO> phasisDtos = new ArrayList<PhasisDTO>();
		for (Iterator iterator = phasisBos.iterator(); iterator.hasNext();) {
			PhasisBO phasisBO = (PhasisBO) iterator.next();
			phasisDtos.add(ObjectMapper.toPhasisDto(phasisBO));
		}
		return phasisDtos;

	}


}
