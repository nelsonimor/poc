package com.example.poc.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.poc.bo.CompetitionOrganizationBO;
import com.example.poc.bo.CompetitionParticipationBO;

public interface ICompetitionParticipationDAO extends JpaRepository<CompetitionParticipationBO, Integer> {
   
	List<CompetitionParticipationBO> findByCompetitionOrganization(CompetitionOrganizationBO competitionOrganizationBO);

}
