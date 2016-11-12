package com.mirror2.csd.util;

import java.text.DecimalFormat;

public class Padding {
    public static void main(String[] args) {
        String cid = "DPL01";
        if(cid.startsWith("DPL")){
            cid = cid.substring(3,cid.length());
        }
        if(cid.startsWith(" ")){
            cid = cid.substring(1,cid.length());
        }
        DecimalFormat df = new DecimalFormat("000000");
        cid = "DPL "+df.format(Integer.parseInt(cid));
        System.out.println(cid);
    }
}
