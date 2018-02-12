package offer;

import java.util.zip.DeflaterOutputStream;

/**
 * Created by George on 2018/1/7.
 */
public class DoMirror { //给定一个树，找到其对应的镜像
    public static void main(String[] args){
        TreeNode root=new TreeNode(8);
        TreeNode node1=new TreeNode(6);
        TreeNode node2=new TreeNode(10);
        TreeNode node3=new TreeNode(5);
        TreeNode node4=new TreeNode(7);
        TreeNode node5=new TreeNode(9);
        TreeNode node6=new TreeNode(11);
        root.left=node1;
        root.right=node2;
        node1.left=node3;
        node1.right=node4;
        node2.left=node5;
        node2.right=node6;
        DoMirror dm=new DoMirror();
        TreeNode rootMirror=dm.mirror(root);
        dm.mirror2(root);
        return;
    }
    public TreeNode mirror(TreeNode root){
        TreeNode nRoot=null;
        if(root!=null){
            nRoot=new TreeNode(root.val);
            nRoot.left=mirror(root.right);
            nRoot.right=mirror(root.left);
        }
        return nRoot;
    }

    public void mirror2(TreeNode root){
        if(root==null || (root.left==null && root.right==null)){
            return;
        }
        else{
            TreeNode temp=root.left;
            root.left=root.right;
            root.right=temp;

            if(root.left!=null){
                mirror2(root.left);
            }
            if(root.right!=null){
                mirror2(root.right);
            }
        }
    }
}
