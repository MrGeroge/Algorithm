package com.george.math;

/**
 * Created by George on 2017/6/1.
 */
public class Set2X {//判断集合S中是否存在两个数x1,x2使得x=x1+x2
    public static boolean isExist(int a[],int n,int x){
        for(int i=0;i<n-1;i++){
            for(int j=i+1;j<n;j++){
                if(a[i]+a[j]==x){
                    System.out.println("a["+i+"]"+"+a["+j+"]=="+x);
                    return true;
                }
            }
        }
        return false;
    }
    public static void main(String[] args){
        int[] a=new int[]{2,1,3,5,6,7,10};
        if(isExist(a,7,11)){
            return;
        }
    }
}
