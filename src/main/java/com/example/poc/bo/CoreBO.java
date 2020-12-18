package com.example.poc.bo;

import java.sql.Timestamp;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class CoreBO {

	private Timestamp creationDate = new Timestamp(System.currentTimeMillis());
	
	private Timestamp updateDate;

	public Timestamp getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(Timestamp creationDate) {
		this.creationDate = creationDate;
	}
	public Timestamp getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Timestamp updateDate) {
		this.updateDate = updateDate;
	}
}
