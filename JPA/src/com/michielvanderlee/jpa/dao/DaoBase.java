package com.michielvanderlee.jpa.dao;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

import com.michielvanderlee.jpa.MyListener;

@MappedSuperclass
@EntityListeners(MyListener.class)
public abstract class DaoBase
{
	//********************************************************************
	// Constructors
	//********************************************************************	
	public DaoBase()
	{
		this.createdDateTime = Calendar.getInstance();
	}
	
	public DaoBase(String name)
	{
		this.createdDateTime = Calendar.getInstance();
		this.name = name;
	}
	
	public DaoBase(String name, String description)
	{
		this.createdDateTime = Calendar.getInstance();
		this.name = name;
		this.description = description;
	}
	
	//********************************************************************
	// Methods
	//********************************************************************
	public void updateModified()
	{
		this.lastModifiedDateTime = Calendar.getInstance();
	}
	
	
	//********************************************************************
	// getters and setters.
	//********************************************************************
	public String getName()
	{
		return name;
	}
	public void setName(String name)
	{
		updateModified();
		this.name = name;
	}

	public String getDescription()
	{
		return description;
	}
	public void setDescription(String description)
	{
		updateModified();
		this.description = description;
	}

	public long getVersion()
	{
		return version;
	}
	public void setVersion(long version)
	{
		this.version = version;
	}

	public Calendar getCreatedDateTime()
	{
		return createdDateTime;
	}
	public void setCreatedDateTime(Calendar createdDateTime)
	{
		this.createdDateTime = createdDateTime;
	}

	public Calendar getLastModifiedDateTime()
	{
		return lastModifiedDateTime;
	}
	public void setLastModifiedDateTime(Calendar lastModifiedDateTime)
	{
		this.lastModifiedDateTime = lastModifiedDateTime;
	}

	//********************************************************************
	// Properties
	//********************************************************************
	@Column(nullable = false)
	private String name					= null;
	@Column
	private String description			= null;
	@Version
	private long version				= 0L;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar createdDateTime		= null;
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar lastModifiedDateTime	= null;

}
