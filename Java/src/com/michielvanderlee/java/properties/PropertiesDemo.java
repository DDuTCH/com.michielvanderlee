package com.michielvanderlee.java.properties;

import java.io.File;
import java.io.IOException;
import java.util.Properties;

import com.michielvanderlee.utils.properties.PropertiesUtils;

/**
 * This class is a simple demo of how to use properties. It utilizes the PropertiesUtils to handle the property persistence. 
 * 
 * Properties are great for when you want to ship a properties file with an applications.
 * 
 * @author MichielV
 */
public class PropertiesDemo
{

	public static void main(String[] args)
	{
		Properties props = new Properties();
		Properties props2 = null;
		props.put("Michiel", "Vanderlee");
		props.put("Sarah-Lyne", "Vanderlee");
		
		File file = new File("demo.properties");
		File xmlFile = new File("demo.xml");
		File jsonFile = new File("demo.json");
		try
		{
			PropertiesUtils.saveToFile(props, file.toURI());
			PropertiesUtils.saveToXmlFile(props, xmlFile.toURI());
			PropertiesUtils.saveToJsonFile(props, jsonFile.toURI(), true);
			
			props2 = PropertiesUtils.loadFromFile(file.toURI());	
			System.out.println(props2.toString());
			props2 = PropertiesUtils.loadFromXmlFile(xmlFile.toURI());	
			System.out.println(props2.toString());
			props2 = PropertiesUtils.loadFromJsonFile(jsonFile.toURI());	
			System.out.println(props2.toString());
		} catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	
}
