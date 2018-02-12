package leetcode;

import java.util.List;

/**
 * Created by George on 2017/11/17.
 */
class ListNode {
      int val;
      ListNode next;
      ListNode(int x) { val = x;
      next=null;
      }
  }
public class MergeTwoSortedLists {
    public static void main(String[] args){
        ListNode node11=new ListNode(1);
        ListNode node12=new ListNode(2);
        ListNode node13=new ListNode(3);
        node11.next=node12;
        node12.next=node13;
        node13.next=null;

        ListNode node21=new ListNode(2);
        ListNode node22=new ListNode(3);
        ListNode node23=new ListNode(4);
        node21.next=node22;
        node22.next=node23;
        node23=null;

        MergeTwoSortedLists mtsl=new MergeTwoSortedLists();
        ListNode list=mtsl.mergeTwoLists(node11,node21);
        while(list!=null){
            System.out.println(list.val);
            list=list.next;
        }
    }
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if(l1==null){
            return l2;
        }
        if(l2==null){
            return l1;
        }
        ListNode p=l1;
        ListNode q=l2;
        ListNode list=new ListNode(-1);
        ListNode z=list;
        while(p!=null && q!=null){
            if(p.val<=q.val){
                ListNode tmp=new ListNode(p.val);
                tmp.next=null;
                z.next=tmp;
                z=tmp;
                p=p.next;
            }
            else{
                ListNode tmp=new ListNode(q.val);
                tmp.next=null;
                z.next=tmp;
                z=tmp;
                q=q.next;
            }
        }
        if(p!=null){
            z.next=p;
        }
        else{
            z.next=q;
        }
        return list.next;
    }
}
