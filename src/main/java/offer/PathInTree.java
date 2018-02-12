package offer;

import java.util.ArrayList;

/**
 * Created by George on 2018/1/8.
 */
public class PathInTree {//给定一个树以及一个值，找到所有从根节点->叶节点路径和为该值的

    public static void  main(String[] args){
        TreeNode root=new TreeNode(10);
        TreeNode node1=new TreeNode(5);
        TreeNode node2=new TreeNode(12);
        TreeNode node3=new TreeNode(4);
        TreeNode node4=new TreeNode(7);
        root.left=node1;
        root.right=node2;
        node1.left=node3;
        node1.right=node4;
        PathInTree pt=new PathInTree();
        pt.findPath(root,22);
    }

    public void findPath(TreeNode root,int value){
        if(root==null){
            return;
        }
        else{
            ArrayList<Integer> path=new ArrayList<Integer>(); //其中一条路径
            findPathByFrontOrder(root,path,value,0);
        }
    }

    public void findPathByFrontOrder(TreeNode root,ArrayList<Integer> path,int predictValue,int sum){
        sum=sum+root.val;
        path.add(root.val);
        boolean isLeaf=false;
        if(root.left==null && root.right==null){
            isLeaf=true;
        }
        if(isLeaf && sum==predictValue){
            for(Integer i:path){
                System.out.print(i);
            }
            System.out.println();
        }
            if(root.left!=null){
                findPathByFrontOrder(root.left,path,predictValue,sum);
            }
            if(root.right!=null){
                findPathByFrontOrder(root.right,path,predictValue,sum);
            }

            path.remove(path.size()-1);
            sum=sum-root.val;

    }
}
