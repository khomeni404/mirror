package com.mirror2.csd.util;

public class EnumDemo {

    public static void main(String args[]) {
        String months[] = {"January" , "February" , "March" , "April", "May",
                "June", "July", "August", "September", "October",
                "November", "December"};
        //System.out.println(Array.asList(array).indexOf(1); );
        for (int i=0; i< 12; i++ ) {
            if (months[i].equals("May")) {
                System.out.println("index "+i);
            }
        }
        System.out.println("CellPhone List:");
        for(Months m : Months.values()) {
            System.out.println(m + " costs " + m.showPrice() + " dollars");
        }

        Months ret;
        ret = Months.valueOf("Samsung");
        System.out.println("Selected : " + ret);

        System.out.println(Months.Motorola.value);
        System.out.println();
    }
}
