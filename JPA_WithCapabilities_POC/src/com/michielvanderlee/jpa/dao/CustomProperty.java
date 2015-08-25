package com.michielvanderlee.jpa.dao;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.michielvanderlee.jpa.annotation.ColumnBasedCapability;

@ColumnBasedCapability(column="database", values={"events", "flows"})
public class CustomProperty extends DaoBase implements ICapableBasedOnColumnObject
{
	//********************************************************************
	// Constructors
	//********************************************************************

	//********************************************************************
	// Methods
	//********************************************************************
	@Override
	public String getReadCapabilityBasedOnColumn()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getWriteCapabilityBasedOnColumn()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getDeleteCapabilityBasedOnColumn()
	{
		// TODO Auto-generated method stub
		return null;
	}
	
	//********************************************************************
	// getters and setters.
	//********************************************************************
	public long getId()
	{
		return id;
	}
	public void setId(long id)
	{
		this.id = id;
	}
	
	public String getDataBase()
	{
		return database;
	}
	public void setDataBase(String database)
	{
		this.database = database;
	}
	
	//********************************************************************
	// Properties
	//********************************************************************
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	@Column
	private String database;
	
}
