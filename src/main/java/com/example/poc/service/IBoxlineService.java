package com.example.poc.service;

import java.util.List;

import com.exemple.poc.client.dto.response.BoxlineDto;

public interface IBoxlineService {
	
	List<BoxlineDto> findByPointsDesc(int limit);
	



}
