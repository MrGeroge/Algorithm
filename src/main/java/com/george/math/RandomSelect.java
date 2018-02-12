package com.george.math;

import java.util.Random;

/**
 * Created by George on 2017/6/14.
 */
public class RandomSelect {
    public static int _quick_sort(int[] arrays, int start, int end) {
        if(start>=end){
            return -1;
        }
        int i = start;
        int j = end;
        Random random = new Random();
        int s = random.nextInt(end)%(end-start+1) + start;//产生start-end范围的随机数
        int temp;
        temp=arrays[i];
        arrays[i]=arrays[s];
        arrays[s]=temp;
        int value = arrays[i];
        boolean flag = true;
        while (i != j) {
            if (flag) {
                if (value > arrays[j]) {
                    swap(arrays, i, j);
                    flag=false;

                } else {
                    j--;
                }
            }else{
                if(value<arrays[i]){
                    swap(arrays, i, j);
                    flag=true;
                }else{
                    i++;
                }
            }
        }
        _quick_sort(arrays, start, i-1);
        _quick_sort(arrays, i+1, end);
        return i;

    }
    private static void swap(int[] arrays, int i, int j) {
        int temp;
        temp = arrays[i];
        arrays[i] = arrays[j];
        arrays[j] = temp;
    }
    public static int random_select(int[] a,int start,int end,int i){//在start-end之间寻找第i小的数
        if(start==end){
            return a[start];
        }
       int q= _quick_sort(a,start,end);
        int k=q-start+1;
        if(i==k){
            return a[q];
        }
        else if(i<k){
            return random_select(a,start,q-1,i);
        }
        else{
            return random_select(a,q+1,end,i-k);
        }

    }
    public static void main(String[] args){
        int[] a=new int[]{5,2,1,7,4,0,6};
        int tmp=random_select(a,0,6,6);
        System.out.print(tmp);
    }
}
