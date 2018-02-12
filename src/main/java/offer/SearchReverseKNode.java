package offer;

/**
 * Created by George on 2018/1/7.
 */
public class SearchReverseKNode {
    public static void main(String[] args){

    }
    public ListNode searchK(ListNode head,int k){
        if(head==null || k<=0){
            return null;
        }
        else{
            ListNode p=head;
            int count=0;
            while(count<k-1){
                if(p.next!=null){
                    p=p.next;
                    count++;
                }
                else{
                    return null;
                }
            }
                ListNode q=head;
                while(p.next!=null){
                    p=p.next;
                    q=q.next;
                }
                return q;

        }
    }
}
