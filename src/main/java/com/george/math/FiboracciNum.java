package com.george.math;

/**
 * Created by George on 2017/6/2.
 */
public class FiboracciNum {//斐波拉契数列递归
    public static int data(int n){
        if(n==0){
            return 0;
        }
        if(n==1){
            return 1;
        }
        return (data(n-1)+data(n-2));
    }
    public static void main(String[] args){
        System.out.println("data[7]="+data(7));

    }
}
