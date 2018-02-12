package leetcode;

import java.util.ArrayList;

/**
 * Created by George on 2017/12/19.
 */
public class PartitionList {
    public static void main(String[] args){
        PartitionList pl=new PartitionList();
        ListNode head=new ListNode(1);
        /*ListNode node1=new ListNode(4);
        ListNode node2=new ListNode(3);
        ListNode node3=new ListNode(2);
        ListNode node4=new ListNode(5);
        ListNode node5=new ListNode(2);
        head.next=node1;
        node1.next=node2;
        node2.next=node3;
        node3.next=node4;
        node4.next=node5;
        node5.next=null;*/
        ListNode node1=new ListNode(1);
        head.next=node1;
        pl.partition(head,2);
        System.out.print(1);
    }
    public ListNode partition(ListNode head, int x) {//思路找到第一个大于或者等于x的节点n，这个节点以后的节点值如果小于x则插入到n之前
        if(head==null || head.next==null){
            return head;
        }
        else { //至少两个节点
            ListNode p=head;
            while(p!=null){ //找到第一个大于等于x的节点p
                if(p.val>=x){
                    break;
                }
                else{
                    if(p.next==null){//p是最后一个节点都找不到>=x的值，直接返回head
                        return head;
                    }
                    else{
                        p=p.next;
                    }
                }
            }
            ListNode parent=p; //插入位置为p
            ListNode q=p.next;
            while(q!=null){
                if(q.val<x){//待处理q
                    parent.next=q.next; //删除q
                    if(p==head){ //无parent
                        q.next=p;
                        head=q;
                        //q=q.next;
                        q=p.next;
                    }
                    else{
                        ListNode n=head;
                        while(n.next!=p){ //寻找p的parent
                            n=n.next;
                        }
                        n.next=q;
                        q.next=p;
                        //q=q.next;
                        q=p.next;
                    }
                }
                else{
                    parent=q;
                    q=q.next;
                }
            }
            return head;
        }
    }
}
