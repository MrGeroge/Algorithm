package leetcode;

import java.util.List;

/**
 * Created by George on 2017/12/3.
 */
public class InsertionSortList { //使用插入排序单链表
    public static void main(String[] args){
        InsertionSortList isl=new InsertionSortList();
        ListNode head=new ListNode(3);
        ListNode node2=new ListNode(2);
        ListNode node3=new ListNode(4);
        head.next=node2;
        node2.next=node3;
        ListNode newHead=isl.insertionSortList(head);
        ListNode p=newHead;
        while(p!=null){
            System.out.println(p.val);
            p=p.next;
        }
    }
    public ListNode insertionSortList(ListNode head) { //head为第一个节点
        if(head==null){
            return null;
        }
        else if(head.next==null){
            return head;
        }
        else if(head.next.next==null){//两个节点,使用插入排序
            if(head.val<=head.next.val){
                return head;
            }
            else{
               int second=head.next.val; //第二个节点值
                int first=head.val; //第一个节点值
                int temp=second;
                head.next.val=first;
                head.val=temp;
                return head;
            }
        }
        else{ //假设前n-1个节点插入排序，然后对最后的一个节点排序
            ListNode p=head;
            ListNode q=p;
            while(p.next!=null){
                q=p; //保留前一个节点
                p=p.next;
            }//q指向第n-1个节点，p指向第n个节点
            q.next=null; //截断
            ListNode newHead=insertionSortList(head); //对前n-1个排序,然后对p指向的节点进行插入,可插入位置分别为前，中，后
            if(newHead.val>=p.val){ //前
                p.next=newHead;
                return p;
            }
            else{
                ListNode s=newHead;
                ListNode front=s;
                while(s!=null){ //中
                    if(s.val<p.val){
                        front=s;
                        s=s.next;
                    }
                    else{
                        p.next=s;
                        front.next=p;
                        return newHead;
                    }
                }
                if(s==null){//后
                    front.next=p;
                    p.next=null;
                }
                return newHead;
            }

        }

    }
}
