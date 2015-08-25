package com.michielvanderlee.jpa.dao;

import javax.persistence.PostLoad;
import javax.persistence.PrePersist;
import javax.persistence.PreRemove;
import javax.persistence.PreUpdate;

import org.apache.openjpa.event.LifecycleEvent;
import org.apache.openjpa.event.LifecycleListener;

import com.michielvanderlee.jpa.sessionmanager.SessionContext;

public class QLifecycleListener implements LifecycleListener
{
	// ********************************************************************
	// Constructors
	// ********************************************************************
	public QLifecycleListener(final SessionContext ctx)
	{
		this.ctx = ctx;
	}

	// ********************************************************************
	// Methods
	// ********************************************************************
	
	// Validation Methods
	private void validateContext() throws IllegalStateException
	{
		if(this.ctx == null)
		{
			throw new IllegalStateException("Context was not initialized");
		}
	}
	
	private void validateReadCapability( Object o ) throws SecurityException
	{		
		if( o instanceof ICapableObject )
		{
			if( !this.ctx.getUser().getRole().hasCapability( ((ICapableObject) o).getReadCapability()) )
			{
				throw new SecurityException( String.format( "User does not have read capability for object: %s", 
						o.getClass().getSimpleName() ) );
			}
		}
	}

	private void validateWriteCapability( Object o ) throws SecurityException
	{	
		if( o instanceof ICapableObject )
		{
			if( !this.ctx.getUser().getRole().hasCapability( ((ICapableObject) o).getWriteCapability() ) )
			{
				throw new SecurityException( String.format( "User does not have write capability for object: %s", 
						o.getClass().getSimpleName() ) );
			}
		}
	}
	
	private void validateDeleteCapability( Object o ) throws SecurityException
	{		
		if( o instanceof ICapableObject )
		{
			if( !this.ctx.getUser().getRole().hasCapability( ((ICapableObject) o).getDeleteCapability() ) )
			{
				throw new SecurityException( String.format( "User does not have delete capability for object: %s", 
						o.getClass().getSimpleName() ) );
			}
		}
	}

	// Object life cycle event Methods
	@PostLoad
	public void onPostLoad( final Object o)
	{
		validateContext();
		validateReadCapability(o);
	}
	
	@PrePersist
	public void onPrePersist( final Object o )
	{
		validateContext();
		validateWriteCapability(o);
	}
	
	@PreUpdate
	public void onPreUpdate( final Object o )
	{
		validateContext();
		validateWriteCapability(o);
	}
	
	@PreRemove
	public void onPreDelete( final Object o )
	{
		validateContext();
		validateDeleteCapability(o);
	}
	
	
	// LifeCycleListener Methods
	@Override
	public void beforePersist(LifecycleEvent event)
	{
		onPrePersist(event.getSource());
	}

	@Override
	public void afterPersist(LifecycleEvent event)
	{
		// TODO Auto-generated method stub
	}

	@Override
	public void afterLoad(LifecycleEvent event)
	{
		onPostLoad(event.getSource());
	}

	@Override
	public void afterRefresh(LifecycleEvent event)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void beforeStore(LifecycleEvent event)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void afterStore(LifecycleEvent event)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void beforeClear(LifecycleEvent event)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void afterClear(LifecycleEvent event)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void beforeDelete(LifecycleEvent event)
	{
		onPreDelete(event.getSource());
	}

	@Override
	public void afterDelete(LifecycleEvent event)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void beforeDirty(LifecycleEvent event)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void afterDirty(LifecycleEvent event)
	{
		onPreUpdate(event.getSource());
	}

	@Override
	public void beforeDirtyFlushed(LifecycleEvent event)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void afterDirtyFlushed(LifecycleEvent event)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void beforeDetach(LifecycleEvent event)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void afterDetach(LifecycleEvent event)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void beforeAttach(LifecycleEvent event)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void afterAttach(LifecycleEvent event)
	{
		// TODO Auto-generated method stub

	}

	// ********************************************************************
	// getters and setters.
	// ********************************************************************

	// ********************************************************************
	// Properties
	// ********************************************************************
	private SessionContext ctx;
}
