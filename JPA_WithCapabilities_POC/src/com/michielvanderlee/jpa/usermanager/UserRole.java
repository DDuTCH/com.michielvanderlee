package com.michielvanderlee.jpa.usermanager;

import java.util.Properties;

public class UserRole
{
	//********************************************************************
	// Constructors
	//********************************************************************
	public UserRole( String name )
	{
		this.name = name;
		this.capabilities = new Properties();
	}
	
	//********************************************************************
	// Methods
	//********************************************************************
	public void addCapability( String capabilityName, Boolean authorized )
	{
		this.capabilities.setProperty(capabilityName, authorized.toString());
	}

	public boolean hasCapability( String capabilityName )
	{
		return Boolean.valueOf( this.capabilities.getProperty(capabilityName, "false") );
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
		this.name = name;
	}
	
	public Properties getCapabilities()
	{
		return capabilities;
	}
	public void setCapabilities(Properties capabilities)
	{
		this.capabilities = capabilities;
	}

	//********************************************************************
	// Properties
	//********************************************************************
	private String name;
	private Properties capabilities;
}
