package com.george.math;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by George on 2017/10/3.
 *
 * V为树中节点集，G-V为非树节点集，每次从G-V中找到使得到V中某个节点权值最小到节点，并将该节点加入至V中
 */
public class PrimMath {
    public static void main(String[] args){
        PrimGraph graph=PrimMath.createGraph();
        List<PrimEdge> edges=new ArrayList<PrimEdge>();
        int sum=PrimMath.prim(graph,0,edges);
        for(PrimEdge e:edges){
            System.out.println("edge source="+e.getStart()+" edge target="+e.getEnd());
        }
        System.out.println("min cost= "+sum);
    }

    public static int prim(PrimGraph graph,int i,List<PrimEdge> edges){ //prim算法,从第i个节点开始找最小生成树
        int sum=0;
        int[][] arcs=graph.getArcs(); //得到邻接矩阵
        char[] vexs=graph.getVexs(); //得到顶点集
        //最小生成树中顶点到每个非树节点的最小权值（轻边）
        int[] lowCost=new int[6];
        //最小生成树中顶点到每个非树节点的最小权值（轻边对应的树中节点）
        int[] startPoint=new int[6]; //startPoint[i]=j 则表示轻边为（j,i)
        //对lowCost和startPoint初始化
        for(int j=0;j<6;j++){
            lowCost[j]=arcs[i][j];
            startPoint[j]=i;
        }
        //寻找接下来vexnum-1个节点（非树节点)
        for(int m=1;m<6;m++){
            int min=Integer.MAX_VALUE;
            int minIndex=0;

            for(int n=0;n<6;n++){//寻找lowCost中的最小值
                if(lowCost[n]!=0 && lowCost[n]<min){
                    min=lowCost[n];
                    minIndex=n;
                }
            }

            sum=sum+lowCost[minIndex]; //累加权值
            int start=startPoint[minIndex];//起始节点
            int end=minIndex;//终止节点
            PrimEdge edge=new PrimEdge(vexs[start],vexs[end],lowCost[minIndex]);
            edges.add(edge);//最小生成树添加安全边


            lowCost[minIndex]=0; //将节点minIndex加入树种

            //更新lowCost和startPoint
            for(int k=0;k<6;k++){
                int newCost=arcs[minIndex][k];
                if(newCost!=0 && newCost<lowCost[k]){
                    lowCost[k]=newCost;
                    startPoint[k]=minIndex;
                }
            }

        }
        return sum;
    }

    public static PrimGraph createGraph(){ //创建一个图
        char[] vexs=new char[6]; //创建顶点集
        vexs[0]='A';
        vexs[1]='B';
        vexs[2]='C';
        vexs[3]='D';
        vexs[4]='E';
        vexs[5]='F';

        int[][] arcs=new int[6][6];
        arcs[0][0]=0;//不可达
        arcs[0][1]=6;
        arcs[0][2]=1;
        arcs[0][3]=5;
        arcs[0][4]=Integer.MAX_VALUE;
        arcs[0][5]=Integer.MAX_VALUE;

        arcs[1][0]=6;
        arcs[1][1]=0;
        arcs[1][2]=5;
        arcs[1][3]=Integer.MAX_VALUE;
        arcs[1][4]=3;
        arcs[1][5]=Integer.MAX_VALUE;

        arcs[2][0]=1;
        arcs[2][1]=5;
        arcs[2][2]=0;
        arcs[2][3]=5;
        arcs[2][4]=6;
        arcs[2][5]=4;

        arcs[3][0]=5;
        arcs[3][1]=Integer.MAX_VALUE;
        arcs[3][2]=5;
        arcs[3][3]=0;
        arcs[3][4]=Integer.MAX_VALUE;
        arcs[3][5]=2;

        arcs[4][0]=Integer.MAX_VALUE;
        arcs[4][1]=3;
        arcs[4][2]=6;
        arcs[4][3]=Integer.MAX_VALUE;
        arcs[4][4]=0;
        arcs[4][5]=6;

        arcs[5][0]=Integer.MAX_VALUE;
        arcs[5][1]=Integer.MAX_VALUE;
        arcs[5][2]=4;
        arcs[5][3]=2;
        arcs[5][4]=6;
        arcs[5][5]=0;

        PrimGraph graph=new PrimGraph(vexs,arcs,6,10);
        return graph;
    }
}

class PrimGraph{//邻接矩阵表示图
     private char[] vexs;//顶点向量
     private int[][] arcs;//邻接矩阵 权值为非零，若两者不可大则取Integer.MaxValue
    private int vernum; //顶点数
    private int arcnum; //边数

    public PrimGraph(char[] vexs,int[][] arcs,int vernum,int arcnum){
        this.vexs=vexs;
        this.arcs=arcs;
        this.vernum=vernum;
        this.arcnum=arcnum;
    }

    public char[] getVexs() {
        return vexs;
    }

    public void setVexs(char[] vexs) {
        this.vexs = vexs;
    }

    public int[][] getArcs() {
        return arcs;
    }

    public void setArcs(int[][] arcs) {
        this.arcs = arcs;
    }

    public int getVernum() {
        return vernum;
    }

    public void setVernum(int vernum) {
        this.vernum = vernum;
    }

    public int getArcnum() {
        return arcnum;
    }

    public void setArcnum(int arcnum) {
        this.arcnum = arcnum;
    }
}

class PrimEdge{
    private char start; //开始字符
    private char end;//结束字符
    private int weight; //权值

    public PrimEdge(char start, char end, int weight) {
        this.start = start;
        this.end = end;
        this.weight = weight;
    }

    public char getStart() {
        return start;
    }

    public void setStart(char start) {
        this.start = start;
    }

    public char getEnd() {
        return end;
    }

    public void setEnd(char end) {
        this.end = end;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }
}


