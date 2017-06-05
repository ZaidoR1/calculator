package com.example.calc;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class CalcWorkerTest {

  @Parameter(0)
  public String operation;
  @Parameter(1)
  public double result;
  @Parameter(2)
  public double delta;

  // creates the test data
  // Array contains: Operation, expected result, accepted delta
  @Parameters(name = "{0} = {1}")
  public static Collection<Object[]> data() {
    final Object[][] data = new Object[][] {
        {"3+2", 5.0, 0.0}, {"3-2", 1.0, 0.0},
        {"2-3", -1.0, 0.0}, {"2+3+4-7", 2.0, 0.0},
        {"2*3+5", 2 * 3 + 5, 0.0}, {"2*3+2*5", 2 * 3 + 2 * 5, 0.0},
        {"2*3.5-7", 2 * 3.5 - 7, 0.0}, {"2+(3*5)", 2 + (3 * 5), 0.0},
        {"-5*(2+3)", -5 * (2 + 3), 0.0}, {"10/2", 5.0, 0.0},
        {"10/3", 3.33333, 0.00001}, {"10/2.5", 4.0, 0.0},
        {"10/-5", -2.0, 0.0}, {"-10/2+2", -3.0, 0.0},
        {"-10/(2+2)", -2.5, 0.0}, {"sin(90)", 1.0, 0.0},
        {"sin(90)*cos(90)", 0.0, 0.01}, {"1", 1.0, 0.0}};
    return Arrays.asList(data);
  }

  @Test
  public void testCalculate() throws Exception {
    assertEquals("Operation: " + operation, result, CalcWorker.calculate(operation), delta);
  }
}
