package com.george.math;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by George on 2017/9/20.
 *
 *
 */
public class ActivitySelect {  //活动选择
    public static void main(String[] args){
        int s[]=new int[13];
        s[0]=0;
        s[1]=1;
        s[2]=3;
        s[3]=0;
        s[4]=5;
        s[5]=3;
        s[6]=5;
        s[7]=6;
        s[8]=8;
        s[9]=8;
        s[10]=2;
        s[11]=12;
        s[12]=Integer.MAX_VALUE;
        int f[]=new int[13];
        f[0]=0;
        f[1]=4;
        f[2]=5;
        f[3]=6;
        f[4]=7;
        f[5]=8;
        f[6]=9;
        f[7]=10;
        f[8]=11;
        f[9]=12;
        f[10]=13;
        f[11]=14;
        f[12]=0;
        String a[]=new String[12];
        for(int j=0;j<12;j++){
            a[j]="a"+j;
        }
        ActivitySelect as=new ActivitySelect();
        int c[][]=new int[13][13];
        as.selected(s,f,11,c);
        System.out.println(c[1][11]);
        //System.out.println(as.recursiveActivitySelector(s,f,0,11));
        ArrayList<String> list=new ArrayList<String>();
        as.recursiveActivitySelector(s,f,0,11,a,list);
        for(String str:list){
            System.out.print(str);
        }
        System.out.println("##################");
        ArrayList<String> list2=new ArrayList<String>();
        as.greedyActivitySelector(s,f,11,a,list2);
        for(String str:list2){
            System.out.print(str);
        }
    }
    public void selected(int s[],int f[],int n,int c[][]){
        for(int i=0;i<=(n+1);i++){
            for(int j=0;j<=(n+1);j++){
                if(i>=j){
                    c[i][j]=0;
                }
                else{
                    c[i][j]=-1;
                    for(int k=(i+1);k<j;k++){
                        if(f[i]<=s[k] && s[k]<f[k] && f[k]<=s[j]){ //ak在此Aij集合中
                           int t=c[i][k]+c[k][j]+1;
                            if(t>c[i][j]){
                                c[i][j]=t;
                            }
                        }
                    }
                }
            }
        }
    }
    //递归版本
    public void recursiveActivitySelector(int s[], int f[], int i, int n, String a[],ArrayList<String> list){
        int m=i+1;
        while(m<=n && s[m]<f[i]){//找到ai活动结束后的第一个活动，即与ai兼容的活动
            m=m+1;
        }
        if(m<=n){
            list.add(a[m]);
            recursiveActivitySelector(s,f,m,n,a,list);
        }
        else{
            return;
        }
    }

    //迭代版本
    public void greedyActivitySelector(int s[],int f[],int n,String a[],ArrayList<String> list){
        int i=1;
        list.add(a[1]);
        for(int m=2;m<=n;m++){
            if(s[m]>=f[i]){
                list.add(a[m]);
                i=m;
            }
        }
    }

}
