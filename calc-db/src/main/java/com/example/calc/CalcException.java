package com.example.calc;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import org.slf4j.Logger;

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

	public static void logException(Logger logger, Exception e)
	{
		if ( null == logger || null == e )
			return;
		
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		e.printStackTrace(pw);
		logger.error( sw.toString() );
		pw.close();
		try{
			sw.close();
		} catch (IOException e1) {
		}
		
		
	}
}
