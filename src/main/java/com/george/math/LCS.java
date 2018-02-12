package com.george.math;

/**
 * Created by George on 2017/9/20.
 *
 * 一.描述问题：求解集合X{x1,x2,x3,...,xm}和集合Y{y1,y2,y3,...,yn}的最长公共子序列Z
 *
 * 二.动态规划解决LCS问题
 * 1.描述最优解的结构（描述最长公共子序列)
 * 1）如果xm=yn,那么zk=xm=yn,而且Zk-1是Xm-1和Yn-1的一个LCS
 * 2)如果xm!=yn,那么zk!=xm蕴含着Z是Xm-1和Y的一个LCS
 * 3)如果xm!=yn,那么zk!=yn蕴含着Z是X和Yn-1的一个LCS
 *
 * 2.递归定义最优解的值(递归定义LCS)
 * 如果xm=yn,则只需要找出Xm-1和Yn-1的一个LCS，然后加上xm或者yn即为最优解
 * 如果xm!=yn,则需要先找出Xm-1和Y的一个LCS以及Xm和Yn-1的一个LCS，然后比较两者长度，取长者
 *
 * c[i][j]表示Xi与Yj的LCS的长度
 *
 * c[i,j]=0 (i=0或者j=0)
 * c[i,j]=c[i-1,j-1]+1 (i>0,j>0和xi==yj)
 * c[i,j]=max{c[i-1,j],c[i,j-1]} (i>0,j>0和xi!=yj)
 *
 * 3.从底向上计算最优解的值(计算LCS)
 *
 * 4.构造最优解（LCS)
 *
 *
 */
public class LCS {
    public static void main(String[] args){
        char x[]=new char[8];
        x[0]='x';
        x[1]='A';
        x[2]='B';
        x[3]='C';
        x[4]='B';
        x[5]='D';
        x[6]='A';
        x[7]='B';
        char y[]=new char[7];
        y[0]='y';
        y[1]='B';
        y[2]='D';
        y[3]='C';
        y[4]='A';
        y[5]='B';
        y[6]='A';
        int c[][]=new int[8][7];
        String b[][]=new String[8][7];
        LCS lcs=new LCS();
        lcs.lcsLength(x,y,7,6,c,b);
        lcs.printLCS(b,x,7,6);
    }
    public void lcsLength(char x[],char y[],int m,int n,int c[][],String b[][]){ //计算Xm和Yn的LCS
        for(int i=1;i<=m;i++){
            c[i][0]=0;
        }
        for(int j=0;j<=n;j++){
            c[0][j]=0;
        }
        for(int i=1;i<=m;i++){
            for(int j=1;j<=n;j++){
                if(x[i]==y[j]){
                    c[i][j]=c[i-1][j-1]+1;
                    b[i][j]="↖️";
                }
                else{
                    if(c[i-1][j]>=c[i][j-1]){
                        c[i][j]=c[i-1][j];
                        b[i][j]="⬆️";
                    }
                    else{
                        c[i][j]=c[i][j-1];
                        b[i][j]="⬇️";
                    }
                }
            }
        }
    }
    public void printLCS(String b[][],char x[],int i,int j){//打印出Xi与Yj的LCS
        if(i==0 || j==0){
            return;
        }
        else{
            if(b[i][j].equals("↖️")){
                printLCS(b,x,i-1,j-1);
                System.out.print(x[i]);
            }
            else if(b[i][j].equals("⬆️")){
                printLCS(b,x,i-1,j);
            }
            else{
                printLCS(b,x,i,j-1);
            }
        }

    }
}
