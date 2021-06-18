package com.rsa;

import java.math.BigInteger;

public class Main {


    public static final BigInteger ZERO = BigInteger.ZERO;
    public static final BigInteger ONE = BigInteger.ONE;
    public static final BigInteger TWO = BigInteger.valueOf(2);
    public static final BigInteger FOUR = BigInteger.valueOf(4);


    public static void main(String[] args) {
        BigInteger n = new BigInteger("85080976323951696719635578579671062429");
        BigInteger e = new BigInteger("61100559406251463256709716070302151015");

        EuclidAttack.factor(n, e, true);
    }

}