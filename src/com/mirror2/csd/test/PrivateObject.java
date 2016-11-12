package com.mirror2.csd.test;

import java.lang.reflect.Field;

/**
 * Created with IntelliJ IDEA.
 * User: MAyatullah
 * Date: 10/13/14
 * Time: 12:41 PM
 * To change this template use File | Settings | File Templates.
 */
public class PrivateObject {
    private String privateString = null;
    private String privateString2 = "This is a value";


    public static void main(String[] args) {
           abc("privateString");
    }

    public static void abc(String s){
        try {
            PrivateObject privateObject = new PrivateObject();

            Field field = PrivateObject.class.getDeclaredField(s+"2");

            field.setAccessible(true);

            String value = (String) field.get(privateObject);

            System.out.println("value = " + value);
        } catch (Exception e) {

        }
    }


}
