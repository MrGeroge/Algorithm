package offer;

import java.util.List;

/**
 * Created by George on 2018/1/7.
 */
public class ReverseList {//转置链表
    public static void main(String[] args){

    }
    public ListNode reverse(ListNode head){
        if(head==null || head.next==null){
            return head;
        }
        else{
            ListNode nHead=null;
            ListNode prev=null;
            ListNode p=head;

            while(p!=null){
                ListNode next=p.next;
                if(p.next==null){//p为尾节点
                    nHead=p;
                }
                p.next=prev;
                prev=p;
                p=next;
            }
            return nHead;
        }
    }
}
