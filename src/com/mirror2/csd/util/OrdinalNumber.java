package com.mirror2.csd.util;

public class OrdinalNumber {
    public static void main(String[] args) {
        for(int i = 1; i<100; i++){
            //System.out.print(i + getOrdinalNumber(i) + "\t");
            if(i%10 == 0){
                System.out.println();
            }
        }
    }


    public static String getOrdinalNumber(int number) {
        int last = number % 10;
        String suffix = "";
        if (number == 11 || number == 12 || number == 13) {
            suffix = "th";
        } else {
            switch (last) {
                case 1:
                    suffix = "st";
                    break;
                case 2:
                    suffix = "nd";
                    break;
                case 3:
                    suffix = "rd";
                    break;
                default:
                    suffix = "th";
                    break;
            }
        }
        return suffix;
    }
}
