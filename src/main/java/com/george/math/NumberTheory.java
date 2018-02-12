package com.george.math;

/**
 * Created by George on 2017/10/11.
 *
 * 一.寻找两个整数的最大公约数
 * 采用欧几里得算法
 * gcd(a,b)=gcd(b,a mod b)
 */
public class NumberTheory { //数论

    public static void main(String[] args){
        NumberTheory nt=new NumberTheory();
        int max=nt.euclid(12,16);
        System.out.println(max);

        String s=nt.getSimpleBinString(100);
        System.out.println(s);

        int d=nt.modularExponentiation(7,560,561);
        System.out.println(d);

    }
    public int euclid(int a,int b){ //时间复杂度为O(lgb)
        if(b==0){
            return a;
        }
        else{
            return euclid(b,a%b);
        }
    }

    public  String getSimpleBinString(int number) { //将整数转换为二进制字符串
        StringBuilder sBuilder = new StringBuilder();
        for (int i = 0; i < 32; i++){
            sBuilder.append(number & 1).append(" ");
            number = number >>> 1;
        }
        int index = sBuilder.reverse().toString().trim().indexOf("1");
        return sBuilder.toString().trim().substring(index);
    }

    public int modularExponentiation(int a,int b,int n){
        int c=0;
        int d=1;
        String strB=getSimpleBinString(b);
        System.out.println(strB);
        String[] bks=strB.split(" ");
        System.out.println(bks.length);

        for(int i=bks.length-1;i>=0;i--){
           c=2*c;
            d=(d*d)%n;
            if(Integer.parseInt(bks[i])==1){
                c++;
                d=(d*a)%n;
            }
        }
       return d;
    }
}
