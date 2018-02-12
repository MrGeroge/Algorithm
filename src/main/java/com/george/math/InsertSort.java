package com.george.math;

import org.junit.Test;

import java.util.ArrayList;

/**
 * Created by George on 2017/5/31.
 */
public class InsertSort {//插入排序
    public static void main(String[] args){
        int[] a=new int[]{1,2,3,5,6,7,8,0};//已排好序的数组
        int key=4;//待插入数据
        int i=a.length-2;
        while(i>=0&&a[i]>key){
          a[i+1]=a[i];
            i--;
        }
        a[i+1]=key;
        for(int j=0;j<8;j++){
            System.out.println("a["+j+"]="+a[j]);
        }

    }
    private static void insertSort(int[] data,int n)
    {
        if(n>1)
        {
            insertSort(data,n-1);
            merge(data,n-1,n);
        }
    }
    private static void merge(int[] data,int end,int n)
    {
        int temp=data[n-1];
        int i;
        for( i=end-1; i>=0; i--)
        {
            if(data[i]>temp)
                data[i+1]=data[i];
            else
                break;
        }
        data[i+1]=temp;
    }
    @Test
    public void test(){//测试插入排序递归版本
        int[] a=new int[]{1,3,2,6,4,8,7,0};//已排好序的数组
        insertSort(a,7);
        for(int j=0;j<8;j++){
            System.out.println("a["+j+"]="+a[j]);
        }
    }
}
