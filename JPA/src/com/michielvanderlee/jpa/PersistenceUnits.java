package com.michielvanderlee.jpa;

public enum PersistenceUnits
{
	ECLIPSELINK_DB2("eclipselink_db2"),
	ECLIPSELINK_MYSQL("eclipselink_mysql"),
	ECLIPSELINK_POSTGRESQL("eclipselink_postgresql"),
	HIBERNATE_DB2("hibernate_db2"),
	HIBERNATE_MYSQL("hibernate_mysql"),
	HIBERNATE_POSTGRESQL("hibernate_postgresql"),
	OPENJPA_DB2("openjpa_db2"),
	OPENJPA_MYSQL("openjpa_mysql"),
	OPENJPA_POSTGRESQL("openjpa_postgresql")
	;
	
	private PersistenceUnits(String name)
	{
		this.setName(name);
	}
	
	public String getName()
	{
		return name;
	}
	public void setName(String name)
	{
		this.name = name;
	}

	private String name;
}
