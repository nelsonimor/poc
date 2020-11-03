package com.example.poc.file;

import java.sql.Timestamp;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(
   name = "t_file"
)
public class FileBO {
   @Id
   @GeneratedValue(
      strategy = GenerationType.IDENTITY
   )
   private int id;
   private String filename;
   private String filepath;
   private String filetype;
   private Timestamp creationDate = new Timestamp(System.currentTimeMillis());

   public String getFilename() {
      return this.filename;
   }

   public void setFilename(String filename) {
      this.filename = filename;
   }

   public String getFilepath() {
      return this.filepath;
   }

   public void setFilepath(String filepath) {
      this.filepath = filepath;
   }

   public String getFiletype() {
      return this.filetype;
   }

   public void setFiletype(String filetype) {
      this.filetype = filetype;
   }

   public Timestamp getCreationDate() {
      return this.creationDate;
   }
}
