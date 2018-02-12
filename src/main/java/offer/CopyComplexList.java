package offer;

/**
 * Created by George on 2018/1/8.
 */

class ComplexListNode{
    int value;
    ComplexListNode next;
    ComplexListNode sibLing; //可以为null或者指向任何节点
}

public class CopyComplexList { //采用分治法，分治法的思想是将复杂问题简单化，即将问题分解成许多子问题，每个子问题用一个函数来描述其功能
    public ComplexListNode copy(ComplexListNode head){
        if(head==null || head.next==null){
            return head;
        }
        else{
            link(head);
            sibling(head);
            return divide(head);
        }
    }

    public void link(ComplexListNode head){//连接原链表和新链表
        ComplexListNode pNode=head;
        while(pNode!=null){
            ComplexListNode cloneNode=new ComplexListNode();
            cloneNode.value=pNode.value;
            cloneNode.sibLing=null;
            cloneNode.next=pNode.next;
            pNode.next=cloneNode;

            pNode=cloneNode.next;
        }
    }

    public void sibling(ComplexListNode head){ //sibling连接
        ComplexListNode pNode=head;
        while(pNode!=null){
            if(pNode.sibLing!=null){
                pNode.next.sibLing=pNode.sibLing.next;
            }

            pNode=pNode.next.next;
        }
    }

    public ComplexListNode divide(ComplexListNode head){//分解成两个链表
        ComplexListNode pNode=head;
        ComplexListNode copyHead=null;
        ComplexListNode copyNode=null;
        if(pNode!=null){
            copyHead=copyNode=pNode.next;
            pNode.next=copyNode.next;
            pNode=pNode.next;
        }

        while(pNode!=null){
            copyNode.next=pNode.next;
            copyNode=copyNode.next;
            pNode.next=copyNode.next;
            pNode=copyNode.next;
        }

        return copyHead;
    }
}
