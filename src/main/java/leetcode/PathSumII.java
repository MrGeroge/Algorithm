package leetcode;

import java.util.ArrayList;

/**
 * Created by George on 2017/12/11.
 */
public class PathSumII {
    //private ArrayList<ArrayList<Integer>> results=new ArrayList<ArrayList<Integer>>();
    public ArrayList<ArrayList<Integer>> pathSum(TreeNode root, int sum) {
        if(root==null){
            ArrayList<ArrayList<Integer>> results=new ArrayList<ArrayList<Integer>>();
            return results;
        }
        else{
            if(sum==root.val && root.left==null &&root.right==null){ //到达了叶节点且sum刚好为0
                ArrayList<ArrayList<Integer>> results=new ArrayList<ArrayList<Integer>>();
                ArrayList<Integer> ins=new ArrayList<Integer>();
                ins.add(root.val);
                results.add(ins);
                return results;
            }
            else if(sum!=root.val && root.left==null &&root.right==null){ //到达了叶节点但sum不为0，则丢弃
                ArrayList<ArrayList<Integer>> results=new ArrayList<ArrayList<Integer>>();
                return results;
            }
            else{ //还未到达叶节点，且sum>root.val
                ArrayList<ArrayList<Integer>> results=new ArrayList<ArrayList<Integer>>();
                sum=sum-root.val;
                ArrayList<ArrayList<Integer>> leftPaths=pathSum(root.left,sum); //左子树路径
                ArrayList<ArrayList<Integer>> rightPaths=pathSum(root.right,sum); //右子树路径
                ArrayList<ArrayList<Integer>> leftResults=new ArrayList<ArrayList<Integer>>();
                ArrayList<ArrayList<Integer>> rightResults=new ArrayList<ArrayList<Integer>>();
                if(leftPaths!=null && leftPaths.size()>0){
                    for(ArrayList list:leftPaths){
                        if(list!=null){
                            ArrayList<Integer> temp=new ArrayList<Integer>();
                            temp.add(root.val);
                            temp.addAll(list);
                            leftResults.add(temp);
                        }
                    }
                }
                if(rightPaths!=null && rightPaths.size()>0){
                    for(ArrayList list:rightPaths){
                        if(list!=null){
                            ArrayList<Integer> temp=new ArrayList<Integer>();
                            temp.add(root.val);
                            temp.addAll(list);
                            rightResults.add(temp);
                        }
                    }
                }
                if(leftResults.size()>0){
                    results.addAll(leftResults);
                }
                if(rightResults.size()>0){
                    results.addAll(rightResults);
                }
                return results;
            }

        }
    }
}
