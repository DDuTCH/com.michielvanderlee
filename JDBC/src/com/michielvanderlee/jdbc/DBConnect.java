package com.michielvanderlee.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class DBConnect
{
	private Connection con;
	private Statement st;
	private ResultSet rs;
	
	public DBConnect(Database database) 
	{
		try 
		{
			con = database.getConnection();
			st = con.createStatement();
			
		} catch(Exception e) 
		{
			System.err.println("Error: " + e);
			e.printStackTrace();
		}
	}
	
	public void getData()
	{
		try 
		{
			String query = "SELECT * FROM persons";
			rs = st.executeQuery(query);
			
			System.out.println("Records from db:");
			while(rs.next())
			{
				String name = rs.getString("name");
				String age = rs.getString("age");
				
				System.out.println("Name: " + name + ", Age: " + age);
			}
			
		} catch(Exception e) 
		{
			System.err.println("Error: " + e);
			e.printStackTrace();
		}
	}
}
