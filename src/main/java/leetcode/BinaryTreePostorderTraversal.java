package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by George on 2017/12/3.
 */
public class BinaryTreePostorderTraversal { //后序遍历
    public ArrayList<Integer> postorderTraversal(TreeNode root) {
        ArrayList<Integer> list=new ArrayList<Integer>();
        if(root==null){
            return list;
        }
        else{
            list.addAll(postorderTraversal(root.left));
            list.addAll(postorderTraversal(root.right));
            list.add(root.val);
            return list;
        }
    }
}
