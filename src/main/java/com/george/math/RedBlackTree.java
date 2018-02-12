package com.george.math;

/**
 * Created by George on 2017/9/15.
 *
 * 1.红黑树是常见的二叉查找树，动态集合操作时间复杂度为O(lgn)
 *
 * 2.对任何一条从根到叶子的路径上各个节点着色方式的限制，红黑树可以保证没有一条路径比其他路径长出两倍
 *
 * 3.红黑树需要满足的性质：
 * 1)每个节点要么为黑色，要么为红色
 * 2)根节点为黑色
 * 3)每个叶节点(Nil)是黑色
 * 4)如果一个节点为红色，那么其两个儿子为黑色
 * 5)对于每个节点，从该节点到其子孙节点的所有路径上包含相同数目的黑节点
 *
 *
 * 4.黑高度：任意节点x出发到一个叶节点的任意一条路径上，黑节点的个数为x的黑高度bh(x)
 *
 * 5.使用nil[T]代表所有Nil--所有的叶子节点和根部的父节点
 *
 * 6.对一个基本数据结构的扩张分为四步：
 * 1)选择基本数据结构
 * 2）确定添加哪些信息
 * 3）验证可用基本数据结构上的基本修改操作来维护新添加的信息
 * 4）设计新的操作
 */

public class RedBlackTree {//带选择功能的红黑树
    public RedBlackNode root; //红黑树对应的根节点

    public RedBlackTree(){ //构造红黑树
        this.root=new RedBlackNode();
    }

    public void leftRotate(RedBlackNode node){//左旋,根节点的右子树称为新的根节点，原节点称为新节点的左节点，原节点的右儿子为新根节点原来的左儿子,时间复杂度为O(1)
        RedBlackNode rightChild=node.rightChild;
        node.rightChild=rightChild.leftChild;
        if(rightChild.leftChild!=null){
            rightChild.leftChild.parent=node;
        }
        rightChild.parent=node.parent;
        if(node.parent==null){//node为红黑树的根
            this.root=rightChild;
        }
        else if(node==node.parent.leftChild){//node如果是其根的左子树
            node.parent.leftChild=rightChild;
        }
        else{
            node.parent.rightChild=rightChild;
        }
        rightChild.leftChild=node;
        node.parent=rightChild;
    }

    public void rightRotate(RedBlackNode node){//右旋,根节点的左子树称为新的根节点，原节点称为新节点的右节点，原节点的左儿子为新根节点原来的右儿子,时间复杂度为O(1)
        RedBlackNode leftChild=node.leftChild;
        node.leftChild=leftChild.rightChild;
        if(leftChild.rightChild!=null){
            leftChild.rightChild.parent=node;
        }
        leftChild.parent=node.parent;
        if(node.parent==null){
            this.root=leftChild;
        }
        else if(node==node.parent.leftChild){
            node.parent.leftChild=leftChild;
        }
        else{
            node.parent.rightChild=leftChild;
        }
        leftChild.rightChild=node;
        node.parent=leftChild;
    }

    public void rbTreeInsert(RedBlackNode node){//向红黑树中插入节点node，正常插入并将node着色为红色，除此之外利用副主函数对节点重新着色并旋转，时间复杂度为O(lgn)
        RedBlackNode parent=null;//保存待插入位置的父节点
        RedBlackNode tmp=this.root;
        while(tmp!=null){//parent保存待插入位置的父节点
            parent=tmp;
            if(node.value<tmp.value){
                tmp=tmp.leftChild;
            }
            else{
                tmp=tmp.rightChild;
            }
        }
        node.parent=parent;
        if(parent==null){
            root=node;
        }
        else if(node.value<parent.value){
            parent.leftChild=node;
        }
        else{
            parent.rightChild=node;
        }
        node.leftChild=null;
        node.rightChild=null;
        node.isRed=true;
        //至此二叉树插入工作结束，开始颜色修复
        insertFixup(node);
    }

    /**
     *
     * 1.while循环的不变式
     * 1）节点node为红色
     * 2）节点node.parent是根root，则node.parent是黑色
     * 3）若红黑性质破怀，则至多一个性质破怀，可能是（2）node是根；（4）node.parent为红色
     *
     * 2.修复过程中一共存在三种情况
     * case 1:p[z]为红色，p[z]的兄弟y也为红色，则p[p[z]]肯定为黑色,为了保证性质(5),则将p[z]和y均置为黑色，p[p[z]]置为红色，然后将p[p[z]]赋值给z以完成向上循环
     * case 2:y是黑色，z是右儿子
     * case 3:y是黑色，z是左儿子
     */
    public void insertFixup(RedBlackNode node){ //插入节点node可能破坏红性质（2）根节点只能是黑色,(4)红色节点的儿子们只能是红色
        while(node.parent.isRed==true){
            if(node.parent==node.parent.parent.leftChild){//node.parent是node.parent.parent的左儿子
                RedBlackNode tmp=node.parent.parent.rightChild; //tmp为node.parent.parent的右儿子
                if(tmp.isRed==true){//若右儿子是红色，且左儿子也是红色
                    node.parent.isRed=false;
                    tmp.isRed=false;
                    node.parent.parent.isRed=true;
                    node=node.parent.parent;
                }
                else if(node==node.parent.rightChild){//若node是node.parent的右儿子
                    node=node.parent;
                    leftRotate(node);  //case 2->case 3，即node->node.parent的左儿子
                    node.parent.isRed=false;
                    node.parent.parent.isRed=true;
                    rightRotate(node.parent.parent);
                }
                    node.parent.isRed=false;
                    node.parent.parent.isRed=true;
                    rightRotate(node.parent.parent);//p[z]为黑色（子树根）,z为红色(左儿子),p[p[z]]为红色(右儿子)
            }
            else{
                System.out.println("正好相反");
            }
        }
        this.root.isRed=false; //根节点置为黑色
    }

    public RedBlackNode searchMin(RedBlackNode subTree){ //寻找子树中的最小元素
        if(subTree==null){
            return null;
        }
        else{
            while(subTree.leftChild!=null){
                subTree=subTree.leftChild;
            }
        }
        return subTree;
    }

    public RedBlackNode searchNext(RedBlackNode node){ //寻找node的后继节点
        RedBlackNode tmp=null;
        if(node.rightChild!=null){ //右子树中的最小元素即为后继节点
            tmp=searchMin(node.rightChild);//tmp即为后继节点
            return tmp;
        }
        else{
            RedBlackNode parent=node.parent;
            while(parent!=null && node==parent.rightChild){
                node=parent;
                parent=parent.parent;
            }
            return parent;
        }

    }

    public RedBlackNode rbTreeDelete(RedBlackNode node){
        RedBlackNode tmp;
        if(node.leftChild==null || node.rightChild==null){ //node至多一个孩子
            tmp=node;
        }
        else{
            tmp=searchNext(node); //待删除节点为node的后继节点
        }
        RedBlackNode child;
        if(tmp.leftChild!=null){
            child=tmp.leftChild;
        }
        else{
            child=tmp.rightChild;
        }
        child.parent=tmp.parent;
        if(tmp.parent==null){
            root=child;
        }
        else if(tmp.parent.leftChild==tmp){
            tmp.parent.leftChild=child;
        }
        else{
            tmp.parent.rightChild=child;
        }
        if(tmp!=node){
            node.value=tmp.value;
        }
        if(tmp.isRed==false){ //已删除节点为黑色
            deleteFixup(child);
        }
        return tmp;
    }


    public RedBlackNode searchMinI(RedBlackNode subTree,int i){ //寻找子树subTree中第i小的节点
        int j=subTree.leftChild.size+1;
        if(i==j){
            return subTree;
        }
        else if(i<j){
            return searchMinI(subTree.leftChild,i);
        }
        else{
            return searchMinI(subTree.rightChild,i-j);
        }
    }

    public int searchInOrderLocation(RedBlackNode x){ //寻找节点x在中序遍历中的位置r
        int r=x.leftChild.size+1; //有r个节点在x之前
        RedBlackNode y=x;
        while(y!=root){
            if(y.parent.rightChild==y){
                r=y.leftChild.size+1+r;
            }
            y=y.parent;
        }
        return r;
    }

    /**
     * 1.如果已删除节点y为黑色，则会产生三个问题
     * case 1：y为根节点，y的一个红色孩子成为新的根，违反了性质（2）
     * case 2：如果p[child]与child均为红色，则违反了性质（4）
     * case 3：如果删除节点y，则包含y的所有路径上黑节点的个数少1，则违反了性质（5）
     *
     *
     */
    public void deleteFixup(RedBlackNode node){ //删除修复红黑性质（1）（2）（4）
        while(node!=root && node.isRed==false){
            if(node==node.parent.leftChild){
                RedBlackNode brother=node.parent.rightChild; //brother为右儿子
                if(brother.isRed==true){
                    brother.isRed=false;
                    node.parent.isRed=true; //红黑黑
                    leftRotate(node.parent);
                    brother=node.parent.rightChild;//brother总是指向node的右兄弟
                }
                if(brother.leftChild.isRed==false && brother.rightChild.isRed==false){
                    brother.isRed=true;
                    node=node.parent; //向上循环
                }
                else if(brother.rightChild.isRed==false){
                    brother.leftChild.isRed=false;
                    brother.isRed=true;
                    rightRotate(brother);//使得w为黑色，且w的右儿子是红色
                    brother=node.parent.rightChild; //brother总是指向node的右兄弟
                }
                    brother.isRed=node.parent.isRed; //brother的颜色保存p[node]的颜色
                    node.parent.isRed=false;
                    brother.rightChild.isRed=false; //brother右儿子颜色为黑色
                    leftRotate(node.parent);
                    node=root; //循环终止
            }
            else{
                System.out.println("正好相反");
            }
        }
        node.isRed=false;
    }

    public void CreateRedBlackTree(){//创建红黑树
        root=new RedBlackNode(26,false);
        RedBlackNode node17=new RedBlackNode(17,true);
        RedBlackNode node41=new RedBlackNode(41,false);
        RedBlackNode node14=new RedBlackNode(14,false);
        RedBlackNode node21=new RedBlackNode(21,false);
        RedBlackNode node30=new RedBlackNode(30,true);
        RedBlackNode node47=new RedBlackNode(47,false);
        RedBlackNode node10=new RedBlackNode(10,true);
        RedBlackNode node16=new RedBlackNode(16,false);
        RedBlackNode node19=new RedBlackNode(19,false);
        RedBlackNode node23=new RedBlackNode(23,false);
        RedBlackNode node28=new RedBlackNode(28,false);
        RedBlackNode node38=new RedBlackNode(38,false);
        RedBlackNode node7=new RedBlackNode(7,false);
        RedBlackNode node12=new RedBlackNode(12,false);
        RedBlackNode node15=new RedBlackNode(15,true);
        RedBlackNode node20=new RedBlackNode(20,true);
        RedBlackNode node35=new RedBlackNode(35,true);
        RedBlackNode node39=new RedBlackNode(39,true);
        RedBlackNode node3=new RedBlackNode(3,true);
        root.parent=null;
        root.leftChild=node17;
        root.rightChild=node41;
        root.size=20;
        node17.parent=root;
        node17.leftChild=node14;
        node17.rightChild=node21;
        node17.size=12;
        node41.parent=root;
        node41.leftChild=node30;
        node41.rightChild=node47;
        node41.size=7;
        node14.parent=node17;
        node14.leftChild=node10;
        node14.rightChild=node16;
        node14.size=7;
        node21.parent=node17;
        node21.leftChild=node19;
        node21.rightChild=node23;
        node21.size=4;
        node30.parent=node41;
        node30.leftChild=node28;
        node30.rightChild=node38;
        node30.size=5;
        node47.parent=node41;
        node47.leftChild=null;
        node47.rightChild=null;
        node47.size=1;
        node10.parent=node14;
        node10.leftChild=node7;
        node10.rightChild=node12;
        node10.size=4;
        node16.parent=node14;
        node16.leftChild=node15;
        node16.rightChild=null;
        node16.size=2;
        node19.parent=node21;
        node19.leftChild=null;
        node19.rightChild=node20;
        node19.size=2;
        node23.parent=node21;
        node23.leftChild=null;
        node23.rightChild=null;
        node23.size=1;
        node28.parent=node30;
        node28.leftChild=null;
        node28.rightChild=null;
        node28.size=1;
        node38.parent=node30;
        node38.leftChild=node35;
        node38.rightChild=node39;
        node38.size=3;
        node7.parent=node10;
        node7.leftChild=node3;
        node7.rightChild=null;
        node7.size=2;
        node12.parent=node10;
        node12.leftChild=null;
        node12.rightChild=null;
        node12.size=1;
        node15.parent=node16;
        node15.leftChild=null;
        node15.rightChild=null;
        node15.size=1;
        node20.parent=node19;
        node20.leftChild=null;
        node20.rightChild=null;
        node20.size=1;
        node35.parent=node38;
        node35.leftChild=null;
        node35.rightChild=null;
        node35.size=1;
        node39.parent=node38;
        node39.leftChild=null;
        node39.rightChild=null;
        node39.size=1;
        node3.parent=node7;
        node3.leftChild=null;
        node3.rightChild=null;
        node3.size=1;
    }
}

class RedBlackNode{
    public int value;
    public boolean isRed;
    public RedBlackNode parent;
    public RedBlackNode leftChild;
    public RedBlackNode rightChild;
    public int size;

    public RedBlackNode(){
        this.parent=new RedBlackNode();
        this.leftChild=new RedBlackNode();
        this.rightChild=new RedBlackNode();
    }
    public RedBlackNode(int value,boolean isRed){
        this();
        this.value=value;
        this.isRed=isRed;
    }
}