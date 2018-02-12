package com.george.math;

/**
 * Created by George on 2017/5/31.
 */
public class SelectSort {//选择排序
    public static void main(String[] args){
        int[] a=new int[]{10,213,423,11,52,0,12,452,546};
        int j;
        int i;
        int temp=0;
        for(j=0;j<a.length-1;j++){
            int min=a[j];
            i=j+1;
            while(i<a.length){//找到了最小值
                if(a[i]<=min){
                    min=a[i];
                    temp=i;
                }
                i++;
            }
            if(a[j]!=min) {
                a[temp] = a[j];
                a[j] = min;
            }
        }
        for(int k=0;k<a.length;k++){
            System.out.println("a["+k+"]="+a[k]);
        }
        return;
    }
}
