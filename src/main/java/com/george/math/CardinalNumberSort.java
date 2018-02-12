package com.george.math;

/**
 * Created by Administrator on 2017/6/13 0013.
 */
public class CardinalNumberSort {//基数排序
    public static int[] sort(int[] a,int n,int d){//d为维度
        for(int i=0;i<d;i++){
            int[][] temp=new int[10][n];
            int[] count=new int[10];
            int weight=(int)Math.pow(10,i);
            for(int num:a){//遍历数组中的元素
                int dight=(num/weight)%10;
                temp[dight][count[dight]]=num;
                count[dight]++;
            }
            int index=0;
            for(int j=0;j<10;j++){
                for(int k=0;k<count[j];k++){
                    a[index]=temp[j][k];
                    index++;
                }
            }
        }
        return a;
    }
    public static void main(String[] args){
     int[] a=new int[]{121,101,192,180,273,456,789};
     int[] b=new int[7];
     b=sort(a,7,3);
     for(int i=0;i<7;i++){
         System.out.println("b[i"+i+"]="+b[i]);
     }
    }
}
