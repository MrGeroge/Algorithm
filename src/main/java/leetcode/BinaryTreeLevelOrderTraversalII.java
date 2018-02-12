package leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by George on 2017/12/12.
 */
public class BinaryTreeLevelOrderTraversalII {
    public ArrayList<ArrayList<Integer>> levelOrderBottom(TreeNode root) { //层次遍历倒置
        ArrayList<ArrayList<Integer>> results=null;
         if(root==null){
            results=new ArrayList<ArrayList<Integer>>();
             return results;
        }
        else if(root.left==null && root.right==null){
             results=new ArrayList<ArrayList<Integer>>();
             ArrayList<Integer> list=new ArrayList<Integer>();
             list.add(root.val);
             results.add(list);
             return results;
         }
         else{ //root.left!=null ||root.right!=null
             results=new ArrayList<ArrayList<Integer>>();
             HashMap<Integer,ArrayList<TreeNode>> map=new HashMap();
             Queue<TreeNode> queue=new LinkedList<TreeNode>();
             queue.add(root);
             ArrayList<TreeNode> rootList=new ArrayList<TreeNode>();
             rootList.add(root);
             map.put(0,rootList);
             while(!queue.isEmpty()){ //层次遍历
                 TreeNode node=queue.poll();
                 for(HashMap.Entry entry:map.entrySet()){
                     ArrayList<TreeNode> list=(ArrayList<TreeNode>)entry.getValue();
                     if(list!=null && list.contains(node)){
                         int parent=(Integer) entry.getKey(); //
                         ArrayList<TreeNode> sons=map.get(parent+1);
                         if(sons==null || sons.size()==0){
                             sons=new ArrayList<TreeNode>();
                         }
                         if(node.left!=null){
                             queue.add(node.left);
                             sons.add(node.left);
                         }
                         if(node.right!=null){
                             queue.add(node.right);
                             sons.add(node.right);
                         }
                         map.put(parent+1,sons);
                     }
                     else{
                         continue;
                     }
                 }
             }
             for(int i=map.size()-1;i>=0;i--){
                 ArrayList<TreeNode> levelList=map.get(i);
                 if(levelList!=null && levelList.size()>0){
                     ArrayList<Integer> ins=new ArrayList<Integer>();
                     for(TreeNode node:levelList){
                         if(node!=null){
                             ins.add(node.val);
                         }
                     }
                     results.add(ins);
                 }
             }
             return results;

         }
    }
}
