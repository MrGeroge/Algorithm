package leetcode;

import java.util.ArrayList;

/**
 * Created by George on 2017/12/2.
 */
public class BinaryTreePreorderTraversal { //二叉树遍历
    public ArrayList<Integer> preorderTraversal(TreeNode root) {
        ArrayList<Integer> list=new ArrayList<Integer>();
        if(root!=null){
            list.add(root.val);
            list.addAll(preorderTraversal(root.left));
            list.addAll(preorderTraversal(root.right));
            return list;
        }
        else{
            return list;
        }
    }
    public static void main(String[] args){

    }
}
