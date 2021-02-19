package com.example.poc.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Service;

import com.example.poc.service.IPersonalStatService;
import com.exemple.poc.client.dto.response.PersonalStatDto;

@Service
public class PersonalStatService implements IPersonalStatService {
	
	@Autowired
	private NamedParameterJdbcOperations jdbcOperations;

	@Override
	public List<PersonalStatDto> getPersonalStats() {
		StringBuilder query = new StringBuilder();
		query.append("select 'cole' as lastname, sum(points) as pointsAvg from t_boxline where fk_person_id = 1");
		List<PersonalStatDto> p = jdbcOperations.query(query.toString(),new BeanPropertyRowMapper<PersonalStatDto>(PersonalStatDto.class));
		return p;
	}
	



}
