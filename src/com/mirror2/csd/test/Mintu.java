package com.mirror2.csd.test;

/**
 * Created with IntelliJ IDEA.
 * User: MAyatullah
 * Date: 9/21/14
 * Time: 5:29 PM
 * To change this template use File | Settings | File Templates.
 */
public class Mintu {


    public static void main(String[] args) {
        String s = "";

        String s1 = s.replaceAll("\n", "");

        String[] s2 = s1.split(",");
        int length = s2.length;
        //public String replaceAll(String regex, String replacement)
        System.out.println("Total No. = "+length);
        for(int i = 0; i<length; i++){
            System.out.println("88"+s2[i]);
        }

    }
}
