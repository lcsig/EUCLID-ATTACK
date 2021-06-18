package com.rsa;

import java.awt.print.Printable;
import java.math.BigInteger;

public class EuclidAttackRet {


    public static final BigInteger ZERO = BigInteger.ZERO;
    public BigInteger n;        // The public key modulus
    public BigInteger e;        // The public key exponent
    public BigInteger a;        // (n + 1) mod e
    public BigInteger r;        // EGCD r(j) when r(j) is < a^(3/4)
    public BigInteger s;        // EGCD s(j) when r(j) is < a^(3/4)
    public BigInteger t;        // EGCD t(j) when r(j) is < a^(3/4)
    public BigInteger m;        // μ(j) = gcd(t(j), r(j))
    public BigInteger tbar;     // t'(j) = t(j)/μ(j)
    public BigInteger b1;       // (a + |t'(j)|^(−1)) mod e
    public BigInteger b2;       // (a + (e - |t'(j)|)^(−1)) mod e
    public BigInteger q;        // q = n / p
    public BigInteger p;        // p = n / q


    /** Init to zeros */
    EuclidAttackRet() {
        n = ZERO;
        e = ZERO;
        a = ZERO;
        q = ZERO;
        r = ZERO;
        s = ZERO;
        t = ZERO;
        p = ZERO;
        b1 = ZERO;
        b2 = ZERO;
        tbar = ZERO;
        m = ZERO;
    }


    public String toString() {
        String val = "";
        val += ("N: " + n + "\n");
        val += ("E: " + e + "\n");
        val += ("A: " + a + "\n");
        val += ("R: " + r + "\n");
        val += ("S: " + s + "\n");
        val += ("T: " + t + "\n");
        val += ("μ: " + m + "\n");
        val += ("t' " + tbar + "\n");
        val += ("B1 " + b1 + "\n");
        val += ("B2 " + b2 + "\n");
        val += ("Q: " + q + "\n");
        val += ("P: " + p);

        return val;
    }
}
