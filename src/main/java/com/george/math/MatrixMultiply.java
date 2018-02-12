package com.george.math;

/**
 * Created by George on 2017/9/19.
 *
 * 问题描述：如何在{A1,A2...An}的矩阵相乘链中找到代价最小的矩阵相乘顺序
 * 1.描述最优解的结构：（寻找最优子结构）
* AiAi+1...Aj的最优解应该是AiAi+1...Ak的最优子解加上Ak+1Ak+2...Aj的最优子解的和
 * 2.递归定义最优解的值
 * m[i,j]=0 (i==j)
 * m[i,j]=min{m[i,k]+m[k+1,j]+pi-1pkpj} (i<=k<j)
 * 3.至底向上计算最优解的值（计算最小代价）
 * 4.根据计算的值构造最优解
 */
public class MatrixMultiply { //矩阵相乘
    public static void main(String[] args){
        int A[][]=new int[1][2];
        int B[][]=new int[2][1];
        A[0][0]=1;
        A[0][1]=2;
        B[0][0]=1;
        B[1][0]=2;
        MatrixMultiply matrixMultiply=new MatrixMultiply();
        int C[][]=matrixMultiply.multiply(A,B,1,2,1);
        System.out.println(C[0][0]);
        int p[]=new int[7];
        p[0]=30;
        p[1]=35;
        p[2]=15;
        p[3]=5;
        p[4]=10;
        p[5]=20;
        p[6]=25;
        int m[][]=new int[7][7];
        int s[][]=new int[7][7];
        matrixMultiply.matrixChainOrder(p,m,s);
        System.out.println("m[1,6]="+m[1][6]);
        matrixMultiply.printOptimalParens(s,m,1,6);
        System.out.println(matrixMultiply.memorizedMatrixChain(p,m,1,6));
    }
    public int[][] multiply(int[][] A,int [][]B,int m,int r,int n){//A为m*r的矩阵,B为r*n的矩阵，相乘之后的结果为m*n的矩阵
        int C[][]=new int[m][n];
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                C[i][j]=0;
                for(int k=0;k<r;k++){
                    C[i][j]=C[i][j]+A[i][k]*B[k][j];
                }
            }
        }
        return C;
    }

    //自底向上动态规划解决
    public void matrixChainOrder(int p[],int m[][],int s[][]){
        int n=p.length-1; //矩阵链长度
        for(int i=1;i<=n;i++){
            m[i][i]=0;
        }
        for(int l=2;l<=n;l++){  //l为链的长度
            for(int i=1;i<=(n-l+1);i++){//指定l下的链起始坐标，即可从1～（n-l+1)
                int j=(i+l-1);  //指定l下的链终止坐标，即只能为i+l-1
                m[i][j]=Integer.MAX_VALUE;
                for(int k=i;k<=j-1;k++){
                    int tmp=m[i][k]+m[k+1][j]+p[i-1]*p[k]*p[j];
                    if(tmp<m[i][j]){
                        m[i][j]=tmp;
                        s[i][j]=k;
                    }
                }
            }
        }
    }
    //自顶向下带备忘录的递归调用
    public int memorizedMatrixChain(int p[],int m[][],int h,int g){
        int n=p.length-1;
        for(int i=1;i<=n;i++){
            for(int j=1;j<=n;j++){
                m[i][j]=Integer.MAX_VALUE;
            }
        }
        return lookupChain(p,m,h,g);
    }

    public int lookupChain(int p[],int m[][],int i,int j){
        if(m[i][j]<Integer.MAX_VALUE){ //查找m[i][j]
            return m[i][j];
        }
        else{
            if(i==j){
                m[i][j]=0;
            }
            else{
                for(int k=i;k<j;k++){
                    int q=lookupChain(p,m,i,k)+lookupChain(p,m,k+1,j)+p[i-1]*p[k]*p[j];
                    if(q<m[i][j]){
                        m[i][j]=q;
                    }
                }

            }
            return m[i][j];
        }
    }

    public void printOptimalParens(int s[][],int m[][],int i,int j){
        //System.out.println("m["+i+"]"+"["+j+"]="+m[i][j]);
        if(i==j){
            System.out.print("A"+i);
        }
        else{
            System.out.print("(");
            printOptimalParens(s,m,i,s[i][j]);
            printOptimalParens(s,m,s[i][j]+1,j);
            System.out.print(")");
        }
    }
}
