package com.rsa;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;


public class EuclidAttackAnalysis {
    /** Ignore this class ... For test purposes */

    /**
     * 1. Examining EGCD indices ---> DONE
     * 2. Examining try and error (a + 1 + i^-1) mod e ---> Done
     * 3. Examining the difference between the calculated p + q and the real sum ---> Done
     * 4. Examining the direct solution of (i, j) based on the summation of p,q in [p+q =  (a + 1 + i^-1) mod j]
     */
    public static final BigInteger ZERO = BigInteger.ZERO;
    public static final BigInteger ONE = BigInteger.ONE;
    public static final BigInteger TWO = BigInteger.valueOf(2);
    public static final BigInteger FOUR = BigInteger.valueOf(4);


    /**
     * Examining try and error (a + 1 + i^-1) mod e
     */
    public static void main1_searchingRandomly() {
        BigInteger n = new BigInteger("85080976323951696719635578579671062429");
        BigInteger e = new BigInteger("61100559406251463251709716070302151015");
        int limit1 = 200000;
        int limit2 = 10186820;

        br:
        for (int i = 0; i < limit1; i++) {
            e = e.add(ONE);
            System.out.println("@"+ i);
            BigInteger a = n.add(ONE).mod(e);

            for (int j = 2; j < limit2; j++) {

                BigInteger[] sol1 = new BigInteger[] {ZERO, ZERO};
                try {
                    BigInteger b1 = a.add(BigInteger.valueOf(j).modInverse(e)).mod(e);
                    sol1 = QuadEqn.solve(ONE, ZERO.subtract(b1), n);
                } catch (Exception exp) {}


                BigInteger[] sol2 = new BigInteger[] {ZERO, ZERO};
                try {
                    BigInteger b2 = a.add(e.subtract(BigInteger.valueOf(j)).modInverse(e)).mod(e);
                    sol2 = QuadEqn.solve(ONE, ZERO.subtract(b2), n);
                } catch (Exception exp) {}


                BigInteger[] sol;
                if (sol1[0].multiply(sol1[1]).equals(n)) {
                    System.out.println(i);
                    System.out.println(j);
                    break br;
                }
                else if (sol2[0].multiply(sol2[1]).equals(n)) {
                    System.out.println(i);
                    System.out.println(j);
                    break br;
                }
            }
        }
    }


    /**
     * Examining the difference between the calculated p + q and the real sum
     */
    public static void main2_DifferenceOfValues() {
        BigInteger n = new BigInteger("85080976323951696719635578579671062429");
        BigInteger e = new BigInteger("61100559406251463256709716070302151014");
        BigInteger sum = new BigInteger("18447869973616395454");
        int limit1 = 200000;

        br:
        for (int i = 100; i < limit1; i++) {
            e = e.add(ONE);
            System.out.println("@"+ i);
            BigInteger a = n.add(ONE).mod(e);

            BigInteger b1 = ZERO;
            BigInteger b2 = ZERO;
            for (int j = 2; j < 10186820; j++) {
                try {
                    b1 = a.add(BigInteger.valueOf(j).modInverse(e)).mod(e);
                    System.out.println(sum.subtract(b1));
                } catch (Exception x) {}
                try {
                    b2 = a.add(e.subtract(BigInteger.valueOf(j)).modInverse(e)).mod(e);
                    System.out.println(sum.subtract(b2));
                } catch (Exception x) {}
            }
        }
    }


    /**
     * Examining the direct solution of (x, y) based on the summation of p,q
     *      in [p+q =  (a + 1 + x^-1) mod y]
     */
    public static void main3_answerBasedOnPublicExponent() {
        BigInteger n = new BigInteger("85080976323951696719635578579671062429");
        BigInteger p = new BigInteger("9223372036854777017");
        BigInteger q = new BigInteger("9224497936761618437");
        BigInteger e =  new BigInteger("61100559406251463256709716070302151015"); // p.add(q).add(ONE);


        for (int i = 0; i < 100; i++) {
            e = e.add(ONE);
            BigInteger a = n.add(ONE).mod(e);

            BigInteger x = p.add(q).subtract(a);
            while (x.compareTo(ZERO) < 0)
                x = x.add(e);
            if (!x.gcd(e).equals(ONE))
                continue;

            BigInteger tBar = x.modInverse(e);
            if (a.add(tBar.modInverse(e)).mod(e).equals(p.add(q))) {
                System.out.println("e: \t" + e +
                        "\t\t ----- Diff: \t" + x +
                        "\t\t ----- t'(1): \t" + tBar +
                        "\t\t ----- t'(2): \t" + e.subtract(tBar));
            }
        }
    }

}
