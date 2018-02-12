package leetcode;

/**
 * Created by George on 2017/12/12.
 */
public class ConvertSortedArrayToBinarySearchTree {
    public static void main(String[] args){
        int[] num=new int[]{1,2,3};
        ConvertSortedArrayToBinarySearchTree cs=new ConvertSortedArrayToBinarySearchTree();
        TreeNode root=cs.sortedArrayToBST(num);
    }
    public TreeNode sortedArrayToBST(int[] num) {
        if(num.length==0){
            return null;
        }
        else if(num.length==1){
            return new TreeNode(num[0]);
        }
        else{
            return sortedToBST(num,0,num.length);
        }
    }
    public TreeNode sortedToBST(int[] num,int start,int end){
        if(start==end){
            return null;
        }
        else{
            int mid=(start+end)/2;
            TreeNode root=new TreeNode(num[mid]);
            root.left=sortedToBST(num,start,mid);
            root.right=sortedToBST(num,mid+1,end);
            return root;
        }
    }
}
