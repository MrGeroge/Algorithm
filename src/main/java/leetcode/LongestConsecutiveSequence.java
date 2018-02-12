package leetcode;

import java.util.Arrays;

/**
 * Created by George on 2017/12/7.
 */
public class LongestConsecutiveSequence {
    public static void main(String[] args){
        LongestConsecutiveSequence lc=new LongestConsecutiveSequence();
        int[] num=new int[]{100, 4, 200, 1, 3, 2};
        lc.longestConsecutive(num);
    }
    public int longestConsecutive(int[] num) {

        if(num == null || num.length == 0)
        {
            return 0;
        }
        Arrays.sort(num);
        int max = 1;
        int count = 1;
        for(int i = 1;i<num.length;i++)
        {
            if(num[i] == num[i-1]+1)
            {
                count = count+1;
                if(count > max)
                {
                    max = count;
                }
            }else if(num[i] == num[i-1])
            {
                continue;
            }else
            {
                count = 1;
            }
        }
        return max;
    }
}
