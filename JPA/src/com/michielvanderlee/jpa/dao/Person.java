package com.michielvanderlee.jpa.dao;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.google.gson.Gson;

@Entity
@Table(name="PERSONS")
@NamedQueries({
	@NamedQuery(name="Person.findAll",
				query="SELECT p FROM Person p"),
	@NamedQuery(name="Person.findByName",
				query="SELECT p FROM Person p WHERE p.name = :name")
})
public class Person extends DaoBase
{
	//********************************************
	// Constructors
	//********************************************
	public Person() 
	{
		super();
	}
	
	public Person(String name, int age)
	{
		super(name);
		this.age = age;
	}
	
	//********************************************
	// Methods
	//********************************************
	@Override
	public String toString()
	{
		return new Gson().toJson(this);
	}
	
	//********************************************
	// getters and setters.
	//********************************************
	public long getId()
	{
		return id;
	}
	public void setId(long id)
	{
		this.id = id;
	}
	
	public int getAge()
	{
		return age;
	}
	public void setAge(int age)
	{
		updateModified();
		this.age = age;
	}

	public byte[] getData()
	{
		return data;
	}
	public void setData(byte[] data)
	{
		this.data = data;
	}

	//********************************************
	// Properties
	//********************************************
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	@Column
	private int age;	
	
	@Column
	private byte[] data;

	

}
