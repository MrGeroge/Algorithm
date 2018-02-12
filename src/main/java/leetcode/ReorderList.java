package leetcode;

import java.util.ArrayList;

/**
 * Created by George on 2017/12/3.
 */
public class ReorderList {
    public void reorderList(ListNode head) {
        if(head==null){
            return;
        }
        else if(head.next==null){
            return;
        }
        else if(head.next.next==null){
            return;
        }
        else{ //至少有三个节点
            int n=0;
            ListNode p=head;
            while(p!=null){
                n++;
                p=p.next;
            }//得到一共n个节点
            if(n%2==0){ //偶数链表处理
                int index=n/2; //最后一个元素下标
                ArrayList<ListNode> insertNodes=new ArrayList<ListNode>();
                int j=0;
                ListNode s=head;
                while(s!=null){
                    if(j>=index+1){
                        insertNodes.add(s);
                    }
                    else{

                    }
                    j++;
                    s=s.next;
                }
                ListNode q=head;
                ListNode t=q.next;
                int h=0;
                int end=n/2-1;
                while(h<=end-1){
                    ListNode insertNode=insertNodes.get(insertNodes.size()-1-h); //待插入节点
                    insertNode.next=t;
                    q.next=insertNode;
                    h++;
                    q=t;
                    t=t.next;
                }
                t.next=null;
                return;
            }
            else{//奇数链表处理 L0,L1,L2,...L(n/2向下取整)源数据，待插入节点为Ln,Ln-1,....L(n/2向上取整)
                int count=(int)Math.ceil((double) n/2); //向上取整
                ArrayList<ListNode> insertNodes=new ArrayList<ListNode>();
                int i=0;
                ListNode s=head;
                while(s!=null){
                    if(i>=count){
                        insertNodes.add(s);
                    }
                    else{//原始节点，待插入

                    }
                    i++;
                    s=s.next;
                }
                ListNode q=head;
                ListNode t=q.next;
                int j=0;
                int end=(int)Math.floor((double) n/2);//向上取整
                while(j<=end-1){
                    ListNode insertNode=insertNodes.get(insertNodes.size()-1-j); //待插入节点
                    insertNode.next=t;
                    q.next=insertNode;
                    j++;
                    q=t;
                    t=t.next;
                }
                q.next=null;
                return;
            }

        }
    }
}
