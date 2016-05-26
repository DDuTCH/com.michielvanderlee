package com.michielvanderlee.utils.properties;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.util.Properties;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class PropertiesUtils
{
	//////////////////////////////////////////////////////////////////////////////////
	//	Save methods
	//////////////////////////////////////////////////////////////////////////////////
	public static File saveToFile( Properties props, URI fileUri ) throws IOException
	{
		FileOutputStream fileOutput = null;
		try
		{
			File file = new File(fileUri);
			fileOutput = new FileOutputStream(file);
			
			props.store(fileOutput, null);
			
			return file;
		} finally 
		{
			if(fileOutput != null) { fileOutput.close(); }
		}
	}

	public static File saveToXmlFile( Properties props, URI fileUri ) throws IOException
	{
		FileOutputStream fileOutput = null;
		try
		{
			File file = new File(fileUri);
			fileOutput = new FileOutputStream(file);
			
			props.storeToXML(fileOutput, null);
			
			return file;
		} finally 
		{
			if(fileOutput != null) { fileOutput.close(); }
		}
	}
	
	public static File saveToJsonFile( Properties props, URI fileUri, boolean prettyPrint ) throws IOException
	{		FileWriter writer = null;		try		{			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			File file = new File(fileUri);			writer = new FileWriter(file);		writer.write(gson.toJson(props));		return file;
		} finally 
		{
			if(writer != null) { writer.close(); }
		}
	}
	
	//////////////////////////////////////////////////////////////////////////////////
	//	Load methods
	//////////////////////////////////////////////////////////////////////////////////
	public static Properties loadFromFile( URI fileUri ) throws IOException
	{
		FileInputStream fileInput = null;
		try 
		{
			File file = new File(fileUri);
			fileInput = new FileInputStream(file);
			
			Properties props = new Properties();
			props.load(fileInput);
			
			return props;
		} finally
		{
			if(fileInput != null) {fileInput.close(); }
		}
	}
	
	public static Properties loadFromXmlFile( URI fileUri ) throws IOException
	{
		FileInputStream fileInput = null;
		try 
		{
			File file = new File(fileUri);
			fileInput = new FileInputStream(file);
			
			Properties props = new Properties();
			props.loadFromXML(fileInput);
			
			return props;
		} finally
		{
			if(fileInput != null) {fileInput.close(); }
		}
	}
	
	public static Properties loadFromJsonFile( URI fileUri ) throws IOException
	{
		FileReader reader = null;
		try 
		{
			Gson gson = new Gson();
			File file = new File(fileUri);
			reader = new FileReader(file);
			
			Properties props = gson.fromJson(reader, Properties.class);
			
			return props;
		} finally
		{
			if(reader != null) {reader.close(); }
		}
	}
	
}
