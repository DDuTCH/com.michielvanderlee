package com.michielvanderlee.openmq.ch2;

import java.io.File;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;

public class Consumer
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
			
			MessageConsumer msgConsumer = session.createConsumer(queue);
			conn.start();
			
			Message msg = msgConsumer.receive();
			if (msg instanceof TextMessage) {
                TextMessage txtMsg = (TextMessage) msg;
                System.out.println("Read Message: " + txtMsg.getText());
            }
			
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
