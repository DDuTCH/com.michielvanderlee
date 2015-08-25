package com.michielvanderlee.openmq.ch2;

import java.io.File;
import java.util.Enumeration;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Message;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.jms.QueueBrowser;
import javax.jms.Session;
import javax.jms.TextMessage;

public class Viewer
{

	public static void main(String[] args)
	{
		Connection conn = null;
		Session session = null;
		
		try
		{
			ConnectionFactory connFactory;
			Queue queue;
			
			connFactory = ConnectionFactoryLoader.createConnectionFactory(new File("imq.properties").toURI());
			conn = connFactory.createConnection();
			
			session = conn.createSession(false, Session.AUTO_ACKNOWLEDGE);
			queue = new com.sun.messaging.Queue("world");
			
			QueueBrowser browser = session.createBrowser(queue);
			Enumeration<?> e = browser.getEnumeration();
			int count = 0;
			while(e.hasMoreElements())
			{
				Message m = (Message)e.nextElement();
				System.out.print(m.getClass().getSimpleName() + " = ");
				if(m instanceof TextMessage) 
				{
					System.out.print(((TextMessage)m).getText());
				} 
				else if(m instanceof ObjectMessage)
				{
					System.out.print(((ObjectMessage)m).getObject().getClass().getSimpleName());
				}
				System.out.print("\n");
				count++;
			}
			System.out.println("Number of messages: " + count);
		} catch (Exception e)
		{
			System.err.println("Error occured: " + e.toString());
			e.printStackTrace();
		} finally 
		{
			try {
				if(session != null) { session.close(); }
			} catch( Exception e ) {
				System.err.println("Error occured while closing session");
			}
			try {
				if(conn != null) { conn.close(); }
			} catch( Exception e ) {
				System.err.println("Error occured while closing connection");
			}
		}
	}
}
