package leetcode;

/**
 * Created by George on 2017/12/11.
 */
public class PathSum {
    public boolean hasPathSum(TreeNode root, int sum) {
        if(root==null){
            return false;
        }
        else if(sum==root.val && root.left==null && root.right==null){
            return true;
        }
        else if(sum!=root.val && root.left==null &&root.right==null){
            return false;
        }
        else{
            sum=sum-root.val;
            return hasPathSum(root.left,sum) || hasPathSum(root.right,sum);
        }
    }
}
