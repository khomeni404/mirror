package com.mirror2.util;

import java.util.Date;

public class Licencing {
    TheDates td = new TheDates();
    public static void main(String[] args) {

    }
    private final Date EXPIRY_DATE = td.toDate("2014-11-30");
    public boolean isLicenceOk(){
        if(EXPIRY_DATE.compareTo(new Date())>0){
            return true;
        } else{
            return false;
        }
    }
    public void checkLicenceValidity(){
        if(EXPIRY_DATE.compareTo(new Date())<0){
            try {
                throw new MyExceptions("Software Licence Agreement Over. The date was "+EXPIRY_DATE);
            } catch (MyExceptions myExceptions) {
                myExceptions.printStackTrace();
            }
        }
    }
}
