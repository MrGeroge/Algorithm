package com.george.math;

import javax.xml.ws.Endpoint;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by George on 2017/10/2.
 * Kruskal算法：A是一个森林，加入A的每个安全边是图中连接两个不同连通分支的最小权边
 * 对于图中每个节点创建一个集合Make_Set(v)
 * 对于图中边按照权值升序排列Sort(E)
 * 依次遍历排序好的边(u,v)，若Find_Set(u)所在集合不等于Find_Set(v)，则说明(u,v)安全
 * A=A U (u,v)
 * 将u所在集合与v所在集合合并Union(u,v)
 * 最后返回最小生成树A
 */
public class KruskalMath {
    public static void main(String[] args){
        //为图中每个节点创建不相交集合

        DisjoinTreeNode set1=DisjointSet.makeTree('a');
        DisjoinTreeNode set2=DisjointSet.makeTree('b');
        DisjoinTreeNode set3=DisjointSet.makeTree('c');
        DisjoinTreeNode set4=DisjointSet.makeTree('d');
        DisjoinTreeNode set5=DisjointSet.makeTree('e');
        DisjoinTreeNode set6=DisjointSet.makeTree('f');
        DisjoinTreeNode set7=DisjointSet.makeTree('g');
        DisjoinTreeNode set8=DisjointSet.makeTree('h');
        DisjoinTreeNode set9=DisjointSet.makeTree('i');

        Edge edge1=new Edge(set1,set2,4);
        Edge edge2=new Edge(set2,set3,8);
        Edge edge3=new Edge(set3,set4,7);
        Edge edge4=new Edge(set4,set5,9);
        Edge edge5=new Edge(set5,set6,10);
        Edge edge6=new Edge(set6,set7,2);
        Edge edge7=new Edge(set7,set8,1);
        Edge edge8=new Edge(set8,set1,8);
        Edge edge9=new Edge(set2,set8,11);
        Edge edge10=new Edge(set8,set9,7);
        Edge edge11=new Edge(set3,set9,2);
        Edge edge12=new Edge(set7,set9,6);
        Edge edge13=new Edge(set3,set6,4);
        Edge edge14=new Edge(set4,set6,14);

        List<Edge> edges=new ArrayList<Edge>();
        edges.add(edge1);
        edges.add(edge2);
        edges.add(edge3);
        edges.add(edge4);
        edges.add(edge5);
        edges.add(edge6);
        edges.add(edge7);
        edges.add(edge8);
        edges.add(edge9);
        edges.add(edge10);
        edges.add(edge11);
        edges.add(edge12);
        edges.add(edge13);
        edges.add(edge14);

        Collections.sort(edges);

        List<Edge> A=new ArrayList<Edge>();
        for(Edge e:edges){

                DisjoinTreeNode node1=DisjointSet.findTree(e.getFirstNode());
                DisjoinTreeNode node2=DisjointSet.findTree(e.getSecondNode());

            if(node1!=node2){
                A.add(e);
                DisjointSet.unionTree(e.getFirstNode(),e.getSecondNode());
            }
        }
        //得到最小生成树A
        for(Edge e:A){
            System.out.println("edge source="+e.getFirstNode().getC()+" edge target="+e.getSecondNode().getC());
        }




    }

}
class Edge implements Comparable<Edge>{
    private DisjoinTreeNode firstNode;
    private DisjoinTreeNode secondNode;
    private int weight;

    public Edge(DisjoinTreeNode firstNode, DisjoinTreeNode secondNode, int weight) {
        this.firstNode = firstNode;
        this.secondNode = secondNode;
        this.weight = weight;
    }

    public DisjoinTreeNode getFirstNode() {
        return firstNode;
    }

    public void setFirstNode(DisjoinTreeNode firstNode) {
        this.firstNode = firstNode;
    }

    public DisjoinTreeNode getSecondNode() {
        return secondNode;
    }

    public void setSecondNode(DisjoinTreeNode secondNode) {
        this.secondNode = secondNode;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int compareTo(Edge o) {
        if(this.weight<o.getWeight()){
            return -1;
        }
        else if(this.weight>o.getWeight()){
            return 1;
        }
        else{
            return 0;
        }
    }
}
