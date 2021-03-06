package com.example.poc.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.poc.bo.RosterBO;
import com.example.poc.bo.TeamBO;

public interface IRosterDAO extends JpaRepository<RosterBO, Integer> {

	Optional<RosterBO> findById(int id);
	
	List<RosterBO> findByTeam(TeamBO teamBo);
	
}
