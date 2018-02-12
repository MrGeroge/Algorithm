package leetcode;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by George on 2017/12/3.
 */
public class SortList {//排序链表
    public static void main(String[] args){
        SortList sl=new SortList();
        //ListNode head=new ListNode(-1);
        ListNode node1=new ListNode(2);
        ListNode node2=new ListNode(1);
        //head.next=node1;
        node1.next=node2;
        sl.sortList(node1);
        ListNode node=node1;
        while(node!=null){
            System.out.println(node.val);
            node=node.next;
        }
    }
    public ListNode sortList(ListNode head) { //head是第一个节点
        if(head==null){
            return null;
        }
        else if(head.next==null){ //只有一个节点不需要排序
            return head;
        }
        else{ //至少两个节点n
            List<ListNode> nodes=new ArrayList<ListNode>(); //保存所有节点
            ListNode node=head; //第一个节点
            while(node!=null){
                nodes.add(node);
                node=node.next;
            }
            int n=nodes.size();
            //冒泡排序法
            for(int i=0;i<n-1;i++){
                for(int j=0;j<n-i-1;j++){
                    int tmp;
                    ListNode first=nodes.get(j);
                    ListNode second=nodes.get(j+1);
                    if(first.val<=second.val){
                        continue;
                    }
                    else{ //逆序
                        tmp=second.val;
                        second.val=first.val;
                       first.val=tmp;
                    }
                }
            }
            return head;
        }
    }
}

