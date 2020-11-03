package com.example.poc;

import java.sql.Timestamp;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.validator.constraints.Length;

@Entity
@Table(
   name = "t_person"
)
public class Person {
   @Id
   @GeneratedValue(
      strategy = GenerationType.IDENTITY
   )
   private int id;
   @Length(
      min = 3,
      max = 20
   )
   private String lname;
   @Length(
      min = 3,
      max = 20
   )
   private String fname;
   @Length(
      min = 3,
      max = 20
   )
   private String comment;
   private Timestamp creationDate = new Timestamp(System.currentTimeMillis());
   private Timestamp updateDate;

   public int getId() {
      return this.id;
   }

   public void setId(int id) {
      this.id = id;
   }

   public String getLname() {
      return this.lname;
   }

   public void setLname(String lname) {
      this.lname = lname;
   }

   public String getFname() {
      return this.fname;
   }

   public void setFname(String fname) {
      this.fname = fname;
   }

   public String getComment() {
      return this.comment;
   }

   public void setComment(String comment) {
      this.comment = comment;
   }

   public Timestamp getCreationDate() {
      return this.creationDate;
   }

   public Timestamp getUpdateDate() {
      return this.updateDate;
   }

   public void setUpdateDate(Timestamp updateDate) {
      this.updateDate = updateDate;
   }
}
