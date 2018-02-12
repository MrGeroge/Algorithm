package com.george.math;

import java.util.*;
import java.util.LinkedList;

/**
 * Created by George on 2017/9/21.
 *
 *
 */
public class HufflemanTree { //哈夫曼编码


    public static HufflemanNode createTree(List<HufflemanNode> nodes){
        while(nodes.size()>1){
            Collections.sort(nodes);
            HufflemanNode left=nodes.get(0);//选出权重最小的两个节点
            HufflemanNode right=nodes.get(1);
            HufflemanNode parent=new HufflemanNode(null,left.getWeight()+right.getWeight());
            parent.setLeft(left);
            parent.setRight(right);
            nodes.remove(left);
            nodes.remove(right);
            nodes.add(parent);
        }
        return nodes.get(0); //返回哈夫曼树的根节点
    }
    //打印哈夫曼树
    public static List<HufflemanNode> breadth(HufflemanNode root){
        List<HufflemanNode> list = new ArrayList<HufflemanNode>();
        //Dueue<HufflemanNode> queue = new ArrayDeque();
        Queue<HufflemanNode> queue = new LinkedList<HufflemanNode>();
        if(root != null){
            queue.offer(root);
        }

        while(!queue.isEmpty()){ //遍历
            list.add(queue.peek());
            HufflemanNode node = queue.poll();

            if(node.getLeft() != null){
                queue.offer(node.getLeft());
            }

            if(node.getRight() != null){
                queue.offer(node.getRight());
            }
        }
        return list;
    }


    public static void main(String[] args){
        List<HufflemanNode> list=new ArrayList<HufflemanNode>();
        list.add(new HufflemanNode("a",7));
        list.add(new HufflemanNode("b",5));
        list.add(new HufflemanNode("c",4));
        list.add(new HufflemanNode("d",2));
        list.add(new HufflemanNode("e",3));
        HufflemanNode root=HufflemanTree.createTree(list);
        System.out.println(HufflemanTree.breadth(root));
    }
}

class HufflemanNode implements Comparable<HufflemanNode>{
    private String data;
    private double weight;
    private HufflemanNode left;
    private HufflemanNode right;

    public HufflemanNode(String data,double weight){
        this.data=data;
        this.weight=weight;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public HufflemanNode getLeft() {
        return left;
    }

    public void setLeft(HufflemanNode left) {
        this.left = left;
    }

    public HufflemanNode getRight() {
        return right;
    }

    public void setRight(HufflemanNode right) {
        this.right = right;
    }

    @Override
    public String toString() {
        return "data:"+this.data+";weight:"+this.weight;
    }

    public int compareTo(HufflemanNode o) {
        if(this.weight>o.weight){
            return 1;
        }
        else if(this.weight<o.weight){
            return -1;
        }
        else{
            return 0;
        }
    }
}
