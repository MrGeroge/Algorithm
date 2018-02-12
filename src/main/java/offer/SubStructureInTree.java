package offer;

/**
 * Created by George on 2018/1/7.
 */
class TreeNode{
    int val;
    TreeNode left;
    TreeNode right;
    public TreeNode(int val){
        this.val=val;
        this.left=null;
        this.right=null;
    }
}
public class SubStructureInTree { //判断一个树是否为另一个树的子结构
    public static void main(String[] args){

    }
    public boolean isSubStructure(TreeNode root1,TreeNode root2){
        boolean result=false;
        if(root1!=null && root2!=null){
            if(root1.val==root2.val){
                result=doesSubStructure(root1,root2);
            }
            if(!result){
                result=isSubStructure(root1.left,root2);
            }
            if(!result){
                result=isSubStructure(root1.right,root2);
            }
        }
        return result;
    }

    public boolean doesSubStructure(TreeNode root1,TreeNode root2){
        if(root2==null){
            return true;
        }
        else if(root1==null){
            return false;
        }
        else{
            if(root1.val==root2.val){
                return doesSubStructure(root1.left,root2.left) && doesSubStructure(root1.right,root2.right);
            }
            else{
                return false;
            }
        }
    }


}
