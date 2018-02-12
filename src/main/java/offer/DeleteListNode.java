package offer;

/**
 * Created by George on 2018/1/7.
 */
public class DeleteListNode {
    public static void main(String[] args){
        ListNode head=new ListNode(1);
        ListNode node1=new ListNode(2);
        ListNode node2=new ListNode(3);
        ListNode node3=new ListNode(4);
        head.next=node1;
        node1.next=node2;
        node2.next=node3;
        DeleteListNode deleteListNode=new DeleteListNode();
        head=deleteListNode.deleteNode(head,node3);
        return;
    }
    public ListNode deleteNode(ListNode head,ListNode node){
        if(head==null || node==null){
            return null;
        }
        else{
            if(node.next!=null){
                ListNode next=node.next;
                node.val=next.val;
                node.next=next.next;
                return head;
            }
            else if(node==head){
                return null;
            }
            else{
                ListNode parent=head;
                while(parent.next!=node){
                    parent=parent.next;
                }
                parent.next=null;
                return head;
            }
        }
    }
}
