package com.example.poc.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.poc.bo.PersonBO;
import com.example.poc.bo.RosterItemBO;

public interface IRosterItemDAO extends JpaRepository<RosterItemBO, Integer> {

	List<RosterItemBO> findByPerson(PersonBO personBo);
	
}
