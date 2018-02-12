package com.george.math;

import org.junit.Test;

/**
 * Created by George on 2017/6/1.
 */
public class MergeSort {//合并排序
    public static void main(String[] args){
        int[] a=new int[]{2,4,5,7,1,2,3,6};
        int[] left=new int[]{2,4,5,7};
        int[] right=new int[]{1,2,3,6};
        int i=0,j=0,k=0;
        while(i<left.length&&j<right.length){//不能越界
            if(left[i]<=right[j]){
                a[k]=left[i];
                k++;
                i++;
            }
            else{
                a[k]=right[j];
                k++;
                j++;
            }
        }
        while(k<a.length){
            if(i<left.length){
                a[k]=left[i];
                k++;
                i++;
            }
            if(j<right.length){
                a[k]=right[j];
                k++;
                j++;
            }
        }
        for(k=0;k<a.length;k++){
            System.out.println("a["+k+"]"+a[k]);
        }
        return;
    }
}
