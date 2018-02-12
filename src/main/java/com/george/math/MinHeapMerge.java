package com.george.math;

import org.junit.Test;

/**
 * Created by George on 2017/6/3.
 */

public class MinHeapMerge {//最小堆实现K路合并排序
    static class Node{
        public int data;
        public int index;//属于哪个链表
    }
    public static void min_heapify(Node[] nodes,int i,int heapSize){//维持节点i以下的最小堆
        int left=2*i;
        int right=2*i+1;
        int min;
        if(left<=heapSize&&nodes[left].data<nodes[i].data){
            min=left;
        }
        else{
            min=i;
        }
        if(right<=heapSize&&nodes[right].data<nodes[min].data){
            min=right;
        }
        if(min!=i){
            Node temp=new Node();
            temp.data=nodes[min].data;
            temp.index=nodes[min].index;
            nodes[min].data=nodes[i].data;
            nodes[min].index=nodes[i].index;
            nodes[i].data=temp.data;
            nodes[i].index=temp.index;
            min_heapify(nodes,min,heapSize);
        }
        else{
            return;
        }
    }
    public static void build_min_heap(Node[] nodes,int heapSize){//构建最小堆
        int down=(int)Math.floor((double)heapSize/2);
        for(int i=down;i>=1;i--){
            min_heapify(nodes,i,heapSize);
        }
    }
    public static int min_heap_delete(Node[] nodes,int i,int heapSize){//删除节点i
        Node temp=new Node();
        temp.data=nodes[i].data;
        temp.index=nodes[i].index;
        nodes[i].data=nodes[heapSize].data;
        nodes[i].index=nodes[heapSize].index;
        if(nodes[i].data==temp.data){
            heapSize=heapSize-1;
        }
        else if(nodes[i].data>temp.data){//维护最小堆
            heapSize=heapSize-1;
            min_heapify(nodes,i,heapSize);
        }
        else if(nodes[i].data<temp.data){
            heapSize=heapSize-1;
            int parent=(int)Math.floor((double)i/2);//i的父节点
            while(i>i&&nodes[parent].data>nodes[i].data){//需要向上维护最小堆
                temp.data=nodes[i].data;
                temp.index=nodes[i].index;
                nodes[i].data=nodes[parent].data;
                nodes[i].index=nodes[parent].index;
                nodes[parent].data=temp.data;
                nodes[parent].index=temp.index;
                i=parent;
                parent=(int)Math.floor((double)i/2);
            }
        }
        return heapSize;
    }

    public static void merge(Node[][] klist,int k,int m,Node[] result){
     int heapSize=0;
        int i=0;
        Node[] tmp=new Node[m+1];
        for(int j=0;j<m+1;j++){
            tmp[j]=new Node();
        }
        int j=0;
        int index=0;
        int n=k*m;
        int[] b=new int[k];
        for(int h=0;h<k;h++){
            b[h]=0;
        }
        for(i=0;i<k;i++){
            tmp[i+1].data=klist[i][0].data;
            tmp[i+1].index=klist[i][0].index;//klist[i][0].index==该节点属于哪条链
            b[i]++;//第i条链下一个访问元素
        }
        heapSize=k;
        build_min_heap(tmp,heapSize);//建立最小堆
        while(n>0){
            result[j].data=tmp[1].data;//把堆顶元素（最小值）放入输出链表中
            result[j].index=tmp[1].index;//堆顶元素属于哪个链
            index=result[j].index;
            j++;
            n--;
            if(b[index]<m){//堆顶元素所在链还有其他元素
                tmp[1].data=klist[index][b[index]].data;//将index链中的下一个元素作为堆顶元素
                tmp[1].index=klist[index][b[index]].index;
                b[index]++;
                min_heapify(tmp,1,heapSize);
            }
            else{
                heapSize=min_heap_delete(tmp,1,heapSize);//若堆顶元素所在链表为空，则只需关注其他链即可，即把堆顶元素删除
            }
        }
    }
    @Test
    public void test(){
        Node[] nodes=new Node[11];
        nodes[0]=new Node();
        nodes[1]=new Node();
        nodes[2]=new Node();
        nodes[3]=new Node();
        nodes[4]=new Node();
        nodes[5]=new Node();
        nodes[6]=new Node();
        nodes[7]=new Node();
        nodes[8]=new Node();
        nodes[9]=new Node();
        nodes[10]=new Node();
        nodes[0].data=0;
        nodes[0].index=1;
        nodes[1].data=4;
        nodes[1].index=1;
        nodes[2].data=1;
        nodes[2].index=1;
        nodes[3].data=3;
        nodes[3].index=1;
        nodes[4].data=2;
        nodes[4].index=1;
        nodes[5].data=16;
        nodes[5].index=1;
        nodes[6].data=9;
        nodes[6].index=1;
        nodes[7].data=10;
        nodes[7].index=1;
        nodes[8].data=14;
        nodes[8].index=1;
        nodes[9].data=8;
        nodes[9].index=1;
        nodes[10].data=7;
        nodes[10].index=1;
        build_min_heap(nodes,10);
        for(Node node:nodes){
            System.out.println("node="+node.data);
        }
    }

    @Test
    public void testMerge(){
        Node[][] nodes=new Node[4][5];
        int count=1;
        for(int i=0;i<4;i++){
            nodes[i]=new Node[5];
            for(int j=0;j<5;j++){
                nodes[i][j]=new Node();
                nodes[i][j].data=count+j;
                nodes[i][j].index=i;
            }
            count=count*10;
        }
        Node[] result=new Node[20];
        for(int k=0;k<20;k++){
            result[k]=new Node();
        }
        merge(nodes,4,5,result);
        for(int k=0;k<20;k++){
            System.out.println("result["+k+"]="+result[k].data);
        }
    }
}
