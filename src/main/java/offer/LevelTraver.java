package offer;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by George on 2018/1/8.
 */
public class LevelTraver {
    public static void main(String[] args){
        LevelTraver lt=new LevelTraver();
        TreeNode root=new TreeNode(1);
        TreeNode node=new TreeNode(2);
        root.right=node;
        lt.level(root);

    }
    public void level(TreeNode root){
        if(root==null){
            return;
        }
        else{
            Queue<TreeNode> queue=new LinkedList<TreeNode>();
            queue.offer(root);
            while(!queue.isEmpty()){
                TreeNode node=queue.poll();
                System.out.print(node.val);
                if(node.left!=null){
                    queue.offer(node.left);
                }
                if(node.right!=null){
                    queue.offer(node.right);
                }
            }
            return;
        }
    }
}
