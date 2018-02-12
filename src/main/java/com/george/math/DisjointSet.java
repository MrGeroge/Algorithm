package com.george.math;

/**
 * Created by George on 2017/9/29.
 *
 * 一.不相交集合数据结构概念：
 * 不相交集合数据结构保持一组不相交的动态集合S={S1,S2,...,Sk}，每个集合通过一个代表识别，代表可以是集合中的任意元素
 *
 * 二.不相交集合数据结构的基本操作：
 * Make—Set(x) 创建不相交集合，其代表是x
 * Union(x,y) 合并两个不相交集合x,y,并把x,y从不相交集合结构中删除
 * Find_Set(x) 查找代表x对应的不相交集合
 *
 * 三.不相交集合森林,支持按秩合并（使包含节点较少的树根指向包含节点较多的树根）和路径压缩（将子树的所有节点作为根的孩子）的启发式策略
 */
public class DisjointSet { //不相交数据集合

    public DisjoinLinkedList makeSet(char key){ //给定关键字key创建集合

        DisjoinSetNode node=new DisjoinSetNode();
        node.setC(key);
        node.setRepresent(node);
        node.setNext(null);

        DisjoinLinkedList list=new DisjoinLinkedList();
        list.setHead(node);
        list.setTail(node);
        list.setLength(1);
        return list;
    }

    public static DisjoinTreeNode makeTree(char key){ //创建一个单节点的树
        DisjoinTreeNode root=new DisjoinTreeNode();
        root.setC(key);
        root.setParent(root);
        root.setRank(0);
        return root;
    }

    public static void unionTree(DisjoinTreeNode x,DisjoinTreeNode y){
        linkTree(findTree(x),findTree(y));
    }

    public static void linkTree(DisjoinTreeNode x,DisjoinTreeNode y){
        if(x.getRank()>y.getRank()){
            y.setParent(x);
        }
        else{
            x.setParent(y);
        }
        if(x.getRank()==y.getRank()) {
            y.setRank(y.getRank() + 1);
        }
    }

    public static DisjoinTreeNode findTree(DisjoinTreeNode x){ //返回根节点
        if(x!=x.getParent()){
            x.setParent(findTree(x.getParent()));
        }
        return x.getParent();
    }


    public static void main(String[] args){
        DisjoinTreeNode set1=DisjointSet.makeTree('a');
        DisjoinTreeNode set2=DisjointSet.makeTree('b');
        DisjoinTreeNode set3=DisjointSet.makeTree('c');
        DisjoinTreeNode set4=DisjointSet.makeTree('d');
        DisjoinTreeNode set5=DisjointSet.makeTree('e');
        DisjoinTreeNode set6=DisjointSet.makeTree('f');
        DisjoinTreeNode set7=DisjointSet.makeTree('g');
        DisjoinTreeNode set8=DisjointSet.makeTree('h');
        DisjoinTreeNode set9=DisjointSet.makeTree('i');

        DisjointSet.unionTree(set1,set2);
        DisjoinTreeNode node=DisjointSet.findTree(set1);
    }

}

class DisjoinLinkedList{
    private DisjoinSetNode head; //指向链表的代表
    private DisjoinSetNode tail; //指向最后一个对象
    private int length; //链表长度

    public DisjoinSetNode getHead() {
        return head;
    }

    public void setHead(DisjoinSetNode head) {
        this.head = head;
    }

    public DisjoinSetNode getTail() {
        return tail;
    }

    public void setTail(DisjoinSetNode tail) {
        this.tail = tail;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }
}


class DisjoinSetNode{
    private char c; //集合成员
    private DisjoinSetNode represent; //代表
    private DisjoinSetNode next; //指向下一个成员

    public char getC() {
        return c;
    }

    public void setC(char c) {
        this.c = c;
    }

    public DisjoinSetNode getRepresent() {
        return represent;
    }

    public void setRepresent(DisjoinSetNode represent) {
        this.represent = represent;
    }

    public DisjoinSetNode getNext() {
        return next;
    }

    public void setNext(DisjoinSetNode next) {
        this.next = next;
    }
}

class DisjoinTreeNode{
    private char c;
    private DisjoinTreeNode parent;
    private int rank;

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public char getC() {
        return c;
    }

    public void setC(char c) {
        this.c = c;
    }

    public DisjoinTreeNode getParent() {
        return parent;
    }

    public void setParent(DisjoinTreeNode parent) {
        this.parent = parent;
    }


}
class DisjoinTree{
    private DisjoinTreeNode root;

    public DisjoinTreeNode getRoot() {
        return root;
    }

    public void setRoot(DisjoinTreeNode root) {
        this.root = root;
    }
}
