package com.michielvanderlee.jpa;

import java.io.Serializable;

public class Pair<F, S> implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private F first = null;
	private S second = null;

	/**
	 * Constructor for the pair
	 */
	public Pair()
	{
	}

	/**
	 * Constructor for a new Pair, based on the contents of the supplied Pair.
	 */
	public Pair( Pair<F,S> copyFrom )
	{
		if ( copyFrom != null )
		{
			this.first = copyFrom.first;
			this.second = copyFrom.second;
		}
	}
	
	/**
	 * Constructor for the pair
	 * 
	 * @param first The first object in the pair
	 * @param second The second object in the pair
	 */
	public Pair(F first, S second)
	{
		this.first = first;
		this.second = second;
	}

	/**
	 * @return The first object in the pair
	 */
	public F getFirst()
	{
		return first;
	}

	/**
	 * @deprecated Use getFirst()
	 * @return The first object in the pair
	 */
	public F first()
	{
		return first;
	}

	/**
	 * @return The second object in the pair
	 */
	public S getSecond()
	{
		return second;
	}

	/**
	 * @deprecated Use getSecond()
	 * @return The second object in the pair
	 */
	public S second()
	{
		return second;
	}

	/**
	 * Sets the first value of the pair
	 * 
	 * @param first
	 */
	public void setFirst( F first )
	{
		this.first = first;
	}

	/**
	 * Sets the secod value of the pair
	 * 
	 * @param second
	 */
	public void setSecond( S second )
	{
		this.second = second;
	}

	@Override
	public int hashCode()
	{
		int code = 0;
		if ( first != null ) code += first.hashCode();

		if ( second != null ) code += second.hashCode();

		return code;
	}

	@Override
	public String toString()
	{
		return "PAIR(" + first + "," + second + ")";
	}

	@Override
	public boolean equals( Object obj )
	{
		if ( obj == null ) return false;
		if ( ! (obj instanceof Pair) ) return false;
		if ( obj == this ) return true;

		Pair<?,?> p = (Pair<?, ?>) obj;
		if ( first == null )
		{
			if ( second == null ) return p.first == null && p.second == null;
			else
				return p.first == null && second.equals( p.second );
		}
		else if ( second == null )
		{
			return p.second == null && first.equals( p.first );
		}
		else
		{
			return first.equals( p.first ) && second.equals( p.second );
		}
	}
}