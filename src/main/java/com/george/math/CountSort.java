package com.george.math;

/**
 * Created by Administrator on 2017/6/13 0013.
 */
public class CountSort { //计数排序
    public static int[] sort(int[] a,int n){
        int[] b=new int[n+1];
    int max=a[1];
    for(int i=2;i<=n;i++){
        if(a[i]>max){
            max=a[i];
        }
    }
    int k=max+1;
        int[] c=new int[k];
        for(int j=0;j<k;j++){
            c[j]=0;
        }
        for(int i=1;i<=n;i++){
            c[a[i]]=c[a[i]]+1;//统计a[i]出现次数
        }
        for(int j=1;j<k;j++){//c[j]表示小于等于j的个数
            c[j]=c[j]+c[j-1];
        }
        for(int j=n;j>=1;j--){
            b[c[a[j]]]=a[j];
            c[a[j]]=c[a[j]]-1;
        }
        return b;
    }
    public static int[] sort1(int[] a,int n){
        int[] b=new int[n];
        int max=a[0];
        for(int i=1;i<n;i++){
            if(a[i]>max){
                max=a[i];
            }
        }
        int k=max+1;
        int[] c=new int[k];
        for(int j=0;j<k;j++){
            c[j]=0;
        }
        for(int i=0;i<n;i++){
            c[a[i]]=c[a[i]]+1;//统计a[i]出现次数
        }
        for(int j=1;j<k;j++){//c[j]表示小于等于j的个数
            c[j]=c[j]+c[j-1];
        }
        for(int j=n-1;j>=0;j--){
            b[c[a[j]]-1]=a[j];
            c[a[j]]=c[a[j]]-1;
        }
        return b;
    }
    public static void main(String[] args){
        int[] a=new int[]{0,2,3,2,1,4,3,6,3};
        int[] b=new int[9];
        b=sort1(a,9);
        for(int i=0;i<9;i++){
            System.out.println("b["+i+"]="+b[i]);
        }
    }
}
