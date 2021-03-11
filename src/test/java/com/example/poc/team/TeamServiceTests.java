package com.example.poc.team;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.poc.bo.ContinentBO;
import com.example.poc.bo.CountryBO;
import com.example.poc.bo.TeamBO;
import com.example.poc.dao.ICountryDAO;
import com.example.poc.dao.ITeamDAO;
import com.example.poc.service.ICountryService;
import com.example.poc.service.IEventCreatorService;
import com.example.poc.service.ITeamService;
import com.example.poc.service.impl.CountryService;
import com.example.poc.service.impl.TeamService;
import com.exemple.poc.client.dto.response.CityDto;
import com.exemple.poc.client.dto.response.TeamDto;

@SpringBootTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class TeamServiceTests {
	
	@Mock
    private ITeamDAO teamDAO;
	
	@Mock
    private ICountryDAO countryDAO;
	
	@Mock
	private IEventCreatorService eventCreatorService;
	
	@InjectMocks
    private ITeamService teamService = new TeamService();
	
	private List<TeamBO> teamBOs = new ArrayList<TeamBO>();
	
	private ContinentBO europe = new ContinentBO(1,"EU","Europe");
	private CountryBO france = new CountryBO(1, "France", "FR", "FRA", "French", europe);

	
	@BeforeEach
    void setMockOutput() {
		Optional<CountryBO> optFrance = Optional.of((CountryBO) france);
		
		
		TeamBO teamFrance = new TeamBO();
		teamFrance.setName("Team France");
		teamFrance.setCountry(france);
		teamBOs.add(teamFrance);
        Mockito.when(teamDAO.findAll()).thenReturn(teamBOs);
        Mockito.when(countryDAO.findByName(Mockito.any())).thenReturn(optFrance);
        Mockito.when(teamDAO.save(Mockito.any())).thenReturn(teamFrance);
    }
	
	@DisplayName("Teams : findAll")
    @Test
    void testFindAll() {
        assertEquals(1, teamService.findAllTeams().getTeams().size());
    }
	
	@DisplayName("Team : add city success")
    @Test
    void testAddTeamSuccess() {
		TeamDto teamDto = new TeamDto();
		teamDto.setName("Team France");
		teamDto.setCountryName("France");
		TeamDto dto = teamService.addTeam(teamDto);
		Assertions.assertNotNull(dto);
    }
	

}
