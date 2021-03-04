package com.example.poc.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.poc.bo.CompetitionBO;
import com.example.poc.bo.CompetitionOrganizationBO;

public interface ICompetitionOrganizationDAO extends JpaRepository<CompetitionOrganizationBO, Integer> {
   
	List<CompetitionOrganizationBO> findByCompetition(CompetitionBO competitionBO);

}
