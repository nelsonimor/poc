package com.example.poc.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.poc.bo.GameBO;

public interface IGameDAO extends JpaRepository<GameBO, Integer> {
   
	List<GameBO> findAll();
	
}
