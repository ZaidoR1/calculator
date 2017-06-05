package com.example.calc;

import com.udojava.evalex.Expression;

public class CalcWorker {

  /**
   * Calculates a result, based on the operation string entry. Operation may be something like
   * "3+5*7+17" or "SQRT(9)" or "100.678/2^3" or "(3+5)*2" or whatever
   *
   * @param operation
   * @return double the result
   * @throws Exception if e.g. invalid operation
   * @see <a href=
   *      "https://github.com/uklimaschewski/EvalEx">https://github.com/uklimaschewski/EvalEx</a>
   */
  static double calculate(String operation) throws Exception {
    return new Expression(operation).eval().doubleValue();
  }
}
