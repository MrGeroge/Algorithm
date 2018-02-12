package leetcode;

/**
 * Created by George on 2017/12/19.
 */
import java.util.*;
public class IsScramble {
    public boolean isScramble(String s1, String s2) {
        int len1 = s1.length();
        int len2 = s2.length();
        if (len1 == 1)
            return s1.equals(s2);
        //剪枝:若排序后不不相等则必定不满足条件
        char c1[]=s1.toCharArray();
        char c2[]=s2.toCharArray();
        Arrays.sort(c1);
        Arrays.sort(c2);
        if (!(new String(c1).equals(new String(c2))))
            return false;

        for (int i = 1; i < len1; i++) {
            String s1left = s1.substring(0, i);
            String s1right = s1.substring(i, len1);
            String s2left = s2.substring(0, i);
            String s2right = s2.substring(i, len2);

            //在当前分割处没有交换
            if (isScramble(s1left, s2left) && isScramble(s1right, s2right))
                return true;
            //当前分割处左右交换
            s2right = s2.substring(len1 - i, len1);
            s2left = s2.substring(0, len1 - i);

            if (isScramble(s1left, s2right) && isScramble(s1right, s2left))
                return true;
        }
        return false;

    }
}
