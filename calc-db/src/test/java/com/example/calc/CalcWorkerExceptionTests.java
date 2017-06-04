package com.example.calc;

// siehe http://www.vogella.com/tutorials/JUnit/article.html#usingjuni4

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class CalcWorkerExceptionTests {
  @Parameter(0)
  public String operation;
  @Parameter(1)
  public double result;
  @Parameter(2)
  public double delta;

  // creates the test data
  // Array contains: Operation, expected result, accepted delta
  @Parameters
  public static Collection<Object[]> data() {
    Object[][] data = new Object[][] {{"2+3*5)", 17.0, 0.0}, {"(2+3*5", 17.0, 0.0},
        {"(2+3*5", 17.0, 0.0}, {"-2(5+5)", -10.0, 0.0}, {"1/0", 0.0, 0.0}};
    return Arrays.asList(data);
  }


  // Test fails, if no exceptions are thrown
  @Test(expected = Exception.class)
  public void testCalculate() throws Exception {
    Calc tester = new Calc();
    assertEquals("Operation: " + operation, result, tester.calc(operation), delta);
  }


  class Calc {
    public double calc(String operation) throws Exception {
      return CalcWorker.calculate(operation);
    }
  }
}
