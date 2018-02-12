package leetcode;

import java.util.*;

/**
 * Created by George on 2017/12/12.
 */
public class ConstructBinaryTreeFromInorderAndPost {
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        if(inorder==null || inorder.length==0 || postorder==null || postorder.length==0){
            return null;
        }
        else if(inorder.length==1 && postorder.length==1){//单节点
            TreeNode root=new TreeNode(postorder[0]);
            return root;
        }
        else{
            TreeNode root=new TreeNode(postorder[postorder.length-1]); //根节点
            int index=0;
            for(int i=0;i<inorder.length;i++){ //找根节点的索引
                if(inorder[i]==root.val){
                    index=i;
                    break;
                }
            }
            int[] leftInOrder=new int[index];
            for(int i=0;i<index;i++){
                leftInOrder[i]=inorder[i];
            }
            int[] rightInOrder=new int[inorder.length-index-1];
            for(int i=0;i<rightInOrder.length;i++){
                rightInOrder[i]=inorder[i+index+1];
            }
            int[] leftPostOrder=new int[index];
            for(int j=0;j<index;j++){
                leftPostOrder[j]=postorder[j];
            }
            int[] rightPostOrder=new int[rightInOrder.length];
            for(int j=0;j<rightPostOrder.length;j++){
                rightPostOrder[j]=postorder[j+index];
            }
            root.left=buildTree(leftInOrder,leftPostOrder);
            root.right=buildTree(rightInOrder,rightPostOrder);
            return root;
        }
    }
}
