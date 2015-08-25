package com.michielvanderlee.jdbc;

public class Main
{

	public static void main(String[] args)
	{
		DBConnect dbConnect = new DBConnect(Database.POSTGRESQL);
		dbConnect.getData();

	}

}
