package leetcode;

import java.util.*;

/**
 * Created by George on 2017/12/10.
 */
public class PopulatingNextRightPointersInEachNodeII {
    public static void main(String[] args){
        TreeLinkNode node1=new TreeLinkNode(1);
        TreeLinkNode node2=new TreeLinkNode(2);
        TreeLinkNode node3=new TreeLinkNode(3);
        TreeLinkNode node4=new TreeLinkNode(4);
        TreeLinkNode node5=new TreeLinkNode(5);
        TreeLinkNode node6=new TreeLinkNode(7);
        node1.left=node2;
        node1.right=node3;
        node2.left=node4;
        node2.right=node5;
        node3.right=node6;
        PopulatingNextRightPointersInEachNodeII pnr=new PopulatingNextRightPointersInEachNodeII();
        pnr.connect(node1);
        System.out.println();
    }
    public void connect(TreeLinkNode root) {
        if(root==null){
            return;
        }
        else{
            HashMap<Integer,ArrayList<TreeLinkNode>> map=new HashMap<Integer, ArrayList<TreeLinkNode>>();
            root.next=null;
            Queue<TreeLinkNode> nodes=new LinkedList<TreeLinkNode>();
            nodes.add(root);
            ArrayList<TreeLinkNode> tmp=new ArrayList<TreeLinkNode>();
            tmp.add(root);
            map.put(0,tmp);
            while(!nodes.isEmpty()){
                int parent=0;
                TreeLinkNode node=nodes.poll(); //看node属于第几层
                ArrayList<TreeLinkNode> sons=new ArrayList<TreeLinkNode>();
                for(HashMap.Entry entry:map.entrySet()){
                    ArrayList value=(ArrayList)entry.getValue();
                    if(value.contains(node)){
                        parent=(Integer)entry.getKey();
                    }
                    else{
                        continue;
                    }
                    sons=map.get(parent+1);
                    if(sons==null){
                        sons=new ArrayList<TreeLinkNode>();
                    }
                    if(node.left!=null){
                        nodes.add(node.left);
                        sons.add(node.left);
                    }
                    if(node.right!=null){
                        nodes.add(node.right);
                        sons.add(node.right);
                    }
                }
                if(sons.size()!=0){
                    map.put(parent+1,sons);
                }
            }
            int level=map.size(); //一共多少层
            for(int i=1;i<level;i++){ //处理第i层
                ArrayList<TreeLinkNode> nodeList=map.get(i); //第i层的节点
                for(int j=0;j<nodeList.size()-1;j++){
                    nodeList.get(j).next=nodeList.get(j+1);
                }
                nodeList.get(nodeList.size()-1).next=null;
            }
        }
    }
}
class TreeLinkNode {
      int val;
      TreeLinkNode left, right, next;
      TreeLinkNode(int x) { val = x; }
  }
