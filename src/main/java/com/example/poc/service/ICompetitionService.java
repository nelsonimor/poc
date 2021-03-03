package com.example.poc.service;

import java.util.List;

import com.example.poc.bo.CompetitionBO;

public interface ICompetitionService {
	
	List<CompetitionBO> findAll();
	
}
