package com.george.math;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by George on 2017/10/12.
 *
 * 判断一个数是否为素数
 */
public class PrimeNumber {

    public  static void main(String[] args){
        System.out.println(PrimeNumber.isPrime(100));
        List<Integer> list=PrimeNumber.primeFactor(100);
        if(list.size()<=2){
            System.out.println("num is prime");
        }
        else{
            System.out.println("num is not prime");
            for(Integer i:list){
                System.out.println(i);
            }
        }
    }

    public static boolean isPrime(int num){
        //List<Integer> list=new ArrayList<Integer>();
        if(num==1){
            return true;
        }
        else{
            int i=2;
            while(i<=Math.floor(Math.pow(num,0.5))){
                if(num%i==0){
                  return false;
                }
                else{
                    i++;
                }
            }
            return true;
        }
    }

    public static List<Integer> primeFactor(int num){
        List<Integer> list=new ArrayList<Integer>();
        if(num==1){
            list.add(1);
            return list;
        }
        else{
            list.add(1);
            int i=2;
            while(i<=num){
                if(num%i==0){
                    list.add(i);
                }
                i++;
            }
            return list;
        }
    }
}
