package com.example.poc;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPersonDAO extends JpaRepository<Person, Integer> {
   List<Person> findAll();

   Person findById(int id);

   Person findByLnameAndFname(String lname, String fname);
}
