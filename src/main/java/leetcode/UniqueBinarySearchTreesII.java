package leetcode;

import java.util.ArrayList;

/**
 * Created by George on 2017/12/18.
 */
public class UniqueBinarySearchTreesII {
    public static void main(String[] args){
        UniqueBinarySearchTreesII ub=new UniqueBinarySearchTreesII();
        ArrayList<TreeNode> trees=ub.generateTrees(3);
        System.out.print(true);
    }
    public ArrayList<TreeNode> generateTrees(int n) {
        if (n == 0) {
            ArrayList<TreeNode> list = new ArrayList<TreeNode>();
            list.add(null);
            return list;
        } else if (n == 1) {
            TreeNode node = new TreeNode(1);
            ArrayList<TreeNode> trees = new ArrayList<TreeNode>();
            trees.add(node);
            return trees;
        } else {//至少两个节点t
            return generateTrees(1, n);//n>1
        }
    }

    public ArrayList<TreeNode> generateTrees(int start, int end) {
        if (start == end) {
            ArrayList<TreeNode> trees = new ArrayList<TreeNode>();
            TreeNode node = new TreeNode(start);
            trees.add(node);
            return trees;
        } else if (start > end) {
            ArrayList<TreeNode> trees = new ArrayList<TreeNode>();
            return trees;
        } else {//至少两个元素start<end
            ArrayList<TreeNode> trees = new ArrayList<TreeNode>();
            for (int i = start; i <= end; i++) {//以每个元素为根节点
                ArrayList<TreeNode> leftTrees = generateTrees(start, i - 1);
                ArrayList<TreeNode> rightTrees = generateTrees(i + 1, end);
                int leftNum = leftTrees.size();
                int rightNum = rightTrees.size();
                if (leftNum == 0 && rightNum == 0) {
                    TreeNode root = new TreeNode(i);
                    trees.add(root);
                } else if (leftNum != 0 && rightNum == 0) {//只有左子树
                    for (TreeNode left : leftTrees) {
                        TreeNode root = new TreeNode(i);
                        root.left = left;
                        trees.add(root);
                    }
                } else if (leftNum == 0 && rightNum != 0) {//只有右子树
                    for (TreeNode right : rightTrees) {
                        TreeNode root = new TreeNode(i);
                        root.right = right;
                        trees.add(root);
                    }
                } else {
                    for (TreeNode left : leftTrees) {
                        for (TreeNode right : rightTrees) {
                            TreeNode root = new TreeNode(i);
                            root.left = left;
                            root.right = right;
                            trees.add(root);
                        }
                    }
                }
            }
            return trees;
        }
    }
}
