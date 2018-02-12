package com.george.math;

import java.util.*;
import java.util.LinkedList;

/**
 * Created by George on 2017/10/9.
 *
 * 1.最大流问题：描述在不违背容量限制的条件下，把物质从源点传输到汇点的最大速率是多少
 * 假设G=(V,E)是一个流网络，其容量函数是c，s为网络的源点，t为网络的汇点，其中G的流是一个实质函数f满足三个性质：
 * 容量限制：对u，v属于V，要求f(u,v)<=c(u,v)
 * 反对称性：对u，v属于V，f（u,v)=-f(v,u)
 * 流守恒性：对所有u属于非源汇点，要求u到其他节点v的流之和为0，当（u,v)或者（v,u)都不在E中，则u和v之间不可能有网络流，流入某节点的流量必须等于流出某节点的流量
 *
 * 通常需要对问题进行抵消处理，保证流只沿一个方向传输
 * 对于多源点与多汇点问题可以通过增加超级源点s和超级汇点t来转换
 *
 * 2.最大流问题的典型算法
 * 1）Ford-Fulkerson算法
 * 依赖于三种思想：残留网络，增广路径，割，最大流最小割定力，用流网络的割描述最大流的值，初始状态流的值为0
 *
 * 残留网络中每条边cf（u,v)=c(u-v)-f(u,v)
 * 残留网络中的一条由s到t的简单路径为增广路径，一条增广路径上的每条边输入网络流的最大量是p的残余容量
 * cf(p)=min(cf(u,v))(其中u，v在路径p上)
 *
 * 割S+T=V,s属于S，v属于V，则（S，V）称为割，经过割的净流即为f（s,v)之和,而最大流最小割定理中最小割即为网络中所有割具有最小容量（c(s,v)之和，只包含s->v）的割
 *
 * 对于源点s和汇点t的流网络G=（V,E)中的一个流f，最大流最小割定理满足以下条件：
 * 1）f是G的一个最大流
 * 2）残留网络Gf不包含增广路径
 * 3）对于G的某个割（S，T），有|f|=c(S,T)
 *
 * 2)Edmonds-Karp算法实际上是对Ford-Fulkerson算法对改进，即将增广路径p的选择策略由广度优先搜索->寻找(s,t)的最短路径
 *
 * 3）最大二分匹配问题：
 * 即将节点集V=L+R,s属于L，v属于R，从L->R的所有边(s,v)，取势能最大的边集合即为最大二分匹配
 *
 * 最大二分匹配求解：
 * 1）将L+R组成的多源点和多汇点G，通过增加超级源点s和超级汇点v变成G'
 * 2）每条边的容量为1，调用fordFulkerson算法求解得到的最大流f对应的路径即为最大二分匹配
 */
public class MaxStreamProblem {

    public static void main(String[] args){
        MaxStreamProblem msp=new MaxStreamProblem();
        MaxStreamGraph graph=msp.createMaxStreamGraph();
        int maxFlow=msp.fordFulkersonMethod(graph,0,5);
        System.out.print(maxFlow);
    }
    public int fordFulkersonMethod(MaxStreamGraph graph,int s,int t){ //寻找源s到汇t的最大流
        int u,v;
        int[][] residual=new int[graph.getVernum()][graph.getVernum()]; //等价于f，流量函数
        for(u=0;u<graph.getVernum();u++){ //初始化流量函数
            for(v=0;v<graph.getVernum();v++){
                residual[u][v]=0;
            }
        }
        for(u=0;u<graph.getVernum();u++){
            for(v=0;v<graph.getVernum();v++){
                if(graph.getArcs()[u][v]!=0 && graph.getArcs()[u][v]!=Integer.MAX_VALUE){ //(u,v)属于E
                    residual[u][v]=graph.getArcs()[u][v];
                }
            }
        }

        int[] parent=new int[graph.getVernum()];

        int maxFlow=0;
//退不出来循环
        while(BFS(residual,graph.getVernum(),s,t,parent)){ //parent记录了s->t的增广路径
            int pathFlow=Integer.MAX_VALUE;
            for(int p=t;p!=s;p=parent[p]){ //从v->s搜索
                int q=parent[p]; //增广路径的最后一条边
                pathFlow=pathFlow<residual[q][p] ? pathFlow : residual[q][p];// 寻找增广路径中的每条边传输的网络流量最大量为p的残留容量

            }
            for(int p=t;p!=s;p=parent[p]){
                int q=parent[p];
                residual[q][p]-=pathFlow;
                residual[p][q]+=pathFlow;
            }
            maxFlow+=pathFlow;
        }
        return maxFlow;

    }

    public boolean BFS(int[][] residual,int vernum,int s,int t,int[] parent){ //广度优先搜索算法
        Queue<Integer> queue=new LinkedList<Integer>();
        int visited[]=new int[vernum];
        for(int i=0;i<vernum;i++){
            visited[i]=0;
        }

        visited[s]=1; //源节点访问
        parent[s]=-1;
        queue.add(s);
        while(!queue.isEmpty()){
            int u=queue.poll();
            for(int v=0;v<vernum;v++){
                if(residual[u][v]!=0 && residual[u][v]!=Integer.MAX_VALUE){
                    if(visited[v]==0){
                        visited[v]=1;
                        parent[v]=u;
                        queue.add(v);
                    }
                }
            }
        }
        return visited[t]==1;
    }

    public MaxStreamGraph createMaxStreamGraph(){ //创建最大流图
        char[] vexs=new char[]{'A','B','C','D','E','F'};
        int[][] arcs=new int[][]{
                {0,16,13,Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE},
                {Integer.MAX_VALUE,0,10,12,Integer.MAX_VALUE,Integer.MAX_VALUE},
                {Integer.MAX_VALUE,4,0,Integer.MAX_VALUE,14,Integer.MAX_VALUE},
                {Integer.MAX_VALUE,Integer.MAX_VALUE,9,0,Integer.MAX_VALUE,20},
                {Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE,7,0,4},
                {Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE,0}
        };
        MaxStreamGraph graph=new MaxStreamGraph(vexs,arcs,6,10);
        return graph;
    }
}
class MaxStreamGraph{
    private char[] vexs;
    private int[][] arcs;
    private int vernum;
    private int arcnum;

    public MaxStreamGraph(char[] vexs, int[][] arcs, int vernum, int arcnum) {
        this.vexs = vexs;
        this.arcs = arcs;
        this.vernum = vernum;
        this.arcnum = arcnum;
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
