package com.george.math;

/**
 * Created by George on 2017/6/1.
 */
public class ReverseCount {//逆序对计数
    public static int count(int a[],int n){
        int count=0;
        for(int i=0;i<n-1;i++){
            for(int j=i+1;j<n;j++){
                if(a[i]>a[j]){
                    count++;
                }
            }
        }
        return count;
    }
    public static void main(String[] args){
        int[] a=new int[]{2,3,8,6,1};
        int count=count(a,5);
        System.out.print(count);
    }
}
