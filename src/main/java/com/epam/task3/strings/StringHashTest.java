package com.epam.task3.strings;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

public class StringHashTest {
    public static void main(String[] str) {
        HashMap<StringHashOnFirst4Chars, String> hashBasedOnCharsMap = new HashMap<>();
        HashMap<StringHashOnLength, String> hashBasedOnLengthMap = new HashMap<>();
        LinkedHashMap<StringHashOnFirst4Chars, String> hashBasedOnCharsLinkedMap = new LinkedHashMap<>();
        LinkedHashMap<StringHashOnLength, String> hashBasedOnLengthLinkedMap = new LinkedHashMap<>();

        fillMap(hashBasedOnCharsMap);
        fillMap2(hashBasedOnLengthMap);
        fillMap(hashBasedOnCharsLinkedMap);
        fillMap2(hashBasedOnLengthLinkedMap);

        System.out.println("\n1\n");
        Iterator iter1 = hashBasedOnCharsMap.entrySet().iterator();
        while (iter1.hasNext()) {
            Map.Entry pair = (Map.Entry) iter1.next();
            System.out.print("[" + pair.getKey().hashCode() + "," + pair.getKey() + "," + pair.getValue() + "] ");
        }
        System.out.println("\n2\n");
        Iterator iter2 = hashBasedOnLengthMap.entrySet().iterator();
        while (iter2.hasNext()) {
            Map.Entry pair = (Map.Entry) iter2.next();
            System.out.print("[" + pair.getKey().hashCode() + "," + pair.getKey() + "," + pair.getValue() + "] ");
        }
        System.out.println("\n3\n");
        Iterator iter3 = hashBasedOnCharsLinkedMap.entrySet().iterator();
        while (iter3.hasNext()) {
            Map.Entry pair = (Map.Entry) iter3.next();
            System.out.print("[" + pair.getKey().hashCode() + "," + pair.getKey() + "," + pair.getValue() + "] ");
        }
        System.out.println("\n4\n");
        Iterator iter4 = hashBasedOnLengthLinkedMap.entrySet().iterator();
        while (iter4.hasNext()) {
            Map.Entry pair = (Map.Entry) iter4.next();
            System.out.print("[" + pair.getKey().hashCode() + "," + pair.getKey() + "," + pair.getValue() + "] ");
        }


    }

    public static void fillMap(HashMap<StringHashOnFirst4Chars, String> map) {
        map.put(new StringHashOnFirst4Chars("Element1"), "Element1");
        map.put(new StringHashOnFirst4Chars("El2"), "Element2");
        map.put(new StringHashOnFirst4Chars("Eleme3"), "Element3");
        map.put(new StringHashOnFirst4Chars("E4"), "Element4");
    }

    public static void fillMap2(HashMap<StringHashOnLength, String> map) {
        map.put(new StringHashOnLength("Element1"), "Element1");
        map.put(new StringHashOnLength("El2"), "Element2");
        map.put(new StringHashOnLength("Eleme3"), "Element3");
        map.put(new StringHashOnLength("E4"), "Element4");
    }
}
