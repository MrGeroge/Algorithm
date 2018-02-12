package leetcode;

import java.util.ArrayList;

/**
 * Created by George on 2017/12/3.
 */
public class LinkedListCycleIi {//给定单链表，找到其循环开始的节点
    public ListNode detectCycle(ListNode head) {
        if(head==null){
            return null;
        }
        else if(head.next!=null && head.next==head){
            return head;
        }
        else if(head.next==null){
            return null;
        }
        else if(head.next.next!=null && head.next.next==head){
            return head;
        }
        else if(head.next.next!=null && head.next.next==head.next){
            return head.next;
        }
        else if(head.next.next==null){
            return null;
        }
        else{//至少三个节点
            ListNode p=head;
            ArrayList<ListNode> search=new ArrayList<ListNode>();
            while(p!=null){
                if(p.next==null){
                    return null;
                }
                else if(p.next==p){
                    return p;
                }
                else{//可能有两种情况，一种是指向下一个节点，一种是指向以前的节
                    ListNode q=p.next;
                    for(int i=0;i<search.size();i++){ //寻找p.next是否指向以前节点
                        if(search.get(i)==q){
                            return q;
                        }
                        else{
                            continue;
                        }
                    }
                    search.add(p);
                    p=q;
                }
            }
            return null;

        }

        }
    }

