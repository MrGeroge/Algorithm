package com.george.math;

/**
 * Created by George on 2017/6/5.
 */
public class StoogeSort {
    public static void sort(int[] a,int i,int j){
        if(a[i]>a[j]){
            int temp;
            temp=a[j];
            a[j]=a[i];
            a[i]=temp;
        }
        if((i+1)>=j){//只有两个数排序时
            return;
        }
        int k=(int)Math.floor((double)(j-i+1)/3);//j-i+1表示i～j之间一共几个数
        sort(a,i,j-k);
        sort(a,i+k,j);
        sort(a,i,j-k);
    }
    public static void main(String[] args){
        int[] a = { 49, 38, 65,12,45,5 };
        sort(a,0,5);
        for(int i=0;i<a.length;i++){
            System.out.println("a["+i+"]"+a[i]);
        }

    }}
