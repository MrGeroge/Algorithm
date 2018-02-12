package leetcode;

/**
 * Created by George on 2017/12/18.
 */
public class InterleavingString {
    public boolean isInterleave(String s1, String s2, String s3) {
        if (s1.length() + s2.length() != s3.length()) {
            return false;
        } else if (s1.equals("") && !s2.equals("")) {
            if (s2.equals(s3)) {
                return true;
            } else {
                return false;
            }
        } else if (!s1.equals("") && s2.equals("")) {
            if (s1.equals(s3)) {
                return true;
            } else {
                return false;
            }
        } else {//s1与s2均不为空串，且s1.length+s2.length==s3.length
            int indexS1 = 0;
            int indexS2 = 0;
            int i;
            for (i = 0; i < s3.length(); i++) {
                if (indexS1 < s1.length() && indexS2 < s2.length() && s3.charAt(i) == s1.charAt(indexS1) && s1.charAt(indexS1) != s2.charAt(indexS2)) {
                    indexS1++; //只能选s1
                } else if (indexS1 < s1.length() && indexS2 < s2.length() && s3.charAt(i) == s2.charAt(indexS2) && s1.charAt(indexS1) != s2.charAt(indexS2)) {
                    indexS2++; //只能选s2
                } else if (indexS1 < s1.length() && indexS2 < s2.length() && s1.charAt(indexS1) == s2.charAt(indexS2) && s2.charAt(indexS2) == s3.charAt(i)) {
                    String sub11, sub13;
                    if (indexS1 == s1.length() - 1) {
                        sub11 = "";
                    } else {
                        sub11 = s1.substring(indexS1 + 1); //选择s1
                    }
                    String sub12 = s2.substring(indexS2);
                    sub13 = s3.substring(i + 1);

                    String sub22, sub23;
                    if (indexS2 == s2.length() - 1) {
                        sub22 = "";
                    } else {
                        sub22 = s2.substring(indexS2 + 1);
                    }
                    String sub21 = s1.substring(indexS1);
                    sub23 = s3.substring(i + 1);
                    return isInterleave(sub11, sub12, sub13) || isInterleave(sub21, sub22, sub23);
                } else {
                    break; //s1遍历完了或者s2遍历完了
                }
            }
            if (indexS1 == s1.length() && indexS2 < s2.length()) {//s1遍历完了,s2没有
                String subS2 = s2.substring(indexS2);
                String subS3 = s3.substring(i);
                if (subS2.equals(subS3)) {
                    return true;
                } else {
                    return false;
                }
            } else if (indexS1 < s1.length() && indexS2 == s2.length()) {//s1没遍历完了，s2遍历完了
                String subS1 = s1.substring(indexS1);
                String subS3 = s3.substring(i);
                if (subS1.equals(subS3)) {
                    return true;
                } else {
                    return false;
                }
            } else if (indexS1 < s1.length() && indexS2 < s2.length()) {
                return false;
            } else {
                return true;
            }
        }
    }

    public boolean isInterleave1(String s1, String s2, String s3) {
        int len1 = s1.length();
        int len2 = s2.length();
        int len3 = s3.length();
        if (len1 + len2 != len3)
            return false;
        char[] chs1 = s1.toCharArray();
        char[] chs2 = s2.toCharArray();
        char[] chs3 = s3.toCharArray();
        //dp[i][j]代表 chs1[1...i]  chs2[1...j]能否顺序匹配chs3[i+j]
        boolean[][] dp = new boolean[len1 + 1][len2 + 1];
        dp[0][0] = true;
        //s1中取0个s2中取i个 去和s3中0+i 个匹配
        for (int i = 1; i <= len2; i++) {
            dp[0][i] = dp[0][i - 1] && chs2[i - 1] == chs3[i - 1];
        }
        //s2中取0个s1中取i个 去和s3中0+i 个匹配
        for (int i = 1; i <= len1; i++) {
            dp[i][0] = dp[i - 1][0] && chs1[i - 1] == chs3[i - 1];
        }
        for(int i =1;i<=len1;i++)
            for (int j=1;j<=len2;j++){
                dp[i][j] = dp[i-1][j] && (chs3[i+j-1] == chs1[i-1])|| dp[i][j-1] && (chs3[i+j-1] == chs2[j-1]);
            }
        return dp[len1][len2];
    }

}
