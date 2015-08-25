package com.michielvanderlee.openmq.ch2;

import java.net.URI;
import java.util.Properties;

import javax.jms.ConnectionFactory;

import com.michielvanderlee.utils.properties.PropertiesUtils;

public class ConnectionFactoryLoader
{

	public static ConnectionFactory createConnectionFactory()
	{
		return new com.sun.messaging.ConnectionFactory();
	}
	
	public static ConnectionFactory createConnectionFactory( URI configPropertiesFileUri ) throws Exception
	{
		com.sun.messaging.ConnectionFactory connectionFactory = new com.sun.messaging.ConnectionFactory();
		loadConfigFromFile(connectionFactory, configPropertiesFileUri);
		
		return connectionFactory;
	}
	
	
	private static void loadConfigFromFile( com.sun.messaging.ConnectionFactory factory, URI configFileUri) throws Exception
	{
		Properties props = PropertiesUtils.loadFromFile(configFileUri);
		
		for(Object key : props.keySet())
		{
			factory.setProperty( String.valueOf(key), String.valueOf(props.get(key)) );
		}
	}
	
}
