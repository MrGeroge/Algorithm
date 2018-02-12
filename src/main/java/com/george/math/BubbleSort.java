package com.george.math;

/**
 * Created by George on 2017/6/1.
 */
public class BubbleSort {//冒泡排序
    public static void sort(int a[],int n){
        int j;
        for(j=0;j<n-1;j++){//需要n-1轮
            for(int i=0;i<(n-j-2);i++){
                if(a[i]>=a[i+1]){
                    int temp;
                    temp=a[i+1];
                    a[i+1]=a[i];
                    a[i]=temp;
                }
            }
        }
        for(int k=0;k<n;k++){
            System.out.println("a["+k+"]="+a[k]);
        }
    }
    public static void main(String[] args){
        int[] a=new int[]{10,213,423,11,52,0,12,452,546};
        sort(a,9);
        return;
    }
}
