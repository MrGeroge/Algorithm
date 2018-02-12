package leetcode;

import java.util.*;

/**
 * Created by George on 2017/12/4.
 */
public class IsWordBreak {
    public boolean wordBreak2(String s, Set<String> dict){
        int len = s.length();
        boolean[] arrays = new boolean[len+1];
        arrays[0] = true;
        for (int i = 1; i <= len; ++i){
            for (int j = 0; j < i; ++j){
                if (arrays[j] && dict.contains(s.substring(j, i))){
                    arrays[i] = true;
                    break;
                }
            }
        }
        return arrays[len];
    }
}
