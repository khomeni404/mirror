package com.mirror2.messaging.test;

/**
 * Copyright @ Soft Engine Inc.
 * Created on 03/09/15
 * Created By : Khomeni
 * Edited By : Khomeni &
 * Last Edited on : 03/09/15
 * Version : 1.0
 */

public class Factorial {

    public static void main(String[] arg) {
       // System.out.println(fact(18));
        bubbleSort();

    }

    public static long fact(long num) {
        if (num == 0)
            return 1;
        else {
            long x = num * fact(num - 1);
            //System.out.println( x);
            return x;
        }

    }

    public static void bubbleSort() {
        int[] array = {6, 5, 1, 7, 10, 85, 8, 4};
        printArr(array);

        for (int i = 0; i < array.length; i++) {
            for (int j = 1; j < (array.length-i); j++) {
                if (array[j-1] > array[j]) {
                   int temp = array[j-1];
                    array[j-1] = array[j];
                    array[j] = temp;
                }
            }
            printArr(array);
        }
/*
        for (int i = 0; i < n; i++) {
            for (int j = 1; j < (n - i); j++) {

                if (array[j - 1] > array[j]) {
                    temp = array[j - 1];
                    array[j - 1] = array[j];
                    array[j] = temp;
                }
                 printArr(array);
            }
        }*/
        //System.out.println("array.length = " + array.length);


    }

    public static void printArr(int[] array) {
        for (int num : array) {
            System.out.print(num + ", ");
        }
        System.out.println();
    }
}
