package com.example.calc;

public class CalcException extends IllegalArgumentException
{
	private static final long serialVersionUID = 5114646972588711295L;

	public CalcException()
	{
		super();
	}

	public CalcException( String arg0, Throwable arg1 )
	{
		super( arg0, arg1 );
	}

	public CalcException( String arg0 )
	{
		super( arg0 );
	}

	public CalcException( Throwable arg0 )
	{
		super( arg0 );
	}

}
