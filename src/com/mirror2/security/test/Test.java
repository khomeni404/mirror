package com.mirror2.security.test;

/**
 * Copyright &copy; Soft Engine Inc.
 * Created on 10/03/16
 * Created By : Khomeni
 * Edited By : Khomeni &
 * Last Edited on : 10/03/16
 * Version : 1.0
 */

public class Test {
    public static int p = 7;
    public static int q = 19;
    public static int fyN = 108;
    public static int e = 5;

    public static void main(String[] args) {
        //getEncryptinKey(fyN);
        // System.out.println(108%5);
       // getDecryptionKey();
        //System.out.println(new Double(5/69).toString());
        new Test().go();
    }

    void go() {
        System.out.println("45/48 = " + 45 / 48);
    }
    public static void getEncryptinKey(int fyN) {
        for (int i = 2; i < fyN; i++) {
            System.out.println("e  = " + fyN%i);
        }
    }

    public static void getDecryptionKey() {
        for(int i = 0; i<100; i++) {
            double d = (1 + (i * fyN)) / e;
            System.out.println("d = " + Double.valueOf(4/5));
        }
    }

}
