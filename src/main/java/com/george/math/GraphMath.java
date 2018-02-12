package com.george.math;

import java.util.*;
import java.util.LinkedList;

/**
 * Created by George on 2017/9/29.
 * 一.图结构定义
 * 邻接表：适用于节点个数远小于边数平方的稀疏图
 * 邻接矩阵；适用于两者基本相等的密集图，可方便知道任意两个顶点是否构成边，适用于小图
 *
 * 二.图搜索算法
 * 1.广度优先搜索：给定源节点s，找出从s出发可达的所有节点，构成广度优先树
 * 黑色：标记为已探索节点
 * 灰色：已发现，未探索完节点
 * 白色：未发现节点
 *
 * 简而言之就是每当遍历到一个节点时，先要将其四周的节点遍历一遍，
 * 2.深度优先搜索：
 *
 * 3.最小生成树
 * 需要保证经过所有节点的权值最小，找到最小生成树的算法
 * 1）边集A=null
 * 2)识别安全边(u,v) ,使A=A U (u,v)
 * 3)返回A
 *
 * 关键在于识别安全边即A是某个最小生成树T的真子集，(u,v)属于T,但(u,v)不属于A,则(u,v)是A的安全边
 *
 * 其中一种策略是:
 * 如果(S,V-S)是E的一个割，A中不存在边通过割,(u,v)是通过割（即u属于S，v属于V-S）的权值最小的轻边，那么(u,v)对于A是安全的、
 *
 * 具体的两种寻找安全边的算法；
 * 1)Kruskal算法：A是一个森林，加入A的每个安全边是图中连接两个不同连通分支的最小权边
 * 对于图中每个节点创建一个集合Make_Set(v)
 * 对于图中边按照权值升序排列Sort(E)
 * 依次遍历排序好的边(u,v)，若Find_Set(u)所在集合不等于Find_Set(v)，则说明(u,v)安全
 * A=A U (u,v)
 * 将u所在集合与v所在集合合并Union(u,v)
 * 最后返回最小生成树A
 *
 * 利用不相交集合森林,两种启发式策略按秩合并和路径压缩
 * 2)Prim算法：A是一个树，加入A中的每个安全边是连接树与不在树顶点的最小权边
 *
 *
 */
public class GraphMath { //图算法
    public static void main(String[] args){
        Graph graph=new Graph();
        graph.createGraph(); //创建图
        /*VNode s=new VNode();
        ArcNode node1=new ArcNode();
        node1.setAdjvex(1);
        ArcNode node2=new ArcNode();
        node2.setAdjvex(2);
        node1.setNextarc(node2);
        node2.setNextarc(null);
        s.setIndex(0);
        s.setData('A');
        s.setFirstarc(node1);
        int[] d=new int[graph.getVexnum()];
        int[] color=new int[graph.getVexnum()];
        VNode[] parents=new VNode[graph.getVexnum()];
        /*graph.BFS(s,d,color,parents);
        VNode v=new VNode();
        ArcNode node16=new ArcNode();
        node16.setAdjvex(1);
        ArcNode node17=new ArcNode();
        node17.setAdjvex(7);
        node16.setNextarc(node17);
        node17.setNextarc(null);
        v.setIndex(4);
        v.setData('E');
        v.setFirstarc(node16);

        graph.printPath(s,v,parents);*/

        /*graph.DFS(color,parents,0);

        VNode v=new VNode();
        ArcNode node_tmp1=new ArcNode();
        node_tmp1.setAdjvex(3);
        ArcNode node_tmp2=new ArcNode();
        node_tmp2.setAdjvex(4);
        node_tmp1.setNextarc(node_tmp2);
        node_tmp2.setNextarc(null);
        v.setIndex(7);
        v.setData('H');
        v.setFirstarc(node_tmp1);
        graph.printPath(s,v,parents);*/

        graph.topologicalSort();
    }
}

class Graph{
    private List<VNode> tops=new LinkedList<VNode>();
    public VNode[] vertices; //顶点集合
    private int vexnum; //顶点个数
    private int arcnum;//边个数

    public VNode[] getVertices() {
        return vertices;
    }

    public void setVertices(VNode[] vertices) {
        this.vertices = vertices;
    }

    public int getVexnum() {
        return vexnum;
    }

    public void setVexnum(int vexnum) {
        this.vexnum = vexnum;
    }

    public int getArcnum() {
        return arcnum;
    }

    public void setArcnum(int arcnum) {
        this.arcnum = arcnum;
    }

    public void createGraph(){
        vexnum=8;
        arcnum=9;

        vertices=new VNode[vexnum]; //创建顶点
        for(int i=0;i<vexnum;i++){
            vertices[i]=new VNode();
        }

        ArcNode node1=new ArcNode();
        node1.setAdjvex(1);
        ArcNode node2=new ArcNode();
        node2.setAdjvex(2);
        node1.setNextarc(node2);
        node2.setNextarc(null);
        vertices[0].setData('A');
        vertices[0].setFirstarc(node1);
        vertices[0].setIndex(0);

        ArcNode node3=new ArcNode();
        node3.setAdjvex(3);
        ArcNode node4=new ArcNode();
        node4.setAdjvex(4);
        ArcNode node0=new ArcNode();
        node0.setAdjvex(0);
        node0.setNextarc(node3);
        node3.setNextarc(node4);
        node4.setNextarc(null);
        vertices[1].setData('B');
        vertices[1].setFirstarc(node0);
        vertices[1].setIndex(1);

        ArcNode node=new ArcNode();
        node.setAdjvex(0);
        ArcNode node5=new ArcNode();
        node5.setAdjvex(5);
        ArcNode node6=new ArcNode();
        node6.setAdjvex(6);
        node.setNextarc(node5);
        node5.setNextarc(node6);
        node6.setNextarc(null);
        vertices[2].setData('C');
        vertices[2].setFirstarc(node);
        vertices[2].setIndex(2);

        ArcNode node8=new ArcNode();
        node8.setAdjvex(1);
        ArcNode node9=new ArcNode();
        node9.setAdjvex(7);
        node8.setNextarc(node9);
        node9.setNextarc(null);
        vertices[3].setData('D');
        vertices[3].setFirstarc(node8);
        vertices[3].setIndex(3);

        ArcNode node16=new ArcNode();
        node16.setAdjvex(1);
        ArcNode node17=new ArcNode();
        node17.setAdjvex(7);
        node16.setNextarc(node17);
        node17.setNextarc(null);
        vertices[4].setData('E');
        vertices[4].setFirstarc(node16);
        vertices[4].setIndex(4);

        ArcNode node10=new ArcNode();
        node10.setAdjvex(2);
        ArcNode node11=new ArcNode();
        node11.setAdjvex(6);
        node10.setNextarc(node11);
        node11.setNextarc(null);
        vertices[5].setData('F');
        vertices[5].setFirstarc(node10);
        vertices[5].setIndex(5);

        ArcNode node12=new ArcNode();
        node12.setAdjvex(2);
        ArcNode node13=new ArcNode();
        node13.setAdjvex(5);
        node12.setNextarc(node13);
        node13.setNextarc(null);
        vertices[6].setData('G');
        vertices[6].setFirstarc(node12);
        vertices[6].setIndex(6);

        ArcNode node14=new ArcNode();
        node14.setAdjvex(3);
        ArcNode node15=new ArcNode();
        node15.setAdjvex(4);
        node14.setNextarc(node15);
        node15.setNextarc(null);
        vertices[7].setData('H');
        vertices[7].setFirstarc(node14);
        vertices[7].setIndex(7);


    }

    public void BFS(VNode s,int[] d,int[] color,VNode[] parents){ //广度优先搜索（类似于前序遍历）s开始，每次遍历其子节点,d保存s到任意节点的最短路径长度，parents保存s到任意节点的最短路径所经过的节点
        Queue<VNode> queue=new LinkedList<VNode>(); //队列
         //s到另外节点的距离
        // 每个节点的颜色,0=White 1=Gray 2=Black
        //每个节点的parent
        for(VNode v:vertices){ //除了s的其他节点
            if(v.getIndex()!=s.getIndex()){
                d[v.getIndex()]=Integer.MAX_VALUE;
                color[v.getIndex()]=0;
                parents[v.getIndex()]=null;
            }
        }
        d[s.getIndex()]=0;
        color[s.getIndex()]=1; //s节点为灰色，即已发现但为探索
        parents[s.getIndex()]=null;
        queue.add(s);
        while(!queue.isEmpty()){
            VNode u=queue.poll(); // 移除并返问队列头部的元素
            ArcNode adjNode=u.getFirstarc(); //第一个邻接节点
            while(adjNode!=null){
               if(color[adjNode.getAdjvex()]==0){
                   color[adjNode.getAdjvex()]=1; //设置节点颜色为灰色，说明已发现但未探索
                   parents[adjNode.getAdjvex()]=u;
                   d[adjNode.getAdjvex()]=d[u.getIndex()]+1;
                   queue.add(vertices[adjNode.getAdjvex()]);
               }
               adjNode=adjNode.getNextarc();
            }
            color[u.getIndex()]=2;
        }
    }

    public void DFS(int[] color,VNode[] parents,int time){ //核心思想是探索与每个顶点相邻的每条边
        for(VNode u:vertices){
            color[u.getIndex()]=0;//标记每个顶点为白色
            parents[u.getIndex()]=null;
        }
       time=0;
        for(VNode u:vertices){
            if(color[u.getIndex()]==0){
                int[] d=new int[vexnum];//每个顶点被发现的时间
                int[] f=new int[vexnum];//每个顶点被探索完成的时间
                DFSvisit(u,color,parents,time,d,f);
                System.out.println("###########################");
            }
        }
    }

    public void DFSvisit(VNode u,int[] color,VNode[] parents,int time,int[] d,int[] f){ //深度优先遍历
        color[u.getIndex()]=1; //设置节点为灰色
        time++;
        d[u.getIndex()]=time;
        ArcNode adjNode=u.getFirstarc();
        while(adjNode!=null){
           if(color[adjNode.getAdjvex()]==0){
               parents[adjNode.getAdjvex()]=u;
               DFSvisit(vertices[adjNode.getAdjvex()],color,parents,time,d,f);
           }
           adjNode=adjNode.getNextarc();
        }
        color[u.getIndex()]=2; //设置节点为黑色
        f[u.getIndex()]=time+1;
        //将完成的u插入节点
        tops.add(new VNode());
        for(int i=(tops.size()-2);i>=0;i--){
            tops.set(i+1,tops.get(i));
        }
        tops.set(0,u);
    }

    public void topologicalSort(){ //按照完成时间递减顺序排列
        int[] color=new int[vexnum];
        VNode[] parents=new VNode[vexnum];
        DFS(color,parents,0);
        for(VNode v:tops){
            System.out.println(v.getData());
        }
    }

    public void printPath(VNode s,VNode v,VNode[] parents){ //s到v的路径
        if(s.getIndex()==v.getIndex()){
           System.out.print(s.getData());
        }
        else if(parents[v.getIndex()]==null){
            System.out.println("no path from "+s.getData()+"to"+v.getData()+"exists");
        }
        else{
            printPath(s,parents[v.getIndex()],parents);
            System.out.print(v.getData());
        }

    }


}

class ArcNode{
    private int adjvex; //该边所指向的顶点位置
    private ArcNode nextarc; //指向下一条边的指针

    public int getAdjvex() {
        return adjvex;
    }

    public void setAdjvex(int adjvex) {
        this.adjvex = adjvex;
    }

    public ArcNode getNextarc() {
        return nextarc;
    }

    public void setNextarc(ArcNode nextarc) {
        this.nextarc = nextarc;
    }
}

class VNode{
    private int index; //索引
    private char data; //顶点信息
    private ArcNode firstarc; //指向第一条依附该顶点边

    public char getData() {
        return data;
    }

    public void setData(char data) {
        this.data = data;
    }

    public ArcNode getFirstarc() {
        return firstarc;
    }

    public void setFirstarc(ArcNode firstarc) {
        this.firstarc = firstarc;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}



