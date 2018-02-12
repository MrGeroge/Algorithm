package com.george.math;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by George on 2017/9/26.
 * 一.二项树的基本性质：
 * 1.Bk二项堆的节点总数为2的k次幂
 * 2.Bk二项堆的高度为k
 * 3.Bk二项堆的根节点度数为k
 * 4.Bk二项堆深度为i处时恰有(k,i)个节点
 *
 * 二项堆由多个满足条件的二项树组成
 * 1.每个二项树需满足最小堆性质：即节点关键字大于或者等于其父节点的关键字
 * 2.对于任意非负整数k，在H中至多有颗二项树的根具有度数k
 * 如果x为根，则silbling[x]指向下一个根(二项堆度数严格递增)
 * 如果x不为根，则silbing[x]指向下一个节点,head[H]指向根表的第一个根节点
 * 二项堆中每一种k的二项树只能存在一个，能够合并相同k的二项树得到更大k的二项树
 */
public class TwoItemHeap {
    TwoItemNode head; //头节点
    public static void main(String[] args){
        TwoItemHeap heap1=new TwoItemHeap();
        heap1.makeBinomialHeap(); //创建二项堆head1
        TwoItemHeap heap2=new TwoItemHeap();
        heap2.makeBinomialHeap();//创建二项堆head2

        TwoItemNode node0=new TwoItemNode();
        TwoItemNode node1=new TwoItemNode();
        TwoItemNode node2=new TwoItemNode();
        TwoItemNode node3=new TwoItemNode();
        TwoItemNode node4=new TwoItemNode();
        TwoItemNode node5=new TwoItemNode();
        TwoItemNode node6=new TwoItemNode();


        heap1.head.setSibling(node0);

        node0.setDegree(0);
        node0.setChild(null);
        node0.setKey(12);
        node0.setParent(null);//待设置兄弟
        node0.setSibling(node1);


        node1.setParent(null);
        node1.setKey(7);
        node1.setDegree(1);//待设置兄弟和儿子
        node1.setSibling(node3);
        node1.setChild(node2);


        node2.setDegree(0);
        node2.setSibling(null);
        node2.setKey(25);
        node2.setChild(null);
        node2.setParent(node1);


        node3.setKey(15);
        node3.setParent(null);
        node3.setDegree(2);
        node3.setSibling(null);
        node3.setChild(node4);


        node4.setKey(28);
        node4.setDegree(1);
        node4.setParent(node3); //node4设置兄弟和儿子
        node4.setChild(node5);
        node4.setSibling(node6);


        node5.setKey(41);
        node5.setParent(node4);
        node5.setSibling(null);
        node5.setChild(null);
        node5.setDegree(0);


        node6.setChild(null);
        node6.setDegree(0);
        node6.setSibling(null);
        node6.setParent(node3);
        node6.setKey(33);


        //构建二项堆heap2
        node0=new TwoItemNode();
        node1=new TwoItemNode();
        node2=new TwoItemNode();
        node3=new TwoItemNode();
        node4=new TwoItemNode();
        node5=new TwoItemNode();
        node6=new TwoItemNode();
        TwoItemNode node7=new TwoItemNode();
        TwoItemNode node8=new TwoItemNode();
        TwoItemNode node9=new TwoItemNode();
        TwoItemNode node10=new TwoItemNode();
        TwoItemNode node11=new TwoItemNode();
        TwoItemNode node12=new TwoItemNode();
        TwoItemNode node13=new TwoItemNode();
        TwoItemNode node14=new TwoItemNode();
        TwoItemNode node15=new TwoItemNode();
        TwoItemNode node16=new TwoItemNode();
        TwoItemNode node17=new TwoItemNode();
        TwoItemNode node18=new TwoItemNode();


        heap2.head.setSibling(node0);
        node0.setChild(null);
        node0.setSibling(node1);
        node0.setKey(18);
        node0.setDegree(0);
        node0.setParent(null);

        node1.setChild(node2);
        node1.setSibling(node3);
        node1.setKey(3);
        node1.setParent(null);
        node1.setDegree(1);

        node2.setChild(null);
        node2.setParent(node1);
        node2.setSibling(null);
        node2.setKey(37);
        node2.setDegree(0);

        node3.setParent(null);
        node3.setChild(node4);
        node3.setSibling(null);
        node3.setDegree(4);
        node3.setKey(6);

        node4.setChild(node8);
        node4.setParent(node3);
        node4.setSibling(node5);
        node4.setDegree(3);
        node4.setKey(8);

        node5.setChild(node11);
        node5.setParent(node3);
        node5.setSibling(node6);
        node5.setKey(29);
        node5.setDegree(2);

        node6.setChild(node13);
        node6.setSibling(node7);
        node6.setParent(node3);
        node6.setDegree(1);
        node6.setKey(10);

        node7.setChild(null);
        node7.setSibling(null);
        node7.setParent(node3);
        node7.setDegree(0);
        node7.setKey(44);

        node8.setChild(node14);
        node8.setSibling(node9);
        node8.setParent(node4);
        node8.setKey(30);
        node8.setDegree(2);

        node9.setChild(node16);
        node9.setSibling(node10);
        node9.setParent(node4);
        node9.setKey(23);
        node9.setDegree(1);

        node10.setChild(null);
        node10.setSibling(null);
        node10.setParent(node4);
        node10.setKey(22);
        node10.setDegree(0);

        node11.setChild(node17);
        node11.setSibling(node12);
        node11.setParent(node5);
        node11.setDegree(1);
        node11.setKey(48);

        node12.setChild(null);
        node12.setSibling(null);
        node12.setParent(node5);
        node12.setKey(31);
        node12.setDegree(0);

        node13.setChild(null);
        node13.setSibling(null);
        node13.setParent(node6);
        node13.setKey(17);
        node13.setDegree(0);

        node14.setChild(node18);
        node14.setSibling(node15);
        node14.setParent(node8);
        node14.setKey(45);
        node14.setDegree(1);

        node15.setChild(null);
        node15.setSibling(null);
        node15.setParent(node8);
        node15.setKey(32);
        node15.setDegree(0);

        node16.setChild(null);
        node16.setSibling(null);
        node16.setParent(node9);
        node16.setKey(24);
        node16.setDegree(0);

        node17.setChild(null);
        node17.setSibling(null);
        node17.setParent(node11);
        node17.setKey(50);
        node17.setDegree(0);

        node18.setChild(null);
        node18.setSibling(null);
        node18.setParent(node14);
        node18.setKey(55);
        node18.setDegree(0);

        //heap1和heap2构建完毕
        TwoItemNode heap1MinNode=heap1.binomialHeapMinimum();
        System.out.println("heap1 min="+heap1MinNode.getKey());
        TwoItemNode heap2MinNode=heap2.binomialHeapMinimum();
        System.out.println("heap2 min="+heap2MinNode.getKey());

        TwoItemNode head3=TwoItemHeap.binomiaHeapUnion(heap1.head,heap2.head);
        TwoItemHeap heap3=new TwoItemHeap();
        heap3.head=head3;
        TwoItemNode x=heap3.binomialHeapExtractMin();

        /*TwoItemNode node=new TwoItemNode();
        node.setKey(1);
        head3=heap3.insertTwoItemNode(node);
        heap3.head=head3;
        node=new TwoItemNode();
        node.setKey(2);
        head3=heap3.insertTwoItemNode(node);
        return;*/







    }
    public void makeBinomialHeap(){
        this.head=new TwoItemNode();
    }

    public TwoItemNode binomialHeapMinimum(){ //寻找二项堆中关键字最小的节点
        TwoItemNode y=new TwoItemNode();
        TwoItemNode x=this.head;
        int min=Integer.MAX_VALUE;
        while(x!=null){
            if(x.getKey()<min){
                y=x;
                min=x.getKey();
            }

            x=x.getSibling();
        }
       return y;
    }

    /**
     *
     * @param
     * @param
     * @return
     * 二项堆合并分为两步
     * 1.执行binomialHeapMerge()将二项堆H1和H2合并成一个根表，保证按照二项树根节点度数递增排列
     * 2.一次遍历将二项树根度数相同的连接成更大的二项树,直到每个度数至多只有一个根为止
     */
    /*public TwoItemNode binomialHeapUnion(TwoItemNode head1,TwoItemNode head2){ //两个二项堆合并

    }*/

    public static void linkTwoItemTree(TwoItemNode child,TwoItemNode root){ //合并root和child
        child.setParent(root);
        child.setSibling(root.getChild());
        root.setChild(child);
        root.setDegree(root.getDegree()+1);
    }

    public static TwoItemNode binomiaHeapUnion(TwoItemNode head1,TwoItemNode head2) {//联合堆

        TwoItemNode head3=binomialHeapMerge(head1,head2);
        TwoItemNode prev=null;
        TwoItemNode x=head3.getSibling();
        TwoItemNode next=x.getSibling();
        while(next!=null){
            if(x.getDegree()!=next.getDegree() || (next.getSibling()!=null && next.getSibling().getDegree()==x.getDegree())){
                prev=x;
                x=next;
            }
            else if(x.getKey()<=next.getKey()){ //当前节点的关键字小于next的关键字
                x.setSibling(next.getSibling());
                linkTwoItemTree(next,x);
            }
            else if(prev==null){
                head3=next;
            }
            else{
                prev.setSibling(next);
                linkTwoItemTree(x,next);
                x=next;
            }
            next=x.getSibling();
        }
        return head3;
    }

    public static TwoItemNode binomialHeapMerge(TwoItemNode head1,TwoItemNode head2){//类似于有序链表合并，按照degree来排序
        TwoItemNode x=head1.getSibling();
        TwoItemNode y=head2.getSibling();
        TwoItemNode head3=new TwoItemNode();
        TwoItemNode z=head3;
        while(x!=null && y!=null){
            if(x.getDegree()<=y.getDegree()){
                z.setSibling(x);
                z=z.getSibling();
                x=x.getSibling();
            }
            else{
                z.setSibling(y);
                z=z.getSibling();
                y=y.getSibling();
            }
        }
        if(x!=null){ //说明y遍历完了，直接插入x
                z.setSibling(x);
        }
        if(y!=null){
                z.setSibling(y);
        }
        return head3;
    }

    public TwoItemNode insertTwoItemNode(TwoItemNode node){ //向堆中插入节点node
        TwoItemHeap heap=new TwoItemHeap();
        heap.makeBinomialHeap(); //创建二项堆head1
        node.setParent(null);
        node.setDegree(0);
        node.setChild(null);
        node.setSibling(null);
        heap.head.setSibling(node);
        TwoItemNode head=binomiaHeapUnion(this.head,heap.head);
        return head;
    }

    public TwoItemNode binomialHeapExtractMin(){ //从该二项堆中抽取出最小关键字的节点
        TwoItemNode minNode=binomialHeapMinimum(); //该二项堆中最小关键字节点
        TwoItemNode prev=null;
        TwoItemNode x=this.head.getSibling();
            while(x!=null){
                if(x.getSibling()==minNode){
                    prev=x;
                    break;
                }
                else{
                    x=x.getSibling();
                }
            }
            prev.setSibling(minNode.getSibling()); //删除minNode节点

        List<TwoItemNode> list=new ArrayList<TwoItemNode>();
        TwoItemNode child=minNode.getChild();
        while(child!=null){
            list.add(child);
            child=child.getSibling();
        }
        Collections.sort(list); //升序排列
        TwoItemNode head=new TwoItemNode(); //创建新的二项堆
        TwoItemNode z=head;
        for(TwoItemNode t:list){
            z.setSibling(t);
            z=z.getSibling();
        } //逆序构建新的二项堆
        z.setSibling(null);
        this.head=binomiaHeapUnion(this.head,head);
        return minNode;
        }

    public void binomialHeapDecreaseKey(TwoItemNode x,int k){ //将二项堆中的节点x的关键字降低到k
        x.setKey(k);
        TwoItemNode y=x;
        TwoItemNode z=y.getParent(); //不断与父节点比较key，保证父节点更小，从而保证最小堆性质
        while(z!=null && y.getKey()<z.getKey()){
            int tmp=z.getKey();
            z.setKey(y.getKey());
            y.setKey(tmp);
            y=z;
            z=z.getParent();
        }
    }
    public void binomialHeapDelete(TwoItemNode x){ //从二项堆中删除x
        binomialHeapDecreaseKey(x,Integer.MIN_VALUE); //将x设置为最小
        binomialHeapExtractMin();//将最小的节点删除即x删除
    }
    }



class TwoItemNode implements Comparable<TwoItemNode>{ //二项节点
    private int key; //关键字
    private TwoItemNode child; //最左儿子
    private TwoItemNode sibling;//相邻右兄弟
    private TwoItemNode parent;//父节点
    private int degree; //子女个数

    public TwoItemNode(){
        this.key=Integer.MAX_VALUE;
        this.child=null;
        this.sibling=null;
        this.parent=null;
        this.degree=0;
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public TwoItemNode getChild() {
        return child;
    }

    public void setChild(TwoItemNode child) {
        this.child = child;
    }

    public TwoItemNode getSibling() {
        return sibling;
    }

    public void setSibling(TwoItemNode sibling) {
        this.sibling = sibling;
    }

    public TwoItemNode getParent() {
        return parent;
    }

    public void setParent(TwoItemNode parent) {
        this.parent = parent;
    }

    public int getDegree() {
        return degree;
    }

    public void setDegree(int degree) {
        this.degree = degree;
    }

    public int compareTo(TwoItemNode o) {
        if(this.getDegree()<o.getDegree()){
            return -1;
        }
        else if(this.getDegree()>o.getDegree()){
            return 1;
        }
        else{
            return 0;
        }
    }
}
