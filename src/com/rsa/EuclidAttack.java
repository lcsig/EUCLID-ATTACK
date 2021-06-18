package com.rsa;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 * An implementation of EuclidAttack.
 *  Which is An application of Euclidean algorithm in cryptanalysis of RSA.
 *  It computes the factorization of n in deterministic time O((log n)^2) bit operations.
 *      In the case where the public exponent e has the same order of magnitude as n
 *      and one of the integers k = (ed − 1)/phi(n) and e − k has at most one-quarter as many bits as e.
 *
 * @version 1.00 16 Jun 2021
 * @author Mahmoud Almazari
 */
public class EuclidAttack {
    public static final BigInteger ZERO = BigInteger.ZERO;
    public static final BigInteger ONE = BigInteger.ONE;
    public static final BigInteger TWO = BigInteger.valueOf(2);
    public static final BigInteger FOUR = BigInteger.valueOf(4);


    /**
     * A function to perform factorization of n based on the Euclid Attack
     * @param n The public key modulus.
     * @param e The public key exponent.
     * @param echo True to verbose, False to just return the values without any info.
     * @return EuclidAttackRet class which contains all calculations and p,q where n = p * q.
     */
    public static EuclidAttackRet factor(BigInteger n, BigInteger e, boolean echo) {

        // Step 1 ---> [a = (n + 1) mod e]
        BigInteger a = n.add(ONE).mod(e);


        // Step 2 ---> Using the extended Euclidean algorithm for e and a, compute the biggest remainder
        //      r(j) among them which are < e^3/4 and the associated integers s(j) , t(j) such that
        //      s(j)*e + a*t(j) = r(j) .
        EGCDret egcd_ret = modifiedExtendedGCD(e, a);
        BigInteger r = egcd_ret.r;
        BigInteger s = egcd_ret.s;
        BigInteger t = egcd_ret.t;


        // Step 3 ---> μ(j) = gcd(t(j), r(j)) ---> t'(j) = t(j)/μ(j)
        BigInteger m = t.gcd(r);
        BigInteger tBar = t.divide(m);


        // Step 4 ---> B1 = (a + |t'(j)|^(−1)) mod e
        BigInteger b1 = a.add(tBar.modInverse(e)).mod(e);
        BigInteger[] sol1 = QuadEqn.solve(ONE, ZERO.subtract(b1), n);


        // Step 5 ---> B2 = (a + (e - |t'(j)|)^(−1)) mod e
        BigInteger b2 = a.add(e.subtract(tBar).modInverse(e)).mod(e);
        BigInteger[] sol2 = QuadEqn.solve(ONE, ZERO.subtract(b2), n);


        BigInteger[] sol;
        if (sol1[0].multiply(sol1[1]).equals(n))
            sol = sol1;
        else if (sol2[0].multiply(sol2[1]).equals(n))
            sol = sol2;
        else
            sol = new BigInteger[] {ZERO, ZERO};


        EuclidAttackRet ret = new EuclidAttackRet();
        ret.n = n;
        ret.e = e;
        ret.a = a;
        ret.r = r;
        ret.s = s;
        ret.t = t;
        ret.m = m;
        ret.tbar = tBar;
        ret.b1 = b1;
        ret.b2 = b2;
        ret.p = sol[0];
        ret.q = sol[1];

        if (echo)
            print(ret);

        return ret;
    }


    /**
     * A function to print the fields of EuclidAttackRet class
     * @param retVal The required class to print its fields
     */
    public static void print(EuclidAttackRet retVal) {
        System.out.println(retVal.toString());

        BigInteger phi = retVal.p.subtract(ONE).multiply(retVal.q.subtract(ONE));
        BigInteger d = retVal.e.modInverse(phi);
        BigInteger k = retVal.e.multiply(d).subtract(ONE).divide(phi);

        System.out.println("PH " + phi);
        System.out.println("d: " + d);
        System.out.println("k: " + k);
    }


    /**
     * Extended Greater Common Divisor algorithm to return certain elements of the EGCD table
     *      For Euclid Attack:
     *          a will be substituted as the public key exponent (e). b will be substituted as [(n + 1) mod e]
     *
     * @param a Some integer a, it will used in the comparison. The function will return when r(j) < a^(3/4)
     * @param b Some integer b
     * @return Returns {r(j), s(j), r(j)} in EGCDret class, where r(j) is < a^(3/4)
     */
    private static EGCDret modifiedExtendedGCD(BigInteger a, BigInteger b) {
        List<BigInteger> q = new ArrayList<>();
        List<BigInteger> r = new ArrayList<>();
        List<BigInteger> s = new ArrayList<>();
        List<BigInteger> t = new ArrayList<>();

        // Initialization
        q.add(ZERO); q.add(ZERO);
        r.add(a); r.add(b);
        s.add(ONE); s.add(ZERO);
        t.add(ZERO); t.add(ONE);

        // Calculate a^(3/4)
        BigInteger cmp = a.sqrt().sqrt().pow(3);

        while(!r.get(r.size() - 1).equals(ZERO)) {
            // Calc Q(i+1)
            q.add(r.get(r.size() - 2).divide(r.get(r.size() - 1)));

            // Calc R(i+1)
            r.add(r.get(r.size() - 2).subtract(q.get(q.size() - 1).multiply(r.get(r.size() - 1))));

            // Calc S(i+1)
            s.add(s.get(s.size() - 2).subtract(q.get(q.size() - 1).multiply(s.get(s.size() - 1))));

            // Calc T(i+1)
            t.add(t.get(t.size() - 2).subtract(q.get(q.size() - 1).multiply(t.get(t.size() - 1))));


            if (r.get(r.size() - 1).compareTo(cmp) < 0) {
                EGCDret ret = new EGCDret();
                ret.q = q.get(q.size() - 1);
                ret.r = r.get(r.size() - 1);
                ret.s = s.get(s.size() - 1);
                ret.t = t.get(t.size() - 1);

                return ret;
            }
        }

        return new EGCDret();
    }
}
