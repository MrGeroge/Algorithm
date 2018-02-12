package com.george.math;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;

/**
 * Created by George on 2017/10/8.
 *
 * 最短路径的最优子结构：
 * 如果(v1,v2,...,vk)是v1到vk的最短路径,则1<=i<=j<=k,则(vi.vi+1,...,vj)是vi到vj的最短路径
 *
 * 负权值边:如果vi->vj有回路，且回路的权值为负值，则标记vi->vj的最短路径是负无穷
 *
 * 一条最短路径不可能包含负权回路，也不会包含正权回路
 *
 * 利用广度优先搜索算法可以得到一个以s为根的有跟图（最小路径树），s到每个节点v的简单路径即为最小路径
 *
 * 基本思路均是调用INIYIALIZE-SINGLE-SOURCE进行初始化，然后重复对边进行松弛的过程，松弛是改变最短路径以及前驱的唯一方式，单源最短路径算法的区别在于对每条边进行松弛操作的次数
 *
 * 单源最短路径典型算法：
 * 1.Bellman-Ford算法:解决（边的权值可以为负数）的单源最短路径问题，可检测是否有从源点可达的负权回路，Dijkstra算法要求所有边的权值为非负
 * 基本思路：
 * 1）对图进行初始化
 * 2）执行vernum-1次迭代，每次迭代遍历图中每条边，对每条边进行松弛操作
 * 3）遍历每条边(u,v)，如果该边仍然满足松弛条件，则说明(u,v)存在负权回路返回FLASE，否则返回True
 *
 * 2.有向无环图（DAG）总是存在最短路径
 * 1)对DAG图做拓扑排序
 * 2）初始化d，parent
 * 3）按照拓扑排序遍历图中每个节点u
 * 4）遍历节点u的所有邻接节点v
 * 5）对边(u,v)进行松弛操作
 *
 * 求DAG的关键路径（通过dag的最长路径）的方法：
 * 1）对边的权值取负值
 * 或者
 * 2）将Initialize-Single-Source的"正无穷"换为"负无穷"，并将relax的">"换成"<"
 *
 *3.Dijkstra算法只适用于有向带权图且权值只能为非负数
 *
 * FLOYD_WARSHALL动态规划算法
 * 1.描述最优子结构
 * 1）节点k不是p的中间节点，p上的所有中间节点都属于{1,2，...,k-1}
 * 2）节点k是p的中间节点，则路径p分解为i->k->j，标记为子路径p1和p2，p1或者p2的中间节点来自于{1,2，...,k-1}
 *
 * 2.递归求解最优子结构
 * dij(k)= wij k=0
 * dij(k)=min{dij(k-1),dik(k-1)+dkj(k-1)}
 *
 * 3.自底向上求解最优解D(n)
 *
 * 4.求有向图的传递闭包
 * 判断F中是否存在一条由i到j的路径，方法是：
 * 对图中每个边的权值置1，运行Floyd——Warshall算法得到Dk，若dij<n则存在i到j的路径，否则dij=正无穷
 */
public class SingleSourceMinLoad {//单源最小路径算法
    public static void main(String[] args) {
        SingleSourceMinLoad load = new SingleSourceMinLoad();
        SingleSourceGraph graph = load.createGraph();
        int d[] = new int[graph.getVernum()];
        int parent[] = new int[graph.getVernum()];
        boolean status = load.bellmanFord(graph, 0, d, parent);
        if (status) {
            load.printPath(graph, 0, 3, parent);
        } else {
            System.out.print("exists no path");
        }
        System.out.println("###################");

        SingleSourceGraph dag = load.createDAG(); //创建DAG
        //load.topologicalSort(dag);
        d = new int[dag.getVernum()];
        parent = new int[dag.getVernum()];
        load.dagShortestPath(dag, 1, d, parent);
        load.printPath(dag, 1, 5, parent);

        System.out.println("###################");

        SingleSourceGraph dijGraph = load.createDijkstraGraph();
        d = new int[dijGraph.getVernum()];
        parent = new int[dijGraph.getVernum()];
        List<Integer> S = new ArrayList<Integer>();
        List<Integer> Q = new ArrayList<Integer>();
        load.dijkstra(dijGraph, 0, d, parent, S, Q);
        load.printPath(dijGraph, 0, 3, parent);

        System.out.println("###################");
        for (Integer i : S) {
            System.out.print(dijGraph.getVexs()[i]);
        }

        SingleSourceGraph shortestPathGraph = load.createExtendShortestGraph();
        /*int[][] L=load.slowAllPairsShortestPath(shortestPathGraph);
        for(int i=0;i<5;i++){
            for(int j=0;j<5;j++){
                System.out.print(L[i][j]+" ");
            }
            System.out.println();
        }*/

        int[][] faster = load.fasterSlowAllPairsShortestPath(shortestPathGraph);
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                System.out.print(faster[i][j] + " ");
            }
            System.out.println();
        }

        SingleSourceGraph floydWarshallGraph=load.createFloydWarshallGraph();
        int[][] parent_tmp=new int[floydWarshallGraph.getVernum()][floydWarshallGraph.getVernum()];
        FloydResult result=load.floydWarshall(floydWarshallGraph);
        int[][] Dk=result.getDk();
        parent_tmp=result.getParentk();
        for(int i=0;i<floydWarshallGraph.getVernum();i++){
            for(int j=0;j<floydWarshallGraph.getVernum();j++){
                System.out.print(Dk[i][j]+" ");
            }
            System.out.println();
        }

        System.out.println("%%%%%%%%%%%%%%%%%%");
        for(int i=0;i<floydWarshallGraph.getVernum();i++){
            for(int j=0;j<floydWarshallGraph.getVernum();j++){
                System.out.print(parent_tmp[i][j]+" ");
            }
            System.out.println();
        }

        load.printAllPairsShortestPath(floydWarshallGraph,0,2,parent_tmp);
    }

    public void printAllPairsShortestPath(SingleSourceGraph graph,int s,int v,int[][] parent){
        if(s==v){
            System.out.print(graph.getVexs()[s]);
        }
        else if(parent[s][v]==-1){
            System.out.print("no path from"+graph.getVexs()[s]+"to"+graph.getVexs()[v]+"exists");
        }
        else{
            printAllPairsShortestPath(graph,s,parent[s][v],parent);
            System.out.print(graph.getVexs()[v]);
        }
    }

    public FloydResult floydWarshall(SingleSourceGraph graph){
        int n=graph.getVernum(); //顶点数
        int[][] D0=graph.getArcs(); //W
        int[][] parent0=new int[n][n];
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                if(i==j || graph.getArcs()[i][j]==Integer.MAX_VALUE){
                    parent0[i][j]=-1;
                }
                else{
                    parent0[i][j]=i;//j的前驱是i
                }
            }
        }
        int[][] parent=parent0;
        int[][] tmp=D0;
        for(int k=0;k<n;k++){ //自底向上求解D0,D1,...D(n-1)
            int[][] Dk=new int[n][n];
            int[][] parentk=new int[n][n];
            for(int i=0;i<n;i++){
                for(int j=0;j<n;j++){
                    if(tmp[i][k]==Integer.MAX_VALUE || tmp[k][j]==Integer.MAX_VALUE){
                        Dk[i][j]=tmp[i][j];
                        parentk[i][j]=parent[i][j];
                    }
                    else{
                        Dk[i][j]=Math.min(tmp[i][j],tmp[i][k]+tmp[k][j]);
                        if(tmp[i][j]<=(tmp[i][k]+tmp[k][j])){
                            parentk[i][j]=parent[i][j];
                        }
                        else{
                            parentk[i][j]=parent[k][j];
                        }
                    }

                }
            }
            tmp=Dk;
            parent=parentk;
        }
        FloydResult result=new FloydResult(tmp,parent);
        return result;
    }

    public SingleSourceGraph createFloydWarshallGraph(){
        char[] vexs=new char[]{'A','B','C','D','E'};
        int[][] arcs=new int[][]{
                {0,3,8,Integer.MAX_VALUE,-4},
                {Integer.MAX_VALUE,0,Integer.MAX_VALUE,1,7},
                {Integer.MAX_VALUE,4,0,Integer.MAX_VALUE,Integer.MAX_VALUE},
                {2,Integer.MAX_VALUE,-5,0,Integer.MAX_VALUE},
                {Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE,6,0}
        };
        SingleSourceGraph graph=new SingleSourceGraph(vexs,arcs,5,9);
        return graph;
    }


    public SingleSourceGraph createGraph(){
        char[] vexs=new char[]{'A','B','C','D','E'};
        int[][] arcs=new int[][]{
                {0,6,Integer.MAX_VALUE,Integer.MAX_VALUE,7},
                {Integer.MAX_VALUE,0,5,-4,8},
                {Integer.MAX_VALUE,-2,0,Integer.MAX_VALUE,Integer.MAX_VALUE},
                {2,Integer.MAX_VALUE,7,0,Integer.MAX_VALUE},
                {Integer.MAX_VALUE,Integer.MAX_VALUE,-3,9,0}
        };
        SingleSourceGraph graph=new SingleSourceGraph(vexs,arcs,5,10);
        return graph;
    }

    /**
     *
     * 任意俩节点之间的最短路径，考虑动态规划
     * 1）寻找最优子结构 p(i,j)=p(i,k)+wkj p(i,j)至多包含m个边的从i到j节点的最小权值，p(i,k)至多包含m-1条边
     * 2)递归求解最优子结构
     *  l(0)=0 i==j
     *  l(0)=正无穷 i!=j
     *
     *  l(1)=Wij
     *
     *  m>=1时 l(m)=min(l(m-1)ij,min{l(m-1)ik+wkj}) k>=1 && k<=m
     *
     * 3)自底向上求解最短路径权值
     * L(1),L(2),...,L(m)
     */
    public SingleSourceGraph createExtendShortestGraph(){
        char[] vexs=new char[]{'A','B','C','D','E'};
        int[][] arcs=new int[][]{
                {0,3,8,Integer.MAX_VALUE,-4},
                {Integer.MAX_VALUE,0,Integer.MAX_VALUE,1,7},
                {Integer.MAX_VALUE,4,0,Integer.MAX_VALUE,Integer.MAX_VALUE},
                {2,Integer.MAX_VALUE,-5,0,Integer.MAX_VALUE},
                {Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE,6,0}
        };
        SingleSourceGraph graph=new SingleSourceGraph(vexs,arcs,5,9);
        return graph;
    }

    public int[][] extendShortestPath(SingleSourceGraph graph,int[][] l,int n){ //l=l(m-1)
        int[][] l1=new int[n][n];
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                l1[i][j]=Integer.MAX_VALUE;
                for(int k=0;k<n;k++){
                    if(l[i][k]==Integer.MAX_VALUE || graph.getArcs()[k][j]==Integer.MAX_VALUE){

                    }
                    else{
                        l1[i][j]=Math.min(l1[i][j],l[i][k]+graph.getArcs()[k][j]);
                    }

                }
            }
        }
        return l1;
    }

    public int[][] fasterExtendShortestPath(int[][] l,int[][] m,int n){
        int[][] l1=new int[n][n];
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                l1[i][j]=Integer.MAX_VALUE;
                for(int k=0;k<n;k++){
                    if(l[i][k]==Integer.MAX_VALUE || m[k][j]==Integer.MAX_VALUE){

                    }
                    else{
                        l1[i][j]=Math.min(l1[i][j],l[i][k]+m[k][j]);
                    }
                }
            }
        }
        return l1;
    }

    public int[][] fasterSlowAllPairsShortestPath(SingleSourceGraph graph){
        int n=graph.getVernum();
        int[][] w=graph.getArcs();
        int m=1;
        while(m<n-1){
            w=fasterExtendShortestPath(w,w,n);
            m=2*m;
        }
        return w;
    }

    public int[][] slowAllPairsShortestPath(SingleSourceGraph graph){ //展示任意节点i，j至多包含m边的最短路径
        int n=graph.getVernum();
        int[][] tmp=graph.getArcs();
        for(int m=2;m<=(n-1);m++){
            tmp=extendShortestPath(graph,tmp,n);
        }
        return tmp;
    }

    public void dagShortestPath(SingleSourceGraph dag,int s,int[] d,int[] parent){ //单源节点s的最短路径
        Stack<Integer> stack=new Stack<Integer>();
        topologicalSort(dag,stack); //拓扑排序
        initializeSingleSource(dag,s,d,parent);
        while(!stack.isEmpty()){
            int u=stack.pop();
            for(int i=0;i<dag.getVernum();i++){
                if(dag.getArcs()[u][i]!=0 && dag.getArcs()[u][i]!=Integer.MAX_VALUE){
                    relax(d,parent,u,i,dag.getArcs()[u][i]);
                }
            }
        }
    }

    public void DFS(SingleSourceGraph graph,int u,int[] visit,Stack<Integer> s){ //深度优先搜索
        visit[u]=1;
        for(int i=0;i<graph.getVernum();i++){
            if(graph.getArcs()[u][i]!=0 && graph.getArcs()[u][i]!=Integer.MAX_VALUE){ //i为邻接节点
                if(visit[i]==0){ //邻接点未访问
                    DFS(graph,i,visit,s);
                }
            }
        }
        s.push(u); //最先访问的节点在栈底
    }

    public void topologicalSort(SingleSourceGraph graph,Stack<Integer> stack){ //拓扑排序
        int[] visit=new int[graph.getVernum()];
        for(int i=0;i<graph.getVernum();i++){
            visit[i]=0;
        }
        for(int i=0;i<graph.getVernum();i++){
            if(visit[i]==0){
                DFS(graph,i,visit,stack);
            }
        }
    }

    public SingleSourceGraph createDAG(){//创建有向无环图
        char[] vexs=new char[]{'A','B','C','D','E','F'};
        int[][] arcs=new int[][]{
                {0,5,3,Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE},
                {Integer.MAX_VALUE,0,2,6,Integer.MAX_VALUE,Integer.MAX_VALUE},
                {Integer.MAX_VALUE,Integer.MAX_VALUE,0,7,4,2},
                {Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE,0,-1,1},
                {Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE,0,-2},
                {Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE,0}
        };
        SingleSourceGraph graph=new SingleSourceGraph(vexs,arcs,6,10);
        return graph;

    }

    public SingleSourceGraph createDijkstraGraph(){
        char[] vexs=new char[]{'A','B','C','D','E'};
        int[][] arcs=new int[][]{
                {0,10,Integer.MAX_VALUE,Integer.MAX_VALUE,5},
                {Integer.MAX_VALUE,0,1,Integer.MAX_VALUE,2},
                {Integer.MAX_VALUE,Integer.MAX_VALUE,0,4,Integer.MAX_VALUE},
                {7,Integer.MAX_VALUE,6,0,Integer.MAX_VALUE},
                {Integer.MAX_VALUE,3,9,2,0}
        };
        SingleSourceGraph graph=new SingleSourceGraph(vexs,arcs,5,10);
        return graph;
    }

    public void dijkstra(SingleSourceGraph graph,int s,int[] d,int[] parent,List<Integer> S,List<Integer> Q){ //以s为起点的最短路径
        initializeSingleSource(graph,s,d,parent);
        for(int i=0;i<graph.getVernum();i++){
            Q.add(i);
        }
        while(!Q.isEmpty()){
            int u=extractMIn(Q,d); //u为d最小的节点
            S.add(u);
            for(int j=0;j<graph.getVernum();j++){
                if(graph.getArcs()[u][j]!=0 && graph.getArcs()[u][j]!=Integer.MAX_VALUE){
                    relax(d,parent,u,j,graph.getArcs()[u][j]);
                }
            }
        }
    }

    public int extractMIn(List<Integer> list,int[] d){//返回d最小节点，且在list中删除此节点
        int minValue=d[list.get(0)];
        int minIndex=list.get(0);
        for(int i=1;i<list.size();i++){
            if(d[list.get(i)]<minValue){
                minValue=d[list.get(i)];
                minIndex=list.get(i);
            }
        }
        Iterator<Integer> it=list.iterator();
        while(it.hasNext()){
            int vex=it.next();
            if(vex==minIndex){
                it.remove();
                return minIndex;
            }
        }
        return -1;
    }

    public void initializeSingleSource(SingleSourceGraph graph,int s,int[] d,int[] parent){ //从源节点s出发初始化
        for(int i=0;i<graph.getVernum();i++){ //遍历图中每个节点
            d[i]=Integer.MAX_VALUE;
            parent[i]=-1;
        }
        d[s]=0;
    }

    public void relax(int[] d,int[] parent,int u,int v,int w){ //对边(u,v)松弛操作
        //考虑边界情况处理
        if(d[u]==Integer.MAX_VALUE && d[v]==Integer.MAX_VALUE && w>0){
            return;
        }
        if(d[u]==Integer.MAX_VALUE && d[v]==Integer.MAX_VALUE && w<0){
            d[v]=d[u]+w;
            parent[v]=u;
            return;
        }
        if(d[u]<Integer.MAX_VALUE && d[v]==Integer.MAX_VALUE){
            d[v]=d[u]+w;
            parent[v]=u;
            return;
        }
        if(d[u]==Integer.MAX_VALUE && d[v]<Integer.MAX_VALUE){
            return;
        }
        if(d[u]<Integer.MAX_VALUE && d[v]<Integer.MAX_VALUE){
            if(d[v]>d[u]+w){
                d[v]=d[u]+w;
                parent[v]=u;
            }
            return;
        }


    }

    public void printPath(SingleSourceGraph graph,int s,int v,int[] parent){ //打印最短路径
        if(s==v){
            System.out.print(graph.getVexs()[s]);
        }
        else if(parent[v]==-1){
            System.out.print("no path from"+graph.getVexs()[s]+"to"+graph.getVexs()[v]+"exists");
        }
        else{
            printPath(graph,s,parent[v],parent);
            System.out.print(graph.getVexs()[v]);
        }
    }

    public boolean bellmanFord(SingleSourceGraph graph,int s,int[] d,int[] parent){
        initializeSingleSource(graph,s,d,parent); //初始化图
        for(int m=1;m<graph.getVernum()-1;m++){//需要进行vernum-1次迭代
            for(int i=0;i<graph.getVernum();i++){
                for(int j=0;j<graph.getVernum();j++){
                    if(graph.getArcs()[i][j]!=0 && graph.getArcs()[i][j]!=Integer.MAX_VALUE){//遍历每条边
                        relax(d,parent,i,j,graph.getArcs()[i][j]);
                    }
                }
            }
        }
        for(int i=0;i<graph.getVernum();i++){
            for(int j=0;j<graph.getVernum();j++){
                if(graph.getArcs()[i][j]!=0 && graph.getArcs()[i][j]!=Integer.MAX_VALUE){
                   if(d[j]>d[i]+graph.getArcs()[i][j]){
                       return false;
                   }
                }
            }
        }
        return true;
    }
}
class SingleSourceGraph{
    private char[] vexs;//顶点向量
    private int[][] arcs;//邻接矩阵 权值为非零，若两者不可大则取Integer.MaxValue
    private int vernum; //顶点数
    private int arcnum; //边数

    public SingleSourceGraph(char[] vexs, int[][] arcs, int vernum, int arcnum) {
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

class FloydResult{
        private int[][] Dk;
        private int[][] parentk;

    public FloydResult(int[][] dk, int[][] parentk) {
        Dk = dk;
        this.parentk = parentk;
    }

    public int[][] getDk() {
        return Dk;
    }

    public void setDk(int[][] dk) {
        Dk = dk;
    }

    public int[][] getParentk() {
        return parentk;
    }

    public void setParentk(int[][] parentk) {
        this.parentk = parentk;
    }
}
