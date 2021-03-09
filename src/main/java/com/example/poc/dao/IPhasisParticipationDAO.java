package com.example.poc.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.poc.bo.PhasisParticipationBO;

public interface IPhasisParticipationDAO extends JpaRepository<PhasisParticipationBO, Integer> {
   
	List<PhasisParticipationBO> findAll();
	
	Optional<PhasisParticipationBO> findById(int id);
	
}
