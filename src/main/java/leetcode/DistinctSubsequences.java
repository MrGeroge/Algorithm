package leetcode;

/**
 * Created by George on 2017/12/11.
 */
public class DistinctSubsequences {
    public int numDistinct(String S, String T) {
        int[][] dp=new int[S.length()+1][T.length()+1];
        for(int i=0;i<=T.length();i++){
            dp[0][i]=0;
        }
        for(int j=0;j<=S.length();j++){
            dp[j][0]=1;
        }
        for(int i=1;i<=S.length();i++){
            for(int j=1;j<=T.length();j++){
                if(S.charAt(i-1)!=T.charAt(j-1)){
                    dp[i][j]=dp[i-1][j];
                }
                else{
                    dp[i][j]=dp[i-1][j-1]+dp[i-1][j];
                }
            }
        }
        return dp[S.length()][T.length()];
    }
}
