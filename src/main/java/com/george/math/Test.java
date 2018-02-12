package com.george.math;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by George on 2017/9/25.
 */
public class Test {
    public static void main(String[] args){
        List<Character> keys=new ArrayList<Character>();
        keys.add('B');
        keys.add('C');
        keys.add('D');
        Iterator<Character> it=keys.iterator();
        it.next();
        it.next();
        it.remove();
        for(int i=0;i<keys.size();i++){
            System.out.println(keys.get(i));
        }
    }
}
