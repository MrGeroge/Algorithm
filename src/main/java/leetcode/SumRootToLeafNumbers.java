package leetcode;

/**
 * Created by George on 2017/12/6.
 */
public class SumRootToLeafNumbers {

    public static void main(String[] args){
        SumRootToLeafNumbers sr=new SumRootToLeafNumbers();
        TreeNode root=new TreeNode(8);
        TreeNode node1=new TreeNode(3);
        TreeNode node2=new TreeNode(5);
        TreeNode node3=new TreeNode(9);
        TreeNode node4=new TreeNode(9);
        TreeNode node5=new TreeNode(5);
        root.left=node1;
        root.right=node2;
        node1.right=node3;
        node3.left=node4;
        node3.right=node5;
        System.out.print(sr.sumNumbers(root));
    }
    public int sumNumbers(TreeNode root) {
        if(root==null){
            return 0;
        }
        else if(root.left==null && root.right==null){
            return root.val;
        }
        else if(root.left==null && root.right!=null){
            return sumNumbers(root.right)+root.val*(int)Math.pow(10,depth(root.right));
        }
        else if(root.left!=null && root.right==null){
            return sumNumbers(root.left)+root.val*(int)Math.pow(10,depth(root.left));
        }
        else{
            return sumNumbers(root.left)+sumNumbers(root.right)+root.val*(int)Math.pow(10,depth(root.left))+root.val*(int)Math.pow(10,depth(root.right));
        }
    }
    public int depth(TreeNode root){//树的深度
        if(root==null){
            return 0;
        }
        else if(root.left==null && root.right==null){
            return 1;
        }
        else if(root.left==null && root.right!=null){
            return depth(root.right)+1;
        }
        else if(root.left!=null && root.right==null){
            return depth(root.left)+1;
        }
        else{
            return Math.max(depth(root.left),depth(root.right))+1;
        }
    }
}

