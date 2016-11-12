package com.mirror2.messaging.test;

/**
 * Copyright @ Soft Engine Inc.
 * Created on 04/09/15
 * Created By : Khomeni
 * Edited By : Khomeni &
 * Last Edited on : 04/09/15
 * Version : 1.0
 */

public class TowersOfHanoi {
    public static void main(String[] args) {
        int nDisks = 4;
        doTowers(nDisks, 'A', 'B', 'C');
    }

    public static void doTowers(int topN, char from, char inter, char to) {
        if (topN == 1){
            System.out.println("Disk 1 from " + from + " to " + to);
        }else {
            doTowers(topN - 1, from, to, inter);
            System.out.println("Disk " + topN + " from " + from + " to " + to);
            doTowers(topN - 1, inter, from, to);
        }
    }
}
