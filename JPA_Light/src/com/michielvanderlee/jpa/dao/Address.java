package com.michielvanderlee.jpa.dao;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Address
{
	public Address()
	{
		
	}
	
	public long getId()
	{
		return id;
	}
	public void setId( long id )
	{
		this.id = id;
	}
	public String getLine1()
	{
		return line1;
	}
	public void setLine1(String line1)
	{
		this.line1 = line1;
	}
	public String getLine2()
	{
		return line2;
	}
	public void setLine2(String line2)
	{
		this.line2 = line2;
	}
	public String getCity()
	{
		return city;
	}
	public void setCity(String city)
	{
		this.city = city;
	}
	public String getProvince()
	{
		return province;
	}
	public void setProvince(String province)
	{
		this.province = province;
	}
	public String getPostalCode()
	{
		return postalCode;
	}
	public void setPostalCode(String postalCode)
	{
		this.postalCode = postalCode;
	}
	public String getCountry()
	{
		return country;
	}
	public void setCountry(String country)
	{
		this.country = country;
	}

	@Id
	private long id;
	private String line1;
	private String line2;
	private String city;
	private String province;
	private String postalCode;
	private String country;
}
