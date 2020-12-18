package com.example.poc.bo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.validator.constraints.Length;

@Entity @Table(name = "t_continent")
public class ContinentBO extends CoreBO {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Length(min = 2,max = 2)
	@Column(unique = true)
	private String code;

	@Length(min = 3,max = 15)
	@Column(unique = true)
	private String name;
	
	
	public ContinentBO() {
		
	}
	
	public ContinentBO(int id,String code,String name) {
		this.id = id;
		this.name = name;
		this.code = code;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}




}
