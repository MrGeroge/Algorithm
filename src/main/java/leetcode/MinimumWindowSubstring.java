package leetcode;

import java.util.ArrayList;

/**
 * Created by George on 2018/1/24.
 */
public class MinimumWindowSubstring {
    public String minWindow(String S, String T) {
        int map[]=new int[128];//模拟哈希表
        char[] chs=T.toCharArray();
        for(int i=0;i<chs.length;i++){//统计T中每个字符出现的次数
            map[chs[i]]++;
        }
        int begin=0;
        int end=0;
        int count=T.length();
        int min=Integer.MAX_VALUE;
        int head=0;
        while(end<S.length()){ //end一直向后遍历
             if(map[S.charAt(end++)]-->0){ //end所在字符属于T
                 count--;
             }
             while(count==0){
                 if(end-begin<min){
                     min=end-(head=begin);
                 }
                 //移动begin
                 if(map[S.charAt(begin++)]++==0){ //begin在T中
                     count++;
                 }
             }
        }
        return min<Integer.MAX_VALUE?"":S.substring(begin,begin+min);
    }
}
