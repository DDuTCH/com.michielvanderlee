package com.michielvanderlee.jpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.michielvanderlee.jpa.dao.Address;
import com.michielvanderlee.jpa.dao.Person;

public class Main
{

	public static void main(String[] args)
	{
		EntityManagerFactory factory = null;
		EntityManager em = null;

		try
		{
			factory = Persistence.createEntityManagerFactory("openjpa_postgresql");
			em = factory.createEntityManager();
			em.getTransaction().begin();

			Address a = new Address();
			a.setCity("Fredericton");
			Person p = new Person("Peter", 65);
			p.setAddress(a);
			
			em.persist(p);
			
			em.getTransaction().commit();
		} catch (Exception e)
		{
			System.err.println(e);
			e.printStackTrace();
		} finally
		{
			if (em != null)
			{
				em.close();
			}
			if (factory != null)
			{
				factory.close();
			}
		}
	}

}
