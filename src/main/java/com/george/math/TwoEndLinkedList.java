package com.george.math;

import java.awt.peer.SystemTrayPeer;

/**
 * Created by George on 2017/9/14.
 */

class TwoEndNode{
    private TwoEndNode prev; //前驱节点
    private TwoEndNode next; //后继节点
    private Object data; //实际数据
    public TwoEndNode(TwoEndNode prev,TwoEndNode next,Object data){
        this.prev=prev;
        this.next=next;
        this.data=data;
    }

    public TwoEndNode(Object o){
        this.prev=null;
        this.next=null;
        this.data=o;
    }
    public TwoEndNode(){

    }

    public TwoEndNode getPrev() {
        return prev;
    }

    public void setPrev(TwoEndNode prev) {
        this.prev = prev;
    }

    public TwoEndNode getNext() {
        return next;
    }

    public void setNext(TwoEndNode next) {
        this.next = next;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
public class TwoEndLinkedList { //双端链表
    private TwoEndNode head; //头节点
    public TwoEndLinkedList(){
        head=null;
    }
    public TwoEndLinkedList(TwoEndNode head){
        this.head=head;
    }
    public TwoEndNode listSearch(Object key){//查找链表中有无此元素,若tmp==null则表示链表中无此元素，若tmp!=null则表示tmp即为查找的元素
        TwoEndNode tmp=head; //从前向后查找
        while(tmp!=null&&!tmp.getData().equals(key)){
            tmp=tmp.getNext();
        }
        return tmp;
    }
    public void listInsert(TwoEndNode node){//链表中插入元素node,在当前head节点前插入
        node.setNext(head);
        if(head!=null){
           head.setPrev(node);
        }
        node.setPrev(null);
        head=node;
    }

    public void listDelete(TwoEndNode node){//从链表中删除此节点node
        if(node.getPrev()!=null){ //说明非头节点
            node.getPrev().setNext(node.getNext());
        }
        else{
            head=node.getNext();
        }
        if(node.getNext()!=null){//说明非尾节点
            node.getNext().setPrev(node.getPrev());
        }
    }

    public static void main(String[] args){
        TwoEndLinkedList linkedList=new TwoEndLinkedList();
        String key="head";
        TwoEndNode node=new TwoEndNode(key);
        linkedList.listInsert(node);
        String tag="tag";
        node=new TwoEndNode(tag);
        linkedList.listInsert(node);
        node=linkedList.listSearch("tag");
        linkedList.listDelete(node);
        node=linkedList.listSearch("head");
        if(node==null){
            System.out.println("null");
            return;
        }
        else{
            System.out.println(node.getData());
        }
        node=new TwoEndNode("ppp");
        TwoEndLinkedListWithSentinel sentinelLinkedList=new TwoEndLinkedListWithSentinel();
        sentinelLinkedList.listInsert(node);
        node=new TwoEndNode("qqq");
        sentinelLinkedList.listInsert(node);
        node=new TwoEndNode("sss");
        sentinelLinkedList.listInsert(node);
        node = sentinelLinkedList.listSearch("qqq");
        System.out.println(node.getData());
        sentinelLinkedList.listDelete(node);
        node=linkedList.listSearch("qqq");
        if(node==null){
            System.out.println("null");
            return;
        }
        else{
            System.out.println(node.getData());
        }

    }

}
        class TwoEndLinkedListWithSentinel{ //带佣兵的双端链表
        private TwoEndNode sentinel;
            public TwoEndLinkedListWithSentinel(){
                sentinel=new TwoEndNode();
                sentinel.setPrev(new TwoEndNode());
                sentinel.setNext(new TwoEndNode());
                sentinel.setData(null);
            }

            public TwoEndNode listSearch(Object o){
                TwoEndNode tmp=sentinel.getNext(); //第一个节点
                while(tmp!=null&&!tmp.getData().equals(o)){
                    tmp=tmp.getNext();
                }
                return tmp;
            }

            public void listInsert(TwoEndNode node){ //插入到表头
                node.setNext(sentinel.getNext());
                sentinel.getNext().setPrev(node);
                node.setPrev(sentinel);
                sentinel.setNext(node);
            }

            public void listDelete(TwoEndNode node){
                node.getNext().setPrev(node.getPrev());
                node.getPrev().setNext(node.getNext());
            }
        }
