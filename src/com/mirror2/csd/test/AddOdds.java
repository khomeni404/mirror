package com.mirror2.csd.test;

public class AddOdds {
    public static void main(String[] args) {
        int x = 100;
        int answer = 0;
        for(int i = 0; i<=x; i++){
            answer += i;
//            if(i%2 != 0){
//                 answer += i;
//            }
        }
        System.out.println("answer= " + answer);
    }
}
