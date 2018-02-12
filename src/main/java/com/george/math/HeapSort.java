package com.george.math;

import org.junit.Test;

/**
 * Created by George on 2017/6/2.
 */
public class HeapSort {//堆排序
    public static void max_heapify(int a[],int n,int i){//维护堆数组
        int left=2*i;
        int right=2*i+1;
        int largest;
        if(left<=n&&a[left]>a[i]){
            largest=left;
        }
        else{
            largest=i;
        }
        if(right<=n&&a[right]>a[largest]){
            largest=right;
        }
        if(largest!=i){
            int temp;
            temp=a[largest];
            a[largest]=a[i];
            a[i]=temp;
            max_heapify(a,n,largest);
        }
       else{
            return;
        }
    }

    public static void build_max_heap(int a[],int n){//根据堆数组构建最大堆
        double temp=(double)n;
        int down=(int)Math.floor(temp/2);
        for(int i=down;i>=1;i--){
            max_heapify(a,n,i);
        }
    }
    public static int[] heap_sort(int a[],int n){//堆排序核心代码
        int[] b=new int[n];//保存堆排序结果
        build_max_heap(a,n);

        for(int i=n;i>2;i--){
            int temp;
            temp=a[i];
            a[i]=a[1];
            a[1]=temp;
            b[i-1]=a[i];//a[i]是目前最大值
            max_heapify(a,i-1,1);//保持最大堆
        }
        b[1]=a[2];
        b[0]=a[1];
        return b;
    }

    /**
     *
     * 下面是最大堆实现最大优先级队列
     */

    public static int heap_maximum(int a[],int n){//返回最高优先级元素即最大堆中的最大值
        return a[1];
    }

    public static int heap_extract_max(int a[],int n){//去掉最高优先级元素并返回即去掉最大值并返回
        int temp;
        temp=a[n];
        a[n]=a[1];
        a[1]=temp;
        int max=a[n];//a[i]是目前最大值
        max_heapify(a,n-1,1);//保持最大堆
        return max;//n-1为目前最大堆中的有效元素个数
    }

    public static void heap_increase_key(int a[],int n,int i,int key){//向上寻找a[i]=key后的插入位置，必须满足小于父节点key
       a[i]=key;
        int parent=(int)Math.floor((double) i/2);
        while(i>1&&a[parent]<a[i]){
            int temp;
            temp=a[parent];
            a[parent]=a[i];
            a[i]=temp;
            i=parent;
            parent=(int)Math.floor((double) i/2);
        }
        return;
    }

public static void max_heap_insert(int a[],int n,int key){
    a[n+1]=-1;
    heap_increase_key(a,n,n+1,key);
}

public static void build_max_heap_byinsert(int[] a,int n){//通过插入法建立最大堆
int heapSize=1;
    for(int i=2;i<n;i++){
        max_heap_insert(a,heapSize,a[i]);
        heapSize++;
    }
}

public static int heap_delete(int a[],int n,int i){//删除第i个节点
    int temp=a[i];
    a[i]=a[n];
    if(a[i]==temp){
        n=n-1;
    }
    else if(a[i]<temp){//维护最大堆
        n=n-1;
        max_heapify(a,i,n);
    }
    else if(a[i]>temp){
        n=n-1;
        int parent=(int)Math.floor((double)i/2);//i的父节点
        while(i>i&&a[parent]<a[i]){//需要向上维护最大堆
            temp=a[i];
            a[i]=a[parent];
            a[parent]=temp;
            i=parent;
            parent=(int)Math.floor((double)i/2);
        }
    }
    return n;
}

    public static void main(String[] args){
        int[] a=new int[]{0,15,13,10,5,12,9,7,4,0,6};
        heap_delete(a,10,3);
        for(int i=0;i<11;i++){
         System.out.println("a["+i+"]="+a[i]);
        }

    }
    @Test
    public void testInsertBuild(){
        int[] a=new int[]{0,15,13,10,5,12,9,7,4,0,6};
        build_max_heap_byinsert(a,10);
        for(int i=0;i<11;i++){
            System.out.println("a["+i+"]="+a[i]);
        }
    }
@Test
    public void testBuild(){
    int[] a=new int[]{0,15,13,10,5,12,9,7,4,0,6};
    build_max_heap(a,10);
    for(int i=0;i<11;i++){
        System.out.println("a["+i+"]="+a[i]);
    }
}
}
