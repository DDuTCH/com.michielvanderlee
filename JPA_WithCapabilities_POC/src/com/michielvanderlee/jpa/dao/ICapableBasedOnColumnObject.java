package com.michielvanderlee.jpa.dao;

public interface ICapableBasedOnColumnObject extends ICapableObject
{
	public String getReadCapabilityBasedOnColumn();
	public String getWriteCapabilityBasedOnColumn();
	public String getDeleteCapabilityBasedOnColumn();
}
