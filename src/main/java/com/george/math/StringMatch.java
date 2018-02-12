package com.george.math;

/**
 * Created by George on 2017/10/12.
 * 四种字符串匹配算法：
 * 1.朴素字符串匹配,最坏情况下的时间复杂度为O((n-m+1)m)
 *
 * 2.Rabin-Karp算法的预处理时间为O(m),最坏情况下的运行时间是O((n-m+1)m)
 *
 * 3.利用有限自动机进行字符串匹配需要O(m|c割妈)和O(n)个匹配时间
 *
 * 4.KMP算法 http://www.ruanyifeng.com/blog/2013/05/Knuth–Morris–Pratt_algorithm.html,预处理时间为O(m),匹配时间为O(n)
 *
 */
public class StringMatch {

    public static void main(String[] args){
        char[] s=new char[]{'a','c','a','a','b','c'};
        char[] p=new char[]{'a','a'};
        int t=StringMatch.simple(s,6,p,2);
        System.out.print(t);
        String str = "BBCABCDABABCDABCDABDE";
        String sub = "BAB";

        int index = StringMatch.kmp(str, sub);
        System.out.println("index-->"+index);

    }

    public static int simple(char[] s,int n,char[] p,int m){
        for(int t=0;t<=(n-m);t++){
            int j=0;
            for(;j<m;j++){
                if(s[t+j]==p[j]){
                    continue;
                }
                else{
                    break;
                }
            }

            if(j==m){
                return t;
            }
        }
            System.out.println("s not include p");
            return -1;
    }

    /**
     * 用于计算匹配的位置（从头到尾）
     * @param str
     * @param sub
     * @return
     */
    public static int kmp(String str, String sub) {
        if(str == null || sub == null || str.length() == 0 || sub.length() == 0){
            throw new IllegalArgumentException("str或者sub不能为空");
        }

        int j = 0;
        int[] n = next(sub);
        for (int i = 0; i < str.length(); i++) {
            while(j > 0 && str.charAt(i) != sub.charAt(j)){
                j = n[j - 1];
            }

            if(str.charAt(i) == sub.charAt(j)){
                j++;
            }

            if(sub.length() == j){
                int index = i - j + 1;
                return index;
            }
        }

        return -1;
    }

    /**
     * 用于生成部分匹配表
     * @param sub
     * @return
     */
    private static int[] next(String sub) {
        int[] n = new int[sub.length()];
        int x = 0;
        for (int i = 1; i < sub.length(); i++) {
            while (x > 0 && sub.charAt(x) != sub.charAt(x)) {
                x = n[x - 1];
            }

            if (sub.charAt(i) == sub.charAt(x)) {
                x++;
            }

            n[i] = x;
        }
        return n;
    }


}
