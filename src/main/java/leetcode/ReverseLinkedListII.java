package leetcode;

import java.util.ArrayList;

/**
 * Created by George on 2017/12/18.
 */
public class ReverseLinkedListII {
    public ListNode reverseBetween(ListNode head, int m, int n) { //1 ≤ m ≤ n ≤ length of list
        if(n==m || head==null){
            return head;
        }
        else{//颠倒从第m个元素到第n个元素
            ArrayList<ListNode> nodes=new ArrayList<ListNode>();
            ListNode p=head;
            while(p!=null){
                nodes.add(p);
                p=p.next;
            }
            int low=m-1;
            int high=n-1;
            while(low<=high){
                int tmp;
                tmp=nodes.get(high).val;
                nodes.get(high).val=nodes.get(low).val;
                nodes.get(low).val=tmp;
                low++;
                high--;
            }
            return head;
        }
    }
}
