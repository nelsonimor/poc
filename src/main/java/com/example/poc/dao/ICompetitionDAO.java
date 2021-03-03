package com.example.poc.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.poc.bo.CompetitionBO;

public interface ICompetitionDAO extends JpaRepository<CompetitionBO, Integer> {
   
	List<CompetitionBO> findAll();

}
