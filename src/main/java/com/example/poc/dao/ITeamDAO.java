package com.example.poc.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.poc.bo.TeamBO;

public interface ITeamDAO extends JpaRepository<TeamBO, Integer> {
   
	List<TeamBO> findAll();
	
	Optional<TeamBO> findByName(String name);
	
}
