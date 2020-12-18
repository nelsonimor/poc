package com.example.poc.continent;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.poc.bo.ContinentBO;
import com.example.poc.client.dto.request.ContinentRequest;
import com.example.poc.dao.IContinentDAO;
import com.example.poc.dao.ICustomContinentDAO;
import com.example.poc.dao.impl.CustomContinentDAO;

@SpringBootTest
public class CustomContinentDAOTests {
	
	private ContinentBO europe = new ContinentBO(8, "EU", "Europe");
	
	@Mock
    private IContinentDAO continentDAO;
	
	@Mock
    private EntityManager entityManager;
	
	@Mock
    private CriteriaBuilder fakeCriteriaBuilder;
	
	@Mock
    private CriteriaQuery<Object> fakeCriteriaQuery;
	
	@Mock
    private Predicate fakePredicate;
	
	@Mock
	private Root<ContinentBO> fakeRoot;
	
	@Mock
	private TypedQuery<Object> fakeQuery;
	
	@InjectMocks
    private ICustomContinentDAO customContinentDAO = new CustomContinentDAO();
	
	@BeforeEach
    void setMockOutput() {

	}
	
	@DisplayName("CustomContinentDAO : findByRequest")
    @Test
    void testFindByRequest() {
		List<Object> continents = new ArrayList<Object>();
		continents.add(europe);
		
        Mockito.when(entityManager.getCriteriaBuilder()).thenReturn(fakeCriteriaBuilder);
        Mockito.when(fakeCriteriaBuilder.createQuery(Mockito.any())).thenReturn(fakeCriteriaQuery);
        Mockito.when(fakeCriteriaQuery.from(ContinentBO.class)).thenReturn(fakeRoot);
        Mockito.when(fakeCriteriaBuilder.equal(Mockito.any(),Mockito.any())).thenReturn(fakePredicate);
        Mockito.when(entityManager.createQuery(fakeCriteriaQuery)).thenReturn(fakeQuery);
        Mockito.when(fakeQuery.getResultList()).thenReturn(continents);
        assertEquals(1, customContinentDAO.findContinents(new ContinentRequest(1, "Europe", "EU")).size());
    }
	
	

}
