package com.example.poc.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.poc.bo.CompetitionBO;
import com.example.poc.dao.ICompetitionDAO;
import com.example.poc.service.ICompetitionService;

@Service
public class CompetitionService implements ICompetitionService {
	
	@Autowired
	private ICompetitionDAO competitionDAO;
	
	@Autowired

	
	public CompetitionService() {
		
	}

	@Transactional
	@Override
	public List<CompetitionBO> findAll() {
		return competitionDAO.findAll();
	}


}
