package leetcode;

/**
 * Created by George on 2017/12/12.
 */
public class SameTree {
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if(p==null && q==null){
            return true;
        }
        else if(p==null || q==null){
            return false;
        }
        else{
            return (p.val==q.val) && isSameTree(p.left,q.left) && isSameTree(p.right,q.right);
        }
    }
}
