package com.rsa;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class PFun {

    public static final BigInteger ZERO = BigInteger.ZERO;
    public static final BigInteger ONE = BigInteger.ONE;
    public static final BigInteger TWO = BigInteger.valueOf(2);
    public static final BigInteger FOUR = BigInteger.valueOf(4);

    /**
     * Return the next prime starting from (number + 1)
     * @param number the number to get the prime after
     * @return the next prime
     */
    public static BigInteger getTheNextPrime(BigInteger number) {

        if (number.mod(TWO).equals(ZERO))
            number = number.add(ONE);

        // Increment and test
        while(!number.isProbablePrime(500)) {
            number = number.add(TWO);
        }

        return number;
    }

    /**
     * @OverLoad of getTheNextPrime(BigInteger number)
     * @param bitRepresentationOfPrime The bit representation of some integer
     * @return the next prime starting from (bitRepresentationOfPrime Value + 1)
     */
    public static BigInteger getTheNextPrime(String bitRepresentationOfPrime) {

        BigInteger number = new BigInteger(bitRepresentationOfPrime, 2);
        return getTheNextPrime(number);
    }


    /**
     * An implementation of EGCD to print the EGCD table
     * @param a r(0)
     * @param b r(1)
     * @return The last raw in the EGCD table
     */
    public static EGCDret printExtendedGCD(BigInteger a, BigInteger b) {
        List<BigInteger> q = new ArrayList<BigInteger>();
        List<BigInteger> r = new ArrayList<BigInteger>();
        List<BigInteger> s = new ArrayList<BigInteger>();
        List<BigInteger> t = new ArrayList<BigInteger>();


        // Initialization
        EGCDret ret = new EGCDret();
        q.add(ZERO); q.add(ZERO);
        r.add(a); r.add(b);
        s.add(ONE); s.add(ZERO);
        t.add(ZERO); t.add(ONE);


        while(!r.get(r.size() - 1).equals(ZERO)) {
            // Calc Q(i+1)
            q.add(r.get(r.size() - 2).divide(r.get(r.size() - 1)));

            // Calc R(i+1)
            r.add(r.get(r.size() - 2).subtract(q.get(q.size() - 1).multiply(r.get(r.size() - 1))));

            // Calc S(i+1)
            s.add(s.get(s.size() - 2).subtract(q.get(q.size() - 1).multiply(s.get(s.size() - 1))));

            // Calc T(i+1)
            t.add(t.get(t.size() - 2).subtract(q.get(q.size() - 1).multiply(t.get(t.size() - 1))));

            ret.q = q.get(q.size() - 1);
            ret.r = r.get(r.size() - 1);
            ret.s = s.get(s.size() - 1);
            ret.t = t.get(t.size() - 1);
            System.out.println(ret.q + " --- " + ret.r + " --- " + ret.s + " --- " + ret.t);
        }

        return ret;
    }

}
