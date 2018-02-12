package leetcode;

/**
 * Created by George on 2017/12/18.
 */
public class DecodeWays {
    public class Solution {
        public int numDecodings(String s) {
            if(s.length()==0) return 0;
            int [] dp = new int[s.length()+1];
            dp[0]=1;
            if(s.length()>0&&s.charAt(0)>'0')
                dp[1]=1;
            for(int i=2;i<=s.length();i++){
                if(s.charAt(i-1)>'0')
                    dp[i]=dp[i-1];
                if(s.charAt(i-2)>'0'){
                    int t  =Integer.parseInt(s.substring(i-2,i));
                    if(t<=26&&t>0)
                        dp[i]+=dp[i-2];
                }
            }
            return dp[s.length()];
        }
    }
}
