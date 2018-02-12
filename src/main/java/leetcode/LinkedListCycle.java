package leetcode;

import java.util.ArrayList;

/**
 * Created by George on 2017/12/3.
 */
public class LinkedListCycle {
    public boolean hasCycle(ListNode head) {
        if(head==null){
            return false;
        }
        else if(head.next!=null && head.next==head){
            return true;
        }
        else if(head.next==null){
            return false;
        }
        else if(head.next.next!=null && head.next.next==head){
            return true;
        }
        else if(head.next.next!=null && head.next.next==head.next){
            return true;
        }
        else if(head.next.next==null){
            return false;
        }
        else{//至少三个节点
            ListNode p=head;
            ArrayList<ListNode> search=new ArrayList<ListNode>();
            while(p!=null){
                if(p.next==null){
                    return false;
                }
                else if(p.next==p){
                    return true;
                }
                else{//可能有两种情况，一种是指向下一个节点，一种是指向以前的节
                    ListNode q=p.next;
                    for(int i=0;i<search.size();i++){ //寻找p.next是否指向以前节点
                        if(search.get(i)==q){
                            return true;
                        }
                        else{
                            continue;
                        }
                    }
                    search.add(p);
                    p=q;
                }
            }
            return false;

        }
    }
}
