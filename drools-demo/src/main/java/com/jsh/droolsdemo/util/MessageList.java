package com.jsh.droolsdemo.util;

import java.util.List;
import java.util.Vector;

public class MessageList {
    public static List<String> testList = new Vector<>();

    public static void init(){
        testList.clear();
    }

    public static void add(Object result){
        if (result != null) {
            if (result instanceof String) {
                testList.add((String) result);
            }
        }
    }
}