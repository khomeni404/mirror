package com.mirror2.util;

import org.apache.commons.lang.StringUtils;

import java.io.DataOutput;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

/**
 * Created by DORPAN Server on 05/06/2015.
 */
public class MirrorUtil {
    public static String makeCid(String cid) {
        if (cid.toUpperCase().startsWith("DPL")) {
            cid = cid.substring(3, cid.length());
        }
        DecimalFormat df = new DecimalFormat("000000");
        return "DPL " + df.format(Integer.parseInt(cid.trim()));

    }

    public static String getStatus(int index) {
        return MirrorDataList.APP_STATUS[index][1];
    }

    public static List<Map<String, Comparable>> sortBySalesQty(List listMap) {
        Collections.sort(listMap, new Comparator<Map<String, Comparable>>() {

            @Override
            public int compare(Map<String, Comparable> m1, Map<String, Comparable> m2) {
                return m1.get("full_inc").compareTo(m2.get("full_inc")); //Ascending
            }
        });
        return listMap;
    }

    public static String toJasperAmt(Object o) {
        Double d = (Double) o;
        return BigDecimal.valueOf(d.intValue()).toPlainString();
    }
    public static String toJasperAmt(Double d) {
        return BigDecimal.valueOf(d.intValue()).toPlainString();
    }

    public static String toCommaFormattedTaka(Object takaPaisaObj) {
        String takaPaisa = String.valueOf(takaPaisaObj);
        if (takaPaisa == null) return "";
        boolean hasPaisa = takaPaisa.split("\\.").length > 1;
        String taka = takaPaisa.split("\\.")[0];
        String paisa = "";
        if (hasPaisa) {
            paisa = takaPaisa.split("\\.")[1];
            paisa = cutOrPadRight(paisa, 2, "0");
        }else {
            paisa = "00";
        }
        int len = taka.length();
        if (len <= 3) {
            return taka + "." + paisa;
        } else {
            String sotok = taka.substring(len - 3);
            String result = "";
            String rest = taka.substring(0, len - 3);
            for (int i = 0; i <= rest.length(); i++) {
                if (rest.length() % 2 == 0) {
                    result += rest.substring(0, 2);
                    rest = rest.substring(2);
                } else {
                    result += rest.substring(0, 1);
                    rest = rest.substring(1);
                }
                result += ",";
            }
            //System.out.println(result);
            return result + sotok + "." + paisa;
        }
    }
    static String cutOrPadRight(String str, int size, String padStr) {
        str = str == null ? "" : str;
        int len = str.trim().length();
        str = len > size ? str.substring(0, size) : str;
        return StringUtils.rightPad(str, size, padStr);
    }

    static String cutOrPadLeft(String str, int size, String padStr) {
        str = str == null ? "" : str;
        int len = str.trim().length();
        str = len > size ? str.substring(0, size) : str;
        return StringUtils.leftPad(str, size, padStr);
    }
}
