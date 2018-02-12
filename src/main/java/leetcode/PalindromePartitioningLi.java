package leetcode;

import java.util.Stack;

/**
 * Created by George on 2017/12/5.
 */
public class PalindromePartitioningLi {
    public static void main(String[] args){
        PalindromePartitioningLi pp=new PalindromePartitioningLi();
        System.out.println();
        //System.out.print(pp.reverseString("abc"));
    }
    public int minCut(String s){
        int[] dp=new int[s.length()+1];
        boolean[][] p=new boolean[s.length()][s.length()]; //p[i][j]表示i～j之间是回文串
        dp[s.length()]=-1;
        for(int i=s.length()-1;i>=0;i--){
            dp[i]=Integer.MAX_VALUE;
            for(int j=i;j<s.length();j++){
                if(s.charAt(i)==s.charAt(j) && (j-i<2 || p[i+1][j-1])){
                    p[i][j]=true;
                    dp[i]=Math.min(dp[i],dp[j+1]+1);
                }
            }
        }
        return dp[0];
    }

}
