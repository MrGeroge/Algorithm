package com.george.math;

import java.util.Arrays;

/**
 * Created by Administrator on 2017/6/13 0013.
 */
public class BucketSort {//桶排序
    public static double[] sort(double[] a,int n,int bucketCount){
        double[][] buckets=new double[bucketCount][n];
        int[] count=new int[bucketCount];
        for(int i=0;i<n;i++){
            int index=(int)Math.floor((double)n*a[i]);
            buckets[index][count[index]]=a[i];
            count[index]++;
        }
        for(int i=0;i<bucketCount;i++){
            double[] temp=new double[count[i]];
            for(int j=0;j<count[i];j++){
                temp[j]=buckets[i][j];
            }
            Arrays.sort(temp);
            for(int j=0;j<count[i];j++){
                buckets[i][j]=temp[j];
            }
        }
        double[] b=new double[n];
        int in=0;
        for(int i=0;i<bucketCount;i++){
            for(int j=0;j<count[i];j++){
                b[in]=buckets[i][j];
                in++;
            }
        }
        return b;
    }
    public static void main(String[] args){
        double[] a=new double[]{0.78,0.17,0.39,0.26,0.72,0.94,0.21,0.12,0.23,0.68};
        double[] b=new double[10];
        b=sort(a,10,10);
        for(int i=0;i<10;i++){
            System.out.println("b[i"+i+"]="+b[i]);
        }
    }
}
