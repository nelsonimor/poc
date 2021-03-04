package com.example.poc.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.poc.bo.CompetitionBO;

public interface ICompetitionDAO extends JpaRepository<CompetitionBO, Integer> {
   
	List<CompetitionBO> findAll();
	
	Optional<CompetitionBO> findById(int id);

}
