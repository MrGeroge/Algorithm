package offer;

import java.util.List;
import java.util.Stack;

/**
 * Created by George on 2018/1/5.
 */
public class ListTest {
    public static void main(String[] args){
        ListNode head=new ListNode(1);
        ListNode node1=new ListNode(2);
        ListNode node2=new ListNode(3);
        head.next=node1;
        node1.next=node2;
        ListTest lt=new ListTest();
        ListNode newHead=lt.reverse(head);
        ListNode p=newHead;
        while(p!=null){
            System.out.print(p.val);
            p=p.next;
        }
    }
    public void remove(ListNode head,int val){ //删除单链表中的值
        if(head==null){ //空表
            return;
        }
        else{//至少两个节点
            if(head.val==val){
                head=head.next;
            }
            else{
                ListNode p=head.next;
                ListNode q=head;
                while(p!=null){
                    if(p.val==val){
                        q.next=p.next;
                    }
                    else{
                        q=p;
                        p=p.next;
                    }
                }
            }
            return;
        }
    }
    public ListNode reverse(ListNode head){ //反向链表
        if(head==null){
            return null;
        }
        else{
            ListNode p=head;
            Stack<ListNode> stack=new Stack<ListNode>();
            while(p!=null){
                stack.push(p);
                p=p.next;
            }
            ListNode newHead=stack.pop();
            ListNode q=newHead;
            q.next=null;//创建新链表
            while(!stack.isEmpty()){
                ListNode tmp=stack.pop();
                q.next=tmp;
                q=tmp;
                q.next=null;
            }
            return newHead;
        }
    }

}
class ListNode{
    int val;
    ListNode next;
    public ListNode(int val){
        this.val=val;
        this.next=null;
    }
}