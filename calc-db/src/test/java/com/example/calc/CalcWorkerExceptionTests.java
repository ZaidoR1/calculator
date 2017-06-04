package com.example.calc;

// siehe http://www.vogella.com/tutorials/JUnit/article.html#usingjuni4

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class CalcWorkerExceptionTests {

  // static double calculate(String operation) throws Exception {
  // return new Expression(operation).eval().doubleValue();
  // }

  // Test fails, if no exceptions are thrown
  @Test(expected = Exception.class)
  public final void testCalculateExceptions1() throws Exception {
    final String op19 = "2+(3*5";
    assertEquals("Operation: " + op19, 17.0, CalcWorker.calculate(op19), 0.0);

    // Es muss mind. 1 Exception kommen, damit der Test erfolgreich ist
    // ==> jeder Funktionsaufruf, der eine Exception machen soll, muss ein eigener Test werden.

  }

  @Test(expected = Exception.class)
  public final void testCalculateExceptions2() throws Exception {
    final String op = "2+3*5)";
    assertEquals("Operation: " + op, 17.0, CalcWorker.calculate(op), 0.0);
  }

  @Test(expected = Exception.class)
  public final void testCalculateExceptions3() throws Exception {
    final String op = "1/0";
    assertEquals("Operation: " + op, 0.0, CalcWorker.calculate(op), 0.0);
  }
}
