package leetcode;

import java.util.ArrayList;

/**
 * Created by George on 2017/12/18.
 */
public class BinaryTreeInorderTraversal {
    public ArrayList<Integer> inorderTraversal(TreeNode root) {
        if(root==null){
            ArrayList<Integer> ins=new ArrayList<Integer>();
            return ins;
        }
        else{
            ArrayList<Integer> ins=new ArrayList<Integer>();
            ins.addAll(inorderTraversal(root.left));
            ins.add(root.val);
            ins.addAll(inorderTraversal(root.right));
            return ins;
        }
    }
}
