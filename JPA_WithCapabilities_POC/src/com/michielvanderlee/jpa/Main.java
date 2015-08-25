package com.michielvanderlee.jpa;



import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.apache.openjpa.persistence.OpenJPAEntityManagerSPI;

import com.google.gson.Gson;
import com.michielvanderlee.jpa.dao.Person;
import com.michielvanderlee.jpa.dao.QLifecycleListener;
import com.michielvanderlee.jpa.sessionmanager.SessionContext;
import com.michielvanderlee.jpa.usermanager.User;
import com.michielvanderlee.jpa.usermanager.UserRole;

public class Main
{
	public static SessionContext initSessionContext()
	{
		UserRole userRole = new UserRole("admin");
		userRole.addCapability((new Person()).getReadCapability(), true);
		userRole.addCapability((new Person()).getWriteCapability(), false);
		userRole.addCapability((new Person()).getDeleteCapability(), false);
	
		User user = new User();
		user.setRole(userRole);
		
		SessionContext ctx = new SessionContext(user);
		
		return ctx;
	}
	
	
	@SuppressWarnings("unchecked")
	public static void main(String[] args)
	{
		EntityManagerFactory factory = null;
		EntityManager em = null;
		
		try 
		{
			SessionContext ctx = initSessionContext();
			factory = Persistence.createEntityManagerFactory("openjpa_postgresql");
			em = factory.createEntityManager();
			
			final OpenJPAEntityManagerSPI ojpa = (OpenJPAEntityManagerSPI) em;
			QLifecycleListener listener = new QLifecycleListener(ctx);
			ojpa.addLifecycleListener(listener, (Class[]) null);
			
			em.getTransaction().begin();
						
			//List<Person> list = em.createQuery("SELECT o FROM " + Person.class.getSimpleName() + " o", Person.class).getResultList(); 
			List<Person> list = em.createNativeQuery("SELECT * FROM persons", Person.class).getResultList();
			
			Gson gson = new Gson();
			System.out.println(gson.toJson(list));
			
			// Create a person an persist
			Person p = new Person("Dave", 30);
			em.persist(p);
			
			System.out.println(p);
			
			// Find a person and update
			Person Dave = em.find(Person.class, p.getId());
			Dave.setAge(25);
			em.merge(Dave);
			
			em.getTransaction().commit();
			
			System.out.println(p);
		} 
		catch( Exception e )
		{
			System.err.println(e);
			e.printStackTrace();
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
