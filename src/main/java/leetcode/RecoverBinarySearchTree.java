package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by George on 2017/12/17.
 */
public class RecoverBinarySearchTree { //优先想到递归，其次前序，中序，后序遍历
    private List<TreeNode> list=new ArrayList<TreeNode>();
    public void recoverTree(TreeNode root) {
        if(root==null){
            return;
        }
        else{//至少一个节点
            inorder(root);
            int i;
            for(i=0;i<list.size();i++){
                if(list.get(i).val>list.get(i+1).val){
                    break;
                }
            }
            int j;
            for(j=list.size()-1;j>=0;j--){
                if(list.get(j).val<list.get(j-1).val){
                    break;
                }
            }
            int tmp;
            tmp=list.get(i).val;
            list.get(i).val=list.get(j).val;
            list.get(j).val=tmp;
            return;
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
