package com.example.poc.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.poc.bo.BoxlineBO;

public interface IBoxlineDAO extends JpaRepository<BoxlineBO, Integer> {
   
	Page<BoxlineBO> findAllByOrderByPointsDesc(PageRequest pageRequest);
	


}
