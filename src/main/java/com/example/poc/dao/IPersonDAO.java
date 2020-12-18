package com.example.poc.dao;

import java.sql.Timestamp;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.poc.bo.PersonBO;

public interface IPersonDAO extends JpaRepository<PersonBO, Integer> {
   
	List<PersonBO> findAll();

   PersonBO findById(int id);

   PersonBO findByLastnameAndFirstname(String lname, String fname);
   
   PersonBO findByLastnameAndFirstnameAndBirthdate(String lname, String fname,Timestamp birthDate);
}
