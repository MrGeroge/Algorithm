package offer;

/**
 * Created by George on 2018/1/8.
 */

public class ConvertBinarySearchTree {//将二叉搜索树转换为双向链表

    public static void main(String[] args){
        TreeNode root=new TreeNode(2);
        TreeNode node1=new TreeNode(1);
        TreeNode node2=new TreeNode(3);
        root.left=node1;
        root.right=node2;

        ConvertBinarySearchTree cb=new ConvertBinarySearchTree();
        TreeNode head=cb.convert(root);
        return;
    }

    public TreeNode convert(TreeNode root){
        TreeNode list=null;
        list=covertNode(root,list);
        TreeNode p=list;
        while(p!=null && p.left!=null){
            p=p.left;
        }
        return p;
    }

    public TreeNode covertNode(TreeNode root,TreeNode list){
        if(root==null){
            return null;
        }
        TreeNode t=root;
        if(t.left!=null){
            list=covertNode(root.left,list);
        }
        t.left=list;
        if(list!=null){
            list.right=t;
        }
        list=t;
        if(t.right!=null){
            list=covertNode(t.right,list);
        }
        return list;
    }
}
