package com.george.math;

/**
 * Created by George on 2017/6/1.
 */
public class DivideSelect {//二分查找
    public static int select(int a[],int low,int high,int key){//非递归实现
       while(low<=high){
          int avg=(low+high)/2;
           if(key==a[avg]){
               return avg;
           }
           else if(key>a[avg]){
               low=avg+1;
           }
           else{
               high=avg-1;
           }
       }
       return -1;
    }
    public static int select1(int a[],int low,int high,int key){//二分查找递归实现
        if(low>high){
            return -1;
        }
        int avg=(low+high)/2;
        if(a[avg]==key){
            return avg;
        }
        else if(key>a[avg]){
            int temp=select1(a,avg+1,high,key);
            if(temp>0){
                return temp;
            }
            else{
                return -1;
            }
        }
        else{
            int temp=select1(a,low,avg-1,key);
            if(temp>0){
                return temp;
            }
            else{
                return -1;
            }
        }
    }
    public static void main(String[] args){
        int[] a=new int[]{12,13,45,56,76,78};
        System.out.println("key located in "+select1(a,0,6,49));
    }
}
