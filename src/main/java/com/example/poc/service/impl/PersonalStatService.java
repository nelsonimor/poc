package com.example.poc.service.impl;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Service;

import com.example.poc.bo.PersonBO;
import com.example.poc.dao.IPersonDAO;
import com.example.poc.mapper.ObjectMapper;
import com.example.poc.service.IPersonalStatService;
import com.example.poc.util.NestedRowMapper;
import com.exemple.poc.client.dto.response.CompetitionStatDto;
import com.exemple.poc.client.dto.response.PersonalStatDto;
import com.exemple.poc.client.dto.response.PhasisStatDto;
import com.exemple.poc.client.dto.response.SeasonStatDto;
import com.exemple.poc.client.dto.response.StatDto;

@Service
public class PersonalStatService implements IPersonalStatService {
	
	@Autowired
	private NamedParameterJdbcOperations jdbcOperations;
	
	@Autowired
	private IPersonDAO personDAO;

	@Override
	public PersonalStatDto getPersonalStats(Integer playerId) {
		
		PersonalStatDto personalStatDto = new PersonalStatDto();
		
		Optional<PersonBO> personBO = personDAO.findById(playerId);
		if(personBO.isPresent()) {
			personalStatDto.setPlayer(ObjectMapper.toPersonDTO(personBO.get()));
		}
		
		
		StringBuilder query = new StringBuilder();
		query.append(""
				+ "select "
				+ "count(b.*) as nbGames,"
				+ "sum(b.points) as totalPoints,"
				+ "max(b.points) as maxPoints,"
				+ "(sum(b.points)/count(b.*)) as pointsAvg "
				+ "from t_boxline b "
				+ "where b.fk_person_id = "+playerId);
		List<StatDto> statDtos = jdbcOperations.query(query.toString(),new BeanPropertyRowMapper<StatDto>(StatDto.class));
		personalStatDto.setGlobalStat(statDtos.get(0));
		
		query = new StringBuilder();
		query.append(""
				+ "select "
				+ "count(b.*) as \"statDto.nbGames\","
				+ "sum(b.points) as \"statDto.totalPoints\","
				+ "max(b.points) as \"statDto.maxPoints\","
				+ "(sum(b.points)/count(b.*)) as \"statDto.pointsAvg\","
				+ "t.name as \"teamName\","
				+ "r.startdate as \"start\","
				+ "r.enddate as \"end\" "
				+ "from t_boxline b "
				+ "inner join t_roster r on b.fk_roster_id = r.id "
				+ "inner join t_team t on r.fk_team_id = t.id "
				+ "where b.fk_person_id = "+playerId+" group by b.fk_roster_id");
		List<SeasonStatDto> seasonStatDtos = jdbcOperations.query(query.toString(),new NestedRowMapper<SeasonStatDto>(SeasonStatDto.class));
		personalStatDto.setSeasonStatDTOs(seasonStatDtos);
		
		query = new StringBuilder();
		query.append(""
				+ "select "
				+ "count(b.*) as \"statDto.nbGames\","
				+ "sum(b.points) as \"statDto.totalPoints\","
				+ "max(b.points) as \"statDto.maxPoints\","
				+ "(sum(b.points)/count(b.*)) as \"statDto.pointsAvg\","
				+ "t.name as \"teamName\","
				+ "r.startdate as \"start\","
				+ "r.enddate as \"end\", "
				+ "c.name as \"competitionName\" "
				+ "from t_boxline b "
				+ "inner join t_roster r on b.fk_roster_id = r.id "
				+ "inner join t_team t on r.fk_team_id = t.id "
				+ "inner join t_game g on g.id = b.fk_game_id "
				+ "inner join t_phasis_organization p on g.fk_phasis_organization_id = p.id "
				+ "inner join t_phasis ph on ph.id = p.fk_phasis_id "
				+ "inner join t_competition c on c.id = ph.fk_competition_id "
				+ "where b.fk_person_id = "+playerId+" group by b.fk_roster_id,c.id");
		List<CompetitionStatDto> competitionStatDtos = jdbcOperations.query(query.toString(),new NestedRowMapper<CompetitionStatDto>(CompetitionStatDto.class));
		personalStatDto.setCompetitionStatDTOs(competitionStatDtos);
		
		query = new StringBuilder();
		query.append(""
				+ "select "
				+ "count(b.*) as \"statDto.nbGames\","
				+ "sum(b.points) as \"statDto.totalPoints\","
				+ "max(b.points) as \"statDto.maxPoints\","
				+ "(sum(b.points)/count(b.*)) as \"statDto.pointsAvg\","
				+ "t.name as \"teamName\","
				+ "r.startdate as \"start\","
				+ "r.enddate as \"end\", "
				+ "c.name as \"competitionName\", "
				+ "ph.name as \"phasisName\" "
				+ "from t_boxline b "
				+ "inner join t_roster r on b.fk_roster_id = r.id "
				+ "inner join t_team t on r.fk_team_id = t.id "
				+ "inner join t_game g on g.id = b.fk_game_id "
				+ "inner join t_phasis_organization p on g.fk_phasis_organization_id = p.id "
				+ "inner join t_phasis ph on ph.id = p.fk_phasis_id "
				+ "inner join t_competition c on c.id = ph.fk_competition_id "
				+ "where b.fk_person_id = "+playerId+" group by b.fk_roster_id,c.id,ph.name");
		List<PhasisStatDto> phasisStatDtos = jdbcOperations.query(query.toString(),new NestedRowMapper<PhasisStatDto>(PhasisStatDto.class));
		personalStatDto.setPhasisStatsDTOs(phasisStatDtos);
		
		return personalStatDto;
	}
	



}
