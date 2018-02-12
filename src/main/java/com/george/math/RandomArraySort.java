package com.george.math;

import java.util.Random;

/**
 * Created by George on 2017/6/2.
 */
public class RandomArraySort {//随机数组排序，随机产生优先级数组
    public static void sort(int a[],int n){
        int[] p=new int[n];
        Random random=new Random();//0~n*n*n
        for(int i=0;i<n;i++){
            p[i]=random.nextInt(n);
            System.out.println("p["+i+"]"+p[i]);
        }
        int j;
        for(j=0;j<n-1;j++){//需要n-1轮
            for(int i=0;i<(n-j-2);i++){
                if(p[i]>=p[i+1]){
                    int temp;
                    temp=a[i+1];
                    a[i+1]=a[i];
                    a[i]=temp;
                    temp=p[i+1];
                    p[i+1]=p[i];
                    p[i]=temp;
                }
            }
        }
        for(j=0;j<n;j++){
            System.out.println("a["+j+"]"+a[j]);
        }
    }
    public static void main(String[] args){
        int[] a=new int[]{1,2,3,4,5,6};
        sort(a,6);
        return;
    }
}
