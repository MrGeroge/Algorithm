package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by George on 2017/12/17.
 */
public class ValidateBinarySearchTree {
    public static void main(String[] args){
        TreeNode root=new TreeNode(3);
        TreeNode node1=new TreeNode(1);
        TreeNode node2=new TreeNode(5);
        TreeNode node3=new TreeNode(0);
        TreeNode node4=new TreeNode(2);
        TreeNode node5=new TreeNode(4);
        TreeNode node6=new TreeNode(6);
        root.left=node1;
        root.right=node2;
        node1.left=node3;
        node1.right=node4;
        node2.left=node5;
        node2.right=node6;
        //root.left=node1;
        ValidateBinarySearchTree vb=new ValidateBinarySearchTree();
        System.out.print(vb.isValidBST(root));
    }
    private List<TreeNode> list=new ArrayList<TreeNode>();
    public boolean isValidBST(TreeNode root) {
        if(root==null){
            return true;
        }
        else if(root.left==null && root.right==null){
            return true;
        }
        else{
            inorder(root);
            int i;
            for(i=0;i<list.size();i++){
                if(list.get(i)==root){
                    break;
                }
            }//保证小于i的都小于root.val
            int j;
            for(j=0;j<list.size();j++){
                if((j<i && list.get(j).val<root.val) || (j==i) || (j>i && list.get(j).val>root.val)){
                    continue;
                }
                else{
                    return false;
                }
            }
            list=new ArrayList<TreeNode>();
            return isValidBST(root.left) && isValidBST(root.right);

        }

    }
    public void inorder(TreeNode root){
        if(root==null){
            return;
        }
        else{
            inorder(root.left);
            list.add(root);
            inorder(root.right);
        }
    }
}
