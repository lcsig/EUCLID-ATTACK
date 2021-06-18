package com.rsa;

import java.math.BigInteger;

/**
 * This class contains operations on the quadratic equation of the form
 *      ax^2 + bx + c = 0
 */
public class QuadEqn {


    public static final BigInteger ZERO = BigInteger.ZERO;
    public static final BigInteger ONE = BigInteger.ONE;
    public static final BigInteger TWO = BigInteger.valueOf(2);
    public static final BigInteger FOUR = BigInteger.valueOf(4);


    /**
     * A method to give the solution to the quadratic equation
     *      Sol = (-b +- sqrt(b^2 - 4ac)) / 2a
     * @param a Coefficient of X^2  [ax^2 + bx + c = 0]
     * @param b Coefficient of X    [ax^2 + bx + c = 0]
     * @param c Constant c          [ax^2 + bx + c = 0]
     * @return  Array of BigInteger that contains the two solutions.
     */
    public static BigInteger[] solve(BigInteger a, BigInteger b, BigInteger c) {

        try {
            BigInteger firstSol, secondSol;

            BigInteger sqrt = b.pow(2).subtract(FOUR.multiply(a).multiply(c)).sqrt();   // Calc sqrt(b^2 - 4ac)
            BigInteger minusB = ZERO.subtract(b);                                       // Calc -b
            BigInteger twoA = TWO.multiply(a);                                          // Calc 2a

            firstSol = minusB.add(sqrt).divide(twoA);         // Sol1 = (-b + sqrt(b^2 - 4ac)) / 2a
            secondSol = minusB.subtract(sqrt).divide(twoA);   // Sol2 = (-b + sqrt(b^2 - 4ac)) / 2a

            BigInteger[] ret = {firstSol, secondSol};
            return ret;

        } catch(Exception e) {
            BigInteger[] k = {ZERO, ZERO};
            return k;
        }
    }

}
