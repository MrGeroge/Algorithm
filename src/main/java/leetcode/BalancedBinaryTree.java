package leetcode;

/**
 * Created by George on 2017/12/11.
 */
public class BalancedBinaryTree {
    public boolean isBalanced(TreeNode root) {
        if(root==null){
            return true;
        }
        else if(root.left==null && root.right==null){
            return true;
        }
        else{ //有子树
            if(Math.abs((depth(root.left)-depth(root.right)))<=1 && isBalanced(root.left) && isBalanced(root.right)){
                return true;
            }
            else{
                return false;
            }
        }
    }
    public int depth(TreeNode root){
        if(root==null){
            return 0;
        }
        else if(root.left==null && root.right==null){
            return 1;
        }
        else{
            return Math.max(depth(root.left),depth(root.right))+1;
        }
    }
}
