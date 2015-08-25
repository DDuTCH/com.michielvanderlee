package com.michielvanderlee.jpa;

import javax.persistence.PostLoad;
import javax.persistence.PostPersist;
import javax.persistence.PrePersist;

public class MyListener
{
	//********************************************************************
	// Constructors
	//********************************************************************

	//********************************************************************
	// Methods
	//********************************************************************
	@PostLoad
	private void onPostLoad(Object o)
	{
		System.out.println("Post load");
	}
	
	@PrePersist
	private void onPrePersists(Object o)
	{
		System.out.println("Pre persist");
	}
	
	@PostPersist
	private void onPostPersist(Object o)
	{
		System.out.println("Post persist");
	}
	
	//********************************************************************
	// getters and setters.
	//********************************************************************

	//********************************************************************
	// Properties
	//********************************************************************
}
