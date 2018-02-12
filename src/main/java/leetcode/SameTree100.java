package leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Created by George on 2017/11/26.
 */
public class SameTree100 {
    public static void main(String[] args){
        TreeNode rootP=new TreeNode(1);
        TreeNode rootQ=new TreeNode(1);
        TreeNode node1=new TreeNode(2);
        TreeNode node2=new TreeNode(2);
        rootP.left=node1;
        rootP.right=null;
        rootQ.left=null;
        rootQ.right=node2;

        SameTree100 st=new SameTree100();
        System.out.println(st.isSameTree(rootP,rootQ));
    }
    public boolean isSameTree(TreeNode p, TreeNode q) {
        Queue<TreeNode> queueP=new LinkedList<TreeNode>();
        Queue<TreeNode> queueQ=new LinkedList<TreeNode>();
        List<Integer> listP=new ArrayList<Integer>();
        List<Integer> listQ=new ArrayList<Integer>();
        if(p==null || q==null){
            return false;
        }
        else{ //均不为空树
            if(p!=null){
                boolean isLeft=true; //
                boolean isRight=true;
                queueP.offer(p);
                while(!queueP.isEmpty()){//队列中有元素
                    TreeNode node=queueP.poll();
                    listP.add(node.val);


                    if(node.left==null && isLeft){
                        isLeft=false;
                        listP.add(-1);
                        //queueP.offer(new TreeNode(-1));
                    }
                    else if(node.left==null && !isLeft){

                    }
                    else{
                        isLeft=false;
                        queueP.offer(node.left);
                    }
                    if(node.right==null && isRight){
                        isRight=false;
                        //listP.add(-1);

                    }
                    else if(node.right==null && !isRight){
                        queueP.add(null);
                    }
                    else{
                        isRight=false;
                        queueP.offer(node.right);
                    }

                }
            }
            if(q!=null){
                queueQ.offer(q);
                while(!queueQ.isEmpty()){
                    TreeNode node=queueQ.poll();
                    if(node.val==-1){

                        continue;
                    }
                    else{
                        listQ.add(node.val);
                    }
                    if(node.left==null){
                        listQ.add(-1);
                        queueQ.offer(new TreeNode(-1));
                    }
                    else{
                        queueQ.offer(node.left);
                    }
                    if(node.right==null){
                        listQ.add(-1);
                        queueQ.offer(new TreeNode(-1));
                    }
                    else{
                        queueQ.offer(node.right);
                    }
                }
            }
            if(listP.size()!=listQ.size()){
                return false;
            }
            else{
                for(int i=0;i<listP.size();i++){
                    if(listP.get(i)!=listQ.get(i)){
                        return false;
                    }
                    else{
                        continue;
                    }

                }
                return true;
            }
        }
    }
}
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}