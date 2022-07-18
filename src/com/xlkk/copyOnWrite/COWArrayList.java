package com.xlkk.copyOnWrite;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

public class COWArrayList {
    public static void main(String[] args) {
//        List<String> list = Collections.synchronizedList(new ArrayList<>());
//        List<String> list = new CopyOnWriteArrayList<>();
        List<String> list = new Vector<>();

        for(int i =0;i<=10;i++){
            new Thread(()->{
                list.add(UUID.randomUUID().toString().substring(0,5));
                System.out.println(list);
            },String.valueOf(i)).start();
        }
    }

}
