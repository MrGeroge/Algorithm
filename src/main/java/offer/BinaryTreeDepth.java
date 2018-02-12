package offer;

/**
 * Created by George on 2018/1/10.
 */
public class BinaryTreeDepth { //二叉树深度
    public static void main(String[] args) {
        TreeNode root=new TreeNode(1);
        TreeNode node1=new TreeNode(2);
        TreeNode node2=new TreeNode(3);

        root.left=node1;
        root.right=node2;

        BinaryTreeDepth bt=new BinaryTreeDepth();
        System.out.print(bt.isBalanceTree2(root));

    }

    public int depth(TreeNode root) {
        if (root == null) {
            return 0;
        } else if (root.left == null && root.right == null) {
            return 1;
        } else if (root.left != null && root.right == null) {
            return depth(root.left) + 1;
        } else if (root.left == null && root.right != null) {
            return depth(root.right) + 1;
        } else {
            int depthLeft = depth(root.left);
            int depthRight = depth(root.right);
            if (depthLeft > depthRight) {
                return depthLeft + 1;
            } else {
                return depthRight + 1;
            }
        }
    }

    public boolean isBalanceTree(TreeNode root) { //是否为平衡二叉树
        if (root == null) {
            return true;
        } else {
            int leftDepth = depth(root.left);
            int rightDepth = depth(root.right);
            return (int) Math.abs(leftDepth - rightDepth) <= 1 && isBalanceTree(root.left) && isBalanceTree(root.right);
        }
    }

    public boolean isBalanceTreeByPostTraver(TreeNode root, int[] depth) {
        if (root == null) {
            depth[0] = 0;
            return true;
        } else {
            int[] left = new int[1];
            int[] right = new int[1];
            if(isBalanceTreeByPostTraver(root.left,left) && isBalanceTreeByPostTraver(root.right,right)){
                if(Math.abs(left[0]-right[0])<=1){
                    depth[0]=left[0]>right[0]?left[0]+1:right[0]+1;
                    return true;
                }
                else{
                    return false;
                }
            }
            else{
                return false;
            }
        }
    }

    public boolean isBalanceTree2(TreeNode root){
        int[] depth=new int[1];
        depth[0]=0;
        return isBalanceTreeByPostTraver(root,depth);
    }
}
