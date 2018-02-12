package com.george.math;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by George on 2017/9/28.
 * 斐波拉契堆有多个无序二项树Uk，每个Uk包含两个Uk-1的无序二项子树，对于无序二项树UK的根节点度为k
 */
public class FiboracciHeap { //斐波拉契堆,有根无序，访问永远从min[H]根开始，即最小关键字的节点
    private FiboracciTreeNode min; //根访问入口，最小关键字节点
    private int nodeNum; //斐波拉契堆包含多少个节点

    public FiboracciTreeNode getMin() {
        return min;
    }

    public void setMin(FiboracciTreeNode min) {
        this.min = min;
    }

    public int getNodeNum() {
        return nodeNum;
    }

    public void setNodeNum(int nodeNum) {
        this.nodeNum = nodeNum;
    }

    public void makeFiboracciHeap(){ //初始化斐波拉契堆
        min=null;
        nodeNum=0;
    }

    public void fiboracciHeapInsert(FiboracciTreeNode node){ //向该斐波拉契堆中插入node
        node.setDegree(0);
        node.setParent(null);
        node.setChild(null);
        node.setLeft(null);
        node.setRight(null);
        node.setMark(false);

        //将node加入到根节点链表中,即插入到min右边
        addFiboracciTreeNodeToRoot(node);

        nodeNum++;
    }

    public void addFiboracciTreeNodeToRoot(FiboracciTreeNode node){
        if(min==null){
            min=node;
        }
        else{
            node.setRight(min.getRight());
            node.setLeft(min);
            if(min.getRight()!=null){
                min.getRight().setLeft(node);
            }
            min.setRight(node);
            if(node.getKey()<min.getKey()){
                min=node; //新的最小关键字节点作为斐波拉契堆的根
            }
        }

    }

    public FiboracciTreeNode searchFiboracciHeapMin(){
        return min;
    }

    public FiboracciHeap fiboracciHeap(FiboracciHeap heap1,FiboracciHeap heap2){ //两个斐波拉契堆合并,仅仅简单的将两根表并置，然后确定新的最小节点
        FiboracciHeap heap3=new FiboracciHeap();
        heap3.makeFiboracciHeap();
        heap3.setMin(heap1.getMin()); //设置heap3的最小根节点min为heap1的最小根节点

        //将heap2插入根表
        if(heap3.getMin()==null){
            heap3.setMin(heap2.getMin());
        }
        else{
            heap2.getMin().setRight(heap3.getMin().getRight());
            heap2.getMin().setLeft(heap3.getMin());
            if(heap3.getMin().getRight()!=null){
                heap3.getMin().getRight().setLeft(heap2.getMin());
            }
            heap3.getMin().setRight(heap2.getMin());
            if(heap2.getMin().getKey()<heap3.getMin().getKey()){
                heap3.setMin(heap2.getMin());
            }
        }
        if(heap1.getMin()==null || (heap2.getMin()!=null && heap2.getMin().getKey()<heap1.getMin().getKey())){
            heap3.setMin(heap2.getMin());
        }
        heap3.setNodeNum(heap1.getNodeNum()+heap2.getNodeNum());
        return heap3;
    }

    public  FiboracciTreeNode fiboracciHeapExtractMin(){ //从该斐波拉契堆中找到最小关键字节点，并删除
        FiboracciTreeNode z=min;
        if(z!=null){
            FiboracciTreeNode child=z.getChild();
            while(child!=null){
                addFiboracciTreeNodeToRoot(child); //将z的每个孩子添加到根表
                child.setParent(null);
                child=child.getRight();
            }
            //从根表中删除z
            z.getLeft().setRight(z.getRight());
            z.getRight().setLeft(z.getLeft());

            if(z==z.getRight()){
                min=null;
            }
            else{
                min=z.getRight();
                consolidate();
            }
            nodeNum--;
        }
        return z;
    }



    /*public  FiboracciTreeNode fiboracciHeapExtractMin(){
    FiboracciTreeNode pf_node=min;
        FiboracciTreeNode node,nodepa_ri,node_right;
        if(pf_node!=null){
            node=pf_node.getChild();//指向最小结点的第一个子结点
            nodepa_ri=pf_node.getRight();//指向最小结点的右边的结点
            if(node!=null){//如果子结点存在
                node_right=node.getLeft();//子结点双链表中的最后一个结点
                node_right.setRight(nodepa_ri);
                nodepa_ri.setLeft(node_right);
                pf_node.setRight(node);
                node.setLeft(pf_node);;
                //将新的根结点的父结点置空
                FiboracciTreeNode ptmp=node;
                while(ptmp!=nodepa_ri){
                    ptmp.setParent(null);
                    ptmp=ptmp.getRight();
                }
                //将pf_node结点的父结点更新
                nodepa_ri=node;
            }
            pf_node.getLeft().setRight(pf_node.getRight());
            pf_node.getRight().setLeft(pf_node.getLeft());


            if(pf_node==pf_node.getRight()){
                 nodeNum--;
                 min=null;
            }else{
                nodeNum--;
                min=pf_node.getRight();
                consolidate();
            }
        }
        return pf_node;
    }*/



    public void consolidate() {
        FiboracciTreeNode[] A=new FiboracciTreeNode[4]; //下标对应不同根点的degree
        for(int i=0;i<4;i++){ //4为根表中根节点出现的最大度数
            A[i]=null;
        }
        FiboracciTreeNode w=min;
        while(w!=min){ //沿根表向右遍历
            FiboracciTreeNode x=w;
            int d=x.getDegree();
            while(A[d]!=null){
                FiboracciTreeNode y=A[d]; //之前保存的和x度数相同的节点
                if(x.getKey()>y.getKey()){ //当前节点的key大于之前保存节点的key
                    FiboracciTreeNode tmp;
                    tmp=y;
                    y=x;
                    x=tmp;
                }
                fiboracciHeapLink(y,x);
                A[d]=null;
                d++;
            }
            A[d]=x;//x的degree增加了，所以A[d]改变了
            w=w.getRight();
        }
        min=null;
        for(int i=0;i<4;i++){
            if(A[i]!=null){
                addFiboracciTreeNodeToRoot(A[i]);
                if(min==null || A[i].getKey()<min.getKey()){
                    min=A[i];
                }
            }
        }
    }

    public void fiboracciHeapLink(FiboracciTreeNode child,FiboracciTreeNode root){
        //从根表中删除child
        child.getLeft().setRight(child.getRight());
        child.getRight().setLeft(child.getLeft());

        child.setParent(root);
        child.setRight(root.getChild());
        root.getChild().setLeft(child);
        child.setLeft(root.getChild().getLeft());
        root.getChild().getLeft().setLeft(child);

        root.setChild(child);

        root.setDegree(root.getDegree()+1);

        child.setMark(false);
    }

    public void fiboracciHeapDecreaseKey(FiboracciTreeNode x,int k){ //降低x的k值
         FiboracciTreeNode y=x.getParent();
        x.setKey(k);
        while(y!=null || x.getKey()<y.getKey()){
            //Cut x
            cut(x,y);
            cascading_cut(y);

        }
        if(x.getKey()<min.getKey()){
            min=x;
        }
    }

    public void cut(FiboracciTreeNode x,FiboracciTreeNode y){ //将x从y的子女表中删除，并把x加入根表,
        //删除x
        x.getLeft().setRight(x.getRight());
        x.getRight().setLeft(x.getLeft());
        y.setDegree(y.getDegree()-1);
        if(x==y.getChild()){
            y.setChild(x.getRight());
        }
        addFiboracciTreeNodeToRoot(x);
        x.setParent(null);
        x.setMark(false);
    }

    public void cascading_cut(FiboracciTreeNode y){ //级联删除
        FiboracciTreeNode z=y.getParent();
        if(z!=null){
            if(y.isMark()==false){
                y.setMark(true);
            }
            else{
               cut(y,z);
                cascading_cut(z);
            }
        }

    }

    public void fiboracciHeapDelete(FiboracciTreeNode x){ //删除节点x
        fiboracciHeapDecreaseKey(x,Integer.MIN_VALUE);
        fiboracciHeapExtractMin();
    }

}
class FiboracciTreeNode{
    private FiboracciTreeNode parent; //父节点
    private FiboracciTreeNode child; //子节点
    private FiboracciTreeNode left; //左兄弟
    private FiboracciTreeNode right; //右兄弟
    private int degree;  //包含多少个孩纸
    private int key; //关键字
    private boolean mark; //当前节点自从成为另一个节点子女以来是否失掉了一个孩子

    public FiboracciTreeNode(){
        parent=null;
        child=null;
        left=null;
        right=null;
        degree=0;
        key=Integer.MAX_VALUE;
        mark=true;
    }
    public FiboracciTreeNode getParent() {
        return parent;
    }

    public void setParent(FiboracciTreeNode parent) {
        this.parent = parent;
    }

    public FiboracciTreeNode getChild() {
        return child;
    }

    public void setChild(FiboracciTreeNode child) {
        this.child = child;
    }

    public FiboracciTreeNode getLeft() {
        return left;
    }

    public void setLeft(FiboracciTreeNode left) {
        this.left = left;
    }

    public FiboracciTreeNode getRight() {
        return right;
    }

    public void setRight(FiboracciTreeNode right) {
        this.right = right;
    }

    public int getDegree() {
        return degree;
    }

    public void setDegree(int degree) {
        this.degree = degree;
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public boolean isMark() {
        return mark;
    }

    public void setMark(boolean mark) {
        this.mark = mark;
    }
}