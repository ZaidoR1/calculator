package com.example.calc;

import com.udojava.evalex.Expression;
import com.udojava.evalex.Expression.ExpressionException;

public class CalcWorker
{

	/**
	 * Calculates a result, based on the operation string entry.operation
	 * 
	 * Operation may be something like "3+5*7+17" or "SQRT(9)" or "100.678/2^3" or "(3+5)*2"
	 * (see {@linkplain}https://github.com/uklimaschewski/EvalEx )
	 * 
	 * @param entry
	 * @return calculated result
	 * @throws CalcException
	 * 
	 */
	static CalcEntry getResult( CalcEntry entry ) throws CalcException 
	{
		double result = 0.0;
		entry.setResult( 0.0 );
		
		if ( entry == null || entry.getOperation() == null || entry.getOperation().length() < 1 )
			throw new CalcException( "Operation not valid" );
		
		try
		{
			result = new Expression( entry.getOperation()).eval().doubleValue();
		}
		catch( ExpressionException e)
		{
			throw new CalcException( "Operation not valid" );
		}
		catch (Exception e)
		{
			throw new CalcException();
		}
		entry.setResult( result );
		
		return entry;
	}
}
