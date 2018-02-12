package com.george.math;

import java.util.Random;

public class QuickSort{
    public int quick_sort(int[] arrays, int lenght) {
        if (null == arrays || lenght < 1) {
            System.out.println("input error!");
            return -1;
        }
       return _quick_sort(arrays, 0, lenght - 1);
    }

    public int _quick_sort(int[] arrays, int start, int end) {
        if(start>=end){
            return -1;
        }

        int i = start;
        int j = end;
        Random random = new Random();
        System.out.println("start="+start);
        System.out.println("end="+end);
        int s = random.nextInt(end)%(end-start+1) + start;//产生start-end范围的随机数
        System.out.println("random="+s);
        //int s=start;
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
        System.out.println("q="+i);
        _quick_sort(arrays, start, i-1);
        _quick_sort(arrays, i+1, end);
        return i;

    }
    private void swap(int[] arrays, int i, int j) {
        int temp;
        temp = arrays[i];
        arrays[i] = arrays[j];
        arrays[j] = temp;
    }

    public static void main(String args[]) {
        QuickSort q = new QuickSort();
        int[] a = { 49, 38, 65,12,45,5 };
        q.quick_sort(a,6);
        for(int i=0;i<a.length;i++){
            System.out.println("a["+i+"]"+a[i]);
        }

    }

}