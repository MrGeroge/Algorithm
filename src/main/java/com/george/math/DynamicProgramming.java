package com.george.math;

/**
 * Created by George on 2017/9/19.
 *
 * 动态规划问题的两个要素：
 * 1）最优子结构
 * 在找寻最优子结构时可遵循一种共同的模式：
 * 问题的一个解可以是做一个选择
 * 假设对一个给定的问题，已知的是一个可以导致最优解的选择
 * 在已知这个选择后，要确定哪些子问题会随之发生，以及如何最好的描述所得子问题空间（尽可能简单，在需要的时候再扩展）
 * 利用剪贴技术，来证明一个最优解中，使用的子问题的解也是最优的
 *
 * 最优子结构在问题域中的两种方式变化：
 * 有多少个子问题被使用在原问题的一个最优解中
 * 决定一个最优解中使用哪些子问题时有多少个选择
 *
 * 动态规划问题的运行时间取决于子问题的总数和每个子问题有多少种选择
 *
 * 问题解的代价包括子问题的代价加上选择本身带来的开销
 *
 * 动态规划适合解决无权最短路径问题，比如原问题是求解从s->t的最短路径，其子问题可以为s->w,w->t即求从s->w和从w->t的最短路径之和即为愿问题的最优解
 * 2）重叠子问题,即子问题的空间要"很小"，原问题的递归算法可重复解同样的子问题，将子问题的解保存在表中便于查找，需要用表格保存最优解比如l[][]或者s[][]
 *
 * 动态规划(局部最优->全局最优)问题的四步：
 * 1.描述最优解的结构
 * 2.递归定义最优解的值
 * 3.从底向上计算最优解的值
 * 4.由计算的结果构造最优解
 *
 * 解决实际问题：装配线调度,即存在两个装配线，每个装配线有多个装配站，起点到S1,1或者S2,1的时间是ei，离开的时间是xi，跨装配线的时间是ti,j，其最短的装配时间
 * 1.描述最优解的结构：将一个问题（起点到装配站Si,j的最快路线）分解成若干子问题(起点到S1,j-1或者S2,j-1的最快路径)，称为最优子结构
 * 2.递归定义最优解的值：f*=min(f1[n]+x1,f2[n]+x2),即起点到终点的最快路径
 * 最优解的值f1[j]=min{f1(j-1)+a1,j;f2[j-1]+t2,j-1+a1,j}(j>=2)
 *          f1[1]=e1+a1,1 (j==1)
 *
 *
 *          f2[j]=min{f2[j-1]+a2,j;f1[j-1]+t1,j-1+a2,j}
 *          f2[1]=e2+a2,1 (j==1)
 *          li[j]=p 则最优路径使用Sp,j-1
 *  3.计算最优解的值
 *  r1(n)=r2(n)=f
 *  r[j]=r1(j+1)+r2(j+1)
 *
 *  4.根据计算的结果构造最优解
 *
 */

public class DynamicProgramming {
    public static void main(String[] args){
        int a[][]=new int[2][6];
        a[0][0]=7;
        a[0][1]=9;
        a[0][2]=3;
        a[0][3]=4;
        a[0][4]=8;
        a[0][5]=4;
        a[1][0]=8;
        a[1][1]=5;
        a[1][2]=6;
        a[1][3]=4;
        a[1][4]=5;
        a[1][5]=7;
        int t[][]=new int[2][5];
        t[0][0]=2;
        t[1][0]=2;
        t[0][1]=3;
        t[1][1]=1;
        t[0][2]=1;
        t[1][2]=2;
        t[0][3]=3;
        t[1][3]=2;
        t[0][4]=4;
        t[1][4]=1;
        int e[]=new int[2];
        e[0]=2;
        e[1]=4;
        int x[]=new int[2];
        x[0]=3;
        x[1]=2;
        int l[][]=new int[2][6];
        DynamicProgramming dp=new DynamicProgramming();
        Result result=dp.fastestWay(a,t,e,x,6,l);
        dp.printStations(l,result,6);
    }

    public Result fastestWay(int a[][],int t[][],int e[],int x[],int n,int l[][]){//寻找起点到Si,j的最快路径
     Result result=new Result();
      int f[][]=new int[2][n];  //fi，j即为最优值
        //int l[][]=new int[2][n];
        for(int p=0;p<2;p++){
            for(int q=0;q<n;q++){
              f[p][q]=0;
            }
        }
        f[0][0]=e[0]+a[0][0]; //调度线编号与调度站编号都从0开始计数
        f[1][0]=e[1]+a[1][0];
        for(int h=1;h<n;h++){
            if((f[0][h-1]+a[0][h])<=(f[1][h-1]+t[1][h-1]+a[0][h])){//找到两种方案的更优值
                f[0][h]=f[0][h-1]+a[0][h];
                l[0][h]=0;//即其前一个站点为S0,h-1
            }
            else{
                f[0][h]=f[1][h-1]+t[1][h-1]+a[0][h];
                l[0][h]=1; //即其前一个站点为S1,h-1
            }
            if((f[1][h-1]+a[1][h])<=(f[0][h-1]+t[0][h-1]+a[1][h])){
                f[1][h]=f[1][h-1]+a[1][h];
                l[1][h]=1;
            }
            else{
                f[1][h]=f[0][h-1]+t[0][h-1]+a[1][h];
                l[1][h]=0;
            }
        }//得到f[0][n-1]和f[1][n-1]

        if((f[0][n-1]+x[0])<=(f[1][n-1]+x[1])){
            result.cost=f[0][n-1]+x[0];
            result.dest=0;
        }
        else{
            result.cost=f[1][n-1]+x[1];
            result.dest=1;
        }
        return result;
    }

    public void printStations(int l[][],Result result,int n){
        int i=result.dest;
        System.out.println("line="+i+" station="+(n-1));
        for(int j=(n-1);j>=1;j--){
            i=l[i][j];
            System.out.println("line="+i+" station="+(j-1));
        }
        System.out.println("cost ="+result.cost);
    }
}
class Result{
    public int cost; //所花时间
    public int dest; //最终站点属于哪个调度线
}
