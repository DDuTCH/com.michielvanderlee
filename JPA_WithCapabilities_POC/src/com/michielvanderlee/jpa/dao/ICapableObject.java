package com.michielvanderlee.jpa.dao;

public interface ICapableObject
{
	public String getReadCapability();
	public String getWriteCapability();
	public String getDeleteCapability();
}
