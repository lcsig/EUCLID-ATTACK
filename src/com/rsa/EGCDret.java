package com.rsa;

import java.math.BigInteger;

public class EGCDret {


    public static final BigInteger ZERO = BigInteger.ZERO;
    BigInteger q;
    BigInteger r;
    BigInteger s;
    BigInteger t;


    /** Init to zeros */
    EGCDret() {
        q = ZERO;
        r = ZERO;
        s = ZERO;
        t = ZERO;
    }


    public String toString() {
        String val = "";
        val += ("Q: " + q + "\n");
        val += ("R: " + r + "\n");
        val += ("S: " + s + "\n");
        val += ("T: " + t + "\n");

        return val;
    }
}
