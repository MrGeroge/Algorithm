package leetcode;

import java.util.HashMap;

/**
 * Created by George on 2017/12/21.
 */
public class RemoveDuplicatesFromSortedList {
    public static  void main(String[] args){
        ListNode head=new ListNode(1);
        ListNode node1=new ListNode(1);
        ListNode node2=new ListNode(2);
        ListNode node3=new ListNode(3);
        ListNode node4=new ListNode(3);
        //ListNode node5=new ListNode(4);
        head.next=node1;
        node1.next=node2;
        node2.next=node3;
        node3.next=node4;
        //node2.next=node3;
        //node3.next=node4;
        //node4.next=node5;
        RemoveDuplicatesFromSortedList rd=new RemoveDuplicatesFromSortedList();
        ListNode tmp=rd.deleteDuplicates(head);
        System.out.print(1);
    }
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        } else {//至少两个节点
            ListNode p = head;
            ListNode parent = head;
            HashMap<Integer, Integer> map = new HashMap<Integer, Integer>(); //每个值出现的次数
            while (p != null) {
                Integer count = map.get(p.val);
                if (count == null) {
                    count = new Integer(1);
                    map.put(p.val, count);
                } else {
                    map.put(p.val, count.intValue() + 1);
                }
                p = p.next;
            }
            p = head;
            while (p != null) {
                Integer count = map.get(p.val);
                if (count == 1) {
                    parent = p;
                    p = p.next;
                } else {//>1
                    if (p == head) {
                        while (count > 1) {
                            p = p.next;
                            count--;
                        }
                        head = p;
                        parent = p;
                        p=p.next;
                    } else {
                        while (count > 1) {
                            p = p.next;
                            count--;
                        }
                        parent.next = p;
                        p=p.next;
                    }
                }
            }
            return head;
        }
    }
}
