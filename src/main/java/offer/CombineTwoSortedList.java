package offer;

/**
 * Created by George on 2018/1/7.
 */
public class CombineTwoSortedList { //合并两有序链表
    public static void main(String[] args){
        CombineTwoSortedList ct=new CombineTwoSortedList();
        ListNode head1=new ListNode(1);
        ListNode head2=new ListNode(2);
        ListNode node1=new ListNode(3);
        ListNode node2=new ListNode(4);
        head1.next=node1;
        head2.next=node2;
        ListNode head3=ct.sorted(head1,head2);
        return;
    }
    public ListNode sorted(ListNode head1,ListNode head2){
        if(head1==null){
            return head2;
        }
        else if(head2==null){
            return head1;
        }
        else{
            ListNode head3=null;
            if(head1.val<head2.val){
               head3=head1;
                head3.next=sorted(head1.next,head2);
            }
            else{
                head3=head2;
                head3.next=sorted(head1,head2.next);
            }
            return head3;
        }
    }
}
