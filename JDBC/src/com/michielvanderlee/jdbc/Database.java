package com.michielvanderlee.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public enum Database
{
	MYSQL("jdbc:mysql://localhost:3306/test", "", "root", ""),
	POSTGRESQL("jdbc:postgresql://localhost:5432/postgres", "", "test", "test");
	
	
	private Database(String url, String schema, String user, String password)
	{
		this.url = url;
		this.schema = schema;
		this.user = user;
		this.password = password;
	}
	
	public Connection getConnection() throws SQLException
	{
		Connection con = DriverManager.getConnection(url, user, password);
		if(schema != null && !schema.isEmpty())
		{
			con.setSchema(schema);
		}
		return con;
	}
	
	private String url;
	private String schema;
	private String user;
	private String password;
}
