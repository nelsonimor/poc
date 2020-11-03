package com.example.poc;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.net.URI;
import java.sql.Timestamp;
import java.util.List;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@Api(
   description = "Person API for CRUD"
)
@RestController
public class PersonController {
   static Logger logger = LoggerFactory.getLogger(PersonController.class);
   @Autowired
   private IPersonDAO personDAO;

   @ApiOperation("Retrieve persons")
   @RequestMapping(
      value = {"/Persons"},
      method = {RequestMethod.GET}
   )
   public List<Person> showPersons() {
      logger.debug("PersonController.showPersons()");
      return this.personDAO.findAll();
   }

   @GetMapping({"/Persons/{id}"})
   public Person showPerson(@PathVariable int id) {
      logger.debug("PersonController.showPerson() id = {}", id);
      return this.personDAO.findById(id);
   }

   @PostMapping({"/Persons"})
   public ResponseEntity<Void> addPerson(@RequestBody @Valid Person person) throws Exception {
      logger.debug("PersonController.addPerson()");
      Person p = this.personDAO.findByLnameAndFname(person.getLname(), person.getFname());
      if (null == p) {
         logger.debug("PersonController.addPerson() not already exist");
         Person personAdded = (Person)this.personDAO.save(person);
         if (personAdded == null) {
            return ResponseEntity.noContent().build();
         } else {
            URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(new Object[]{personAdded.getId()}).toUri();
            return ResponseEntity.created(location).build();
         }
      } else {
         throw new Exception("Person already exists (" + p.getFname() + " " + p.getLname() + " [id = " + p.getId() + "])");
      }
   }

   @DeleteMapping({"/Persons/{id}"})
   public void deletePersonById(@PathVariable int id) {
      this.personDAO.deleteById(id);
   }

   @PutMapping({"/Persons/{id}"})
   public ResponseEntity<Person> updatePerson(@RequestBody Person person, @PathVariable int id) {
      logger.debug("PersonController.updatePerson() id {} ", id);
      Person p = this.personDAO.findById(id);
      if (p == null) {
         logger.debug("PersonController.updatePerson() no user found");
         return ResponseEntity.notFound().build();
      } else {
         person.setId(id);
         person.setUpdateDate(new Timestamp(System.currentTimeMillis()));
         this.personDAO.save(person);
         return ResponseEntity.noContent().build();
      }
   }
}
