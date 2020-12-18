package com.example.poc.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.poc.bo.ContinentBO;
import com.example.poc.client.dto.request.ContinentRequest;
import com.example.poc.dao.ICustomContinentDAO;

@Repository
public class CustomContinentDAO implements ICustomContinentDAO {
	
	@Autowired
	EntityManager em;
	
	public List<ContinentBO> findContinents(ContinentRequest continentRequest) {
		try {
			CriteriaBuilder cb = em.getCriteriaBuilder();
	        CriteriaQuery<ContinentBO> cq = cb.createQuery(ContinentBO.class);
	        Root<ContinentBO> continent = cq.from(ContinentBO.class);
	        
	        List<Predicate> predicates = new ArrayList<Predicate>();
	        
	        
	        if(continentRequest.getNane()!=null) {
		        Predicate continentNamePredicate = cb.equal(continent.get("name"), continentRequest.getNane());
		        predicates.add(continentNamePredicate);
	        }
	        
	        if(continentRequest.getCode()!=null) {
		        Predicate continentCodePredicate = cb.equal(continent.get("code"),continentRequest.getCode());
		        predicates.add(continentCodePredicate);
	        }
	        
	        if(continentRequest.getId()!=null) {
	            Predicate continentIdPredicate = cb.equal(continent.get("id"),continentRequest.getId());
	            predicates.add(continentIdPredicate);
	        }
	        
			if (predicates.size() > 0) {
				cq.where(cb.and(predicates.toArray(new Predicate[predicates.size()])));
			}

	        TypedQuery<ContinentBO> query = em.createQuery(cq);
	        List<ContinentBO> result = query.getResultList();
	        return result;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

		
	}

	@Override
	public String get() {
		// TODO Auto-generated method stub
		return "Hello JUnit 5";
	}

}
