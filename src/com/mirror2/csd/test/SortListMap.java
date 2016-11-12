package com.mirror2.csd.test;

import java.util.*;

public class SortListMap {
    public static void main(String[] args) {
        List<Map<String,Comparable>> summaryList = new ArrayList<Map<String, Comparable>>();
        Map<String, Comparable> objectMap = new HashMap<String, Comparable>();
        objectMap.put("id", "DPL 01");
        objectMap.put("name", "Hamid 1");
        summaryList.add(objectMap);

        objectMap = new HashMap<String, Comparable>();
        objectMap.put("id", "DPL 05");
        objectMap.put("name", "Hamid 5");
        summaryList.add(objectMap);

        objectMap = new HashMap<String, Comparable>();
        objectMap.put("id", "DPL 03");
        objectMap.put("name", "Hamid 3");
        summaryList.add(objectMap);

        Collections.sort(summaryList, new Comparator<Map<String, Comparable>>() {
            @Override
            public int compare(Map<String, Comparable> o1, Map<String, Comparable> o2) {
                return o1.get("id").compareTo(o2.get("id"));
            }
        });


        for(Map<String,Comparable> map : summaryList){
            System.out.print(map.get("id")+", ");
            System.out.println(map.get("id"));
        }


    }
}
