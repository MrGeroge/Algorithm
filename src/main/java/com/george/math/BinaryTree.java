package com.george.math;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;


/**
 * Created by George on 2017/6/14.
 */
    public class BinaryTree {//二叉树遍历,时间复杂度为O(h)


        private TreeNode root=null;

        public BinaryTree(){//空树
            root=new TreeNode(15,"rootNode(A)");
        }

        /**
         * 创建一棵二叉树
         * <pre>
         *           A
         *     B          C
         *  D     E            F
         *  </pre>
         * @param root
         * @author WWX
         */
        public void createBinTree(TreeNode root){//根据根节点构造二叉树
            TreeNode newNodeB = new TreeNode(6,"B");
            TreeNode newNodeC = new TreeNode(18,"C");
            TreeNode newNodeD = new TreeNode(3,"D");
            TreeNode newNodeE = new TreeNode(7,"E");
            TreeNode newNodeF = new TreeNode(20,"F");
            root.leftChild=newNodeB;
            root.rightChild=newNodeC;
            root.leftChild.leftChild=newNodeD;
            root.leftChild.rightChild=newNodeE;
            root.rightChild.rightChild=newNodeF;
            newNodeB.parent=root;
            newNodeC.parent=root;
            newNodeD.parent=newNodeB;
            newNodeE.parent=newNodeB;
            newNodeF.parent=newNodeC;
        }

        public TreeNode treeSearch(TreeNode subTree,int key){//在subTree中查找是否有key的节点
            if(subTree==null||subTree.key==key){
                return subTree;
            }
            else{
                int tmp=subTree.key;
                if(tmp>key){
                    return treeSearch(subTree.leftChild,key);
                }
                else if(tmp<key){
                    return treeSearch(subTree.rightChild,key);
                }
                else{
                   return subTree;
                }
            }
        }
        public boolean isEmpty(){//根据根节点判断二叉树是否为空
            return root==null;
        }

        //树的高度
        public int height(){
            return height(root);
        }

        //节点个数
        public int size(){
            return size(root);
        }


        private int height(TreeNode subTree){//递归法求二叉树高度，判断subTree是否为空（空树判断），若不为空树则可以求其左子树的高度i以及右子树的高度j，若i<j,则整个树的高度为（j+1）
            if(subTree==null)
                return 0;//递归结束：空树高度为0
            else{
                int i=height(subTree.leftChild);
                int j=height(subTree.rightChild);
                return (i<j)?(j+1):(i+1);
            }
        }

        private int size(TreeNode subTree){//递归法求二叉树节点个数，判断subTree是否为空（空树判断），若不为空树则可以求其左子树和右子树的节点个数i，j加上根节点变为整个树的节点总数
            if(subTree==null){
                return 0;
            }else{
                return 1+size(subTree.leftChild)
                        +size(subTree.rightChild);
            }
        }

        //返回双亲结点
        public TreeNode parent(TreeNode element){
            return (root==null|| root==element)?null:parent(root, element);
        }

        public TreeNode parent(TreeNode subTree,TreeNode element){
            if(subTree==null)
                return null;
            if(subTree.leftChild==element||subTree.rightChild==element)
                //返回父结点地址
                return subTree;
            TreeNode p;
            //现在左子树中找，如果左子树中没有找到，才到右子树去找
            if((p=parent(subTree.leftChild, element))!=null)
                //递归在左子树中搜索
                return p;
            else
                //递归在右子树中搜索
                return parent(subTree.rightChild, element);
        }

        public TreeNode getLeftChildNode(TreeNode element){
            return (element!=null)?element.leftChild:null;
        }

        public TreeNode getRightChildNode(TreeNode element){
            return (element!=null)?element.rightChild:null;
        }

        public TreeNode getRoot(){
            return root;
        }

        //在释放某个结点时，该结点的左右子树都已经释放，
        //所以应该采用后续遍历，当访问某个结点时将该结点的存储空间释放
        public void destroy(TreeNode subTree){
            //删除根为subTree的子树
            if(subTree!=null){
                //删除左子树
                destroy(subTree.leftChild);
                //删除右子树
                destroy(subTree.rightChild);
                //删除根结点
                subTree=null;
            }
        }

        public void traverse(TreeNode subTree){//打印子树
            System.out.println("key:"+subTree.key+"--name:"+subTree.data);;
            traverse(subTree.leftChild);
            traverse(subTree.rightChild);
        }

        //前序遍历
        public void preOrder(TreeNode subTree){
            if(subTree!=null){
                visted(subTree);
                preOrder(subTree.leftChild);
                preOrder(subTree.rightChild);
            }
        }

        //中序遍历
        public void inOrder(TreeNode subTree){
            if(subTree!=null){
                inOrder(subTree.leftChild);
                visted(subTree);
                inOrder(subTree.rightChild);
            }
        }

        //后续遍历
        public void postOrder(TreeNode subTree) {
            if (subTree != null) {
                postOrder(subTree.leftChild);
                postOrder(subTree.rightChild);
                visted(subTree);
            }
        }

        //前序遍历的非递归实现
        public void nonRecPreOrder(TreeNode p){
            Stack<TreeNode> stack=new Stack<TreeNode>();
            TreeNode node=p;
            while(node!=null||stack.size()>0){
                while(node!=null){
                    visted(node);
                    stack.push(node);
                    node=node.leftChild;
                }
			while(stack.size()>0){
                    node=stack.pop();
                    node=node.rightChild;
                }
            }
        }

        //中序遍历的非递归实现
        public void nonRecInOrder(TreeNode p){
            Stack<TreeNode> stack =new Stack<BinaryTree.TreeNode>();
            TreeNode node =p;
            while(node!=null||stack.size()>0){
                //存在左子树
                while(node!=null){
                    stack.push(node);
                    node=node.leftChild;
                }
                //栈非空
                if(stack.size()>0){
                    node=stack.pop();
                    visted(node);
                    node=node.rightChild;
                }
            }
        }

        //后序遍历的非递归实现
        public void noRecPostOrder(TreeNode p){
            Stack<TreeNode> stack=new Stack<TreeNode>();
            TreeNode node =p;
            while(p!=null){
                //左子树入栈
                for(;p.leftChild!=null;p=p.leftChild){
                    stack.push(p);
                }
                //当前结点无右子树或右子树已经输出
                while(p!=null&&(p.rightChild==null||p.rightChild==node)){
                    visted(p);
                    //纪录上一个已输出结点
                    node =p;
                    if(stack.empty())
                        return;
                    p=stack.pop();
                }
                //处理右子树
                stack.push(p);
                p=p.rightChild;
            }
        }

        //层次遍历
        public static void levelTravel(TreeNode root){
            if(root==null)return;
            Queue<TreeNode> q=new LinkedList<TreeNode>();
            q.add(root);
            while(!q.isEmpty()){
                TreeNode temp =  q.poll();
                System.out.println(temp.data);
                if(temp.leftChild!=null)q.add(temp.leftChild);
                if(temp.rightChild!=null)q.add(temp.rightChild);
            }
        }
    public void visted(TreeNode subTree){
            subTree.isVisted=true;
            System.out.println("key:"+subTree.key+"--name:"+subTree.data);;
        }

        public TreeNode searchMin(TreeNode subTree){//寻找子树中的最小值
            if(subTree==null){
                return null;
            }
            else{
                while(subTree.leftChild!=null){
                    subTree=subTree.leftChild;
                }
                return subTree;
            }
        }

        public TreeNode searchMax(TreeNode subTree){//寻找子树中的最大值
            if(subTree==null){
                return null;
            }
            else{
                while(subTree.rightChild!=null){
                    subTree=subTree.rightChild;
                }
                return subTree;
            }
        }

    /**
     *1.如果当前结点有右儿子，或者当前结点是根结点，则后继结点为右子树的最左叶节点；
     *2.如果当前结点是父结点的左儿子，则后继结点就是父结点；
     *3.向上遍历，直到n-1代祖先是n代祖先的左儿子，则后继结点为n代祖先；或者遍历到根节点后未找到符合的n代结点，则该结点为中序遍历的最后结点，没有后继
     *
     *
     */

    public TreeNode searchNext(TreeNode node){ //寻找节点在中序遍历下的后继节点，node的后继即具有大于key[node]的最小值所在节点
            if(node.rightChild!=null){
                return searchMin(node.rightChild);//寻找node右子树中的最小值
            }
            TreeNode tmp=node.parent;  //找到其父节点
            while(tmp!=null && node==tmp.rightChild){//直到找到第一个parent的左儿子为node，则说明parent是node的后继节点
                node=tmp;
                tmp=tmp.parent;
            }
            return tmp;
        }

    public TreeNode searchPrev(TreeNode node){//寻找节点在中序遍历下的前驱节点，node的前驱
        if(node.leftChild!=null){
            return searchMax(node.leftChild);
        }
        else{
            TreeNode tmp=node.parent;
            while(tmp!=null && node==tmp.leftChild){//直到找到第一个parent的右儿子是node，则parent即为前驱
                node=tmp;
                tmp=tmp.parent;
            }
            return tmp;
        }
    }

    public void treeInsert(TreeNode node){ //向二叉树中插入节点node
        TreeNode tmp=null;
        TreeNode x=root;//从根节点向下查找
        while(x!=null){
            tmp=x; //保留此时的x
            if(node.key<x.key){
                x=x.leftChild;
            }
            else{
                x=x.rightChild;
            }
        }//tmp即为插入节点位置的parent
        node.parent=tmp;
        if(tmp==null){
            root=node;
        }
        else{
            if(node.key<tmp.key){
                tmp.leftChild=node;
            }
            else{
                tmp.rightChild=node;
            }
        }
    }

    public TreeNode treeDelete(TreeNode z){ //删除节点node
        TreeNode y;
        TreeNode x;
        if(z.leftChild==null || z.rightChild==null){//若node最多一个子节点
            y=z;
        }
        else{ //存在两个子节点
            y=searchNext(z); //寻找node的后继节点,即右子树中最小值
        }
        if(y.leftChild!=null){//找到待删除节点的非空子节点
            x=y.leftChild;
        }
        else{
            x=y.rightChild;
        }
        if(x!=null){
            x.parent=y.parent; //删除y节点
        }
        if(y.parent==null){//y为根节点root，x作为新的root
            root=x;
        }
        else if(y==y.parent.leftChild){//若已删除节点y是左节点，x替换y的位置
            y.parent.leftChild=x;
        }
        else{
            y.parent.rightChild=x;
        }
        if(y!=z){//已删除节点和要求删除节点不是同一个
            z.key=y.key;
        }
        return y;//返回已删除节点
    }


        /**
         * 二叉树的节点数据结构
         * @author WWX
         */
        private static class  TreeNode{
            private int key=0;
            private String data=null;
            private boolean isVisted=false;
            private TreeNode leftChild=null;
            private TreeNode rightChild=null;
            private TreeNode parent=null;

            public TreeNode(){}

            /**
             * @param key  层序编码
             * @param data 数据域
             */
            public TreeNode(int key,String data){
                this.key=key;
                this.data=data;
                this.leftChild=null;
                this.rightChild=null;
                this.parent=null;
            }
        }
        //测试
        public static void main(String[] args) {
            BinaryTree bt = new BinaryTree();
            bt.createBinTree(bt.root);
            System.out.println("the size of the tree is " + bt.size());
            System.out.println("the height of the tree is " + bt.height());

            System.out.println("*******(前序遍历)[ABDECF]遍历*****************");
            bt.preOrder(bt.root);

            System.out.println("*******(中序遍历)[DBEACF]遍历*****************");
            bt.inOrder(bt.root);

            System.out.println("*******(后序遍历)[DEBFCA]遍历*****************");
            bt.postOrder(bt.root);

            System.out.println("***非递归实现****(前序遍历)[ABDECF]遍历*****************");
            bt.nonRecPreOrder(bt.root);

            System.out.println("***非递归实现****(中序遍历)[DBEACF]遍历*****************");
            bt.nonRecInOrder(bt.root);

            System.out.println("***非递归实现****(后序遍历)[DEBFCA]遍历*****************");
            bt.noRecPostOrder(bt.root);

            TreeNode result=bt.treeSearch(bt.root,7);
            System.out.println(result.key);

            result=bt.searchMin(bt.root);
            System.out.println(result.key);

            result=bt.searchMax(bt.root);
            System.out.println(result.key);

            result=bt.searchPrev(bt.root); //寻找root的前驱节点
            System.out.println(result.key);

            result=bt.searchNext(bt.root); //寻找root的后继节点
            System.out.println(result.key);

            TreeNode node=new TreeNode(9,"node new");
            bt.treeInsert(node);
            bt.inOrder(bt.root);

            bt.treeDelete(bt.root.leftChild);
            bt.inOrder(bt.root);
        }
    }

