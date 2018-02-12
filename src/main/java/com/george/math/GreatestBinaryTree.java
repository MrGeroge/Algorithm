package com.george.math;

/**
 * Created by George on 2017/9/20.
 *
 * 最优二叉树标准是一次搜索的期望值最小
 * 每个内节点K=（k1,k2,k3,...,kn)关键字有序构造一个二叉树
 * 有n+1个"虚拟键"（d0,d1,d2,...,dn)表示不在K内的值，虚拟键di位于ki与ki+1之间的值
 *
 *二叉树一次搜索的期望值E(T)=(dep(ki)+1)*pi+(dep(di)+1)*qi
 *
 * 最优二叉树的动态规划步骤：
 * 1.描述最优解的结构（寻找最优子结构）寻找{k1,k2,...,kn}构成的最优二叉树，即其子问题为寻找{ki,ki+1,...,kj}和{di-1,...,dj}构成的最优二叉树，由于{ki,ki+1,...,kj}有序，一旦确定了二叉树的根节点，则整个二叉树便可以构建，，kr为其根（i<=r<=j)，分别计算出不同根下构建的二叉树的期望值，取期望值最小情况即为最优二叉树
 * 至于空树的处理1）若根取ki,di-1为唯一虚拟键2）若根取kj,dj为唯一虚拟键
 * 2.递归定义最优解的值e[i,j]为ki,ki+1,...,kj构成的最优二叉树的期望值（i>=1,j<=n且j>=i-1)
 * 1）j=i-1 e[i,i-1]=qi-1
 * 2)j>=i min{e[i,r-1]+e[r+1,j]+w(i,j)}
 *3.自底向下计算最优解（计算最优二叉树的期望值)
 * 使用root[i,j]保存由{ki,ki+1,...,kj}构成的最优二叉树对应的根节点kr
 * w[i,j]=qi-1 (j=i-1)
 * w[i,j]=w[i,j-1]+pj+qj
 *
 *
 *
 *
 */


public class GreatestBinaryTree { //最优二叉树
    public static void main(String[] args){
        double p[]=new double[6];
        p[0]=0;
        p[1]=0.15;
        p[2]=0.10;
        p[3]=0.05;
        p[4]=0.10;
        p[5]=0.20;
        double q[]=new double[6];
        q[0]=0.05;
        q[1]=0.10;
        q[2]=0.05;
        q[3]=0.05;
        q[4]=0.05;
        q[5]=0.10;
        double w[][]=new double[7][6];
        double e[][]=new double[7][6];
        int root[][]=new int[6][6];
        GreatestBinaryTree greatestBinaryTree=new GreatestBinaryTree();
        greatestBinaryTree.optimalBST(p,q,5,w,e,root);
         System.out.println("e[1][5]="+e[1][5]);
        System.out.println("root[1][5]"+root[1][5]);
    }
    public void optimalBST(double p[],double q[],int n,double w[][],double e[][],int root[][]){//求解最优二叉树
        for(int i=1;i<=(n+1);i++){ //考虑空树情况(j=i-1),只存在虚拟键di-1
            e[i][i-1]=q[i-1];
            w[i][i-1]=q[i-1];
        }
        for(int l=1;l<=n;l++){
            for(int i=1;i<=(n-l+1);i++){
                int j=i+l-1;
                e[i][j]=Integer.MAX_VALUE; //因为要找到e[i][j]的最小值
                w[i][j]=w[i][j-1]+p[j]+q[j];
                for(int r=i;r<=j;r++){ //取根节点
                    double t=e[i][r-1]+e[r+1][j]+w[i][j];
                    if(t<e[i][j]){
                        e[i][j]=t;
                        root[i][j]=r;
                    }
                }
            }
        }
    }


}
