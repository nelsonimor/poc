package com.example.poc.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.example.poc.bo.BoxlineBO;
import com.example.poc.dao.IBoxlineDAO;
import com.example.poc.mapper.ObjectMapper;
import com.example.poc.service.IBoxlineService;
import com.exemple.poc.client.dto.response.BoxlineDto;

@Service
public class BoxlineService implements IBoxlineService {
	
	@Autowired
	private IBoxlineDAO boxlineDAO;

	@Override
	public List<BoxlineDto> findByPointsDesc(int limit) {
		Page<BoxlineBO> pageBoxlines = boxlineDAO.findAll(PageRequest.of(0, limit));
		return pageBoxlines.getContent().stream().map(e -> ObjectMapper.toBoxlineDto(e)).collect(Collectors.toList());
	}
	

	












}
