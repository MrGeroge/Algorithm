package com.george.math;

/**
 * Created by George on 2017/6/14.
 */
public class LinkedList {//双端链表
    class Node{
        public Node next;//后节点
        public Object data;//数据
    }
    private Node head;
    public LinkedList(){
        head=new Node();
        head.data=-1;
        head.next=null;
    }
    public boolean isEmpty(){
        if(head.next==null){
            return true;
        }
        else{
            return false;
        }
    }
    public int length(){//链表长度
        Node p=head;
        int j=0;
        while(p.next!=null){
            p=p.next;
            j++;
        }
        return j;
    }
    public void insert(int i,int element){//在链表中第i个位置之前插入元素element
        Node p=head;
        int j=0;//寻找第i-1个节点位置
        while(p!=null&&j<i-1){//p最终指向第i-1个节点,当i-1可取得链表尾元素
            p=p.next;
            j++;
        }
        if(p==null||j>i-1){//到达链表尾
            return;
        }
        else{
            Node node=new Node();
            node.data=element;
            node.next=p.next;
            p.next=node;
            return;
        }
    }
    public Object delete(int i){//删除第i个元素
        Node p=head;
        int j=0;
        while(p.next!=null&&j<i-1){//i-1不能取链表尾元素
            p=p.next;
            j++;
        }
        if(p.next==null||j>i-1){
            return -1;
        }
        else{
            Object result=p.next.data;
            p.next=p.next.next;
            return result;
        }
    }
    public Node search(Object key){//查找链表中是否有data=key的元素
        Node p=head.next;
        while(p!=null){
            if(p.data==key){
                return p;
            }
            else{
                p=p.next;
            }
        }
        return null;
    }

    public static void main(String[] args){
        LinkedList list=new LinkedList();
        for(int i=1;i<=10;i++){
            list.insert(i,i);
        }
        Node node=list.search(11);
        if(node==null){
            System.out.print("null");
        }
        else {
            System.out.print(node.data);
        }
    }

}
