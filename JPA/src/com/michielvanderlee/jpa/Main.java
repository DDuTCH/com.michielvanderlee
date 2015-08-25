package com.michielvanderlee.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.michielvanderlee.jpa.dao.Person;

public class Main
{

	@SuppressWarnings("unchecked")
	public static void main(String[] args)
	{
		PersistenceUnits unit = PersistenceUnits.OPENJPA_POSTGRESQL;
		
		EntityManagerFactory factory = null;
		EntityManager em = null;
		
		try 
		{
			factory = Persistence.createEntityManagerFactory(unit.getName());
			em = factory.createEntityManager();
			em.getTransaction().begin();
			
			Query q;
			List<Person> daoList;
			
			q = em.createNativeQuery("SELECT * FROM persons p", Person.class);
			daoList = q.getResultList();
			System.out.println(daoList);

			
			em.getTransaction().commit();
		} 
		catch( Exception e )
		{
			em.getTransaction().rollback();
			
			System.out.println("printStackTrace()");
			e.printStackTrace();
			
			System.out.println(e.getClass());
			System.out.println(e.getCause().getClass());
			System.out.println(e.getCause().getCause().getClass());
		} 
		finally
		{
			if( em != null ) {
				em.close();
			}
			if( factory != null ) {
				factory.close();
			}
		}
	}

}
