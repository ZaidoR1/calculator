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
	 * @return entry with calculated result
	 * @throws Exception
	 * 
	 */
	static CalcEntry getResult( CalcEntry entry ) throws Exception 
	{
		double result = 0.0;
		entry.setResult( 0.0 );
		
		if ( entry == null || entry.getOperation() == null || entry.getOperation().length() < 1 )
			throw new Exception( "Operation not valid" );
		
		try	{
			result = new Expression( entry.getOperation()).eval().doubleValue();
		} catch( ExpressionException e)	{
			throw new Exception( "Operation not valid" );
		} catch (Exception e)	{
			throw new Exception();
		}
		entry.setResult( result );
		
		return entry;
	}
}
