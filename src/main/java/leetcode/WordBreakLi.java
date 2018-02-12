package leetcode;

import sun.jvm.hotspot.utilities.HashtableEntry;

import java.util.*;

/**
 * Created by George on 2017/12/3.
 */
public class WordBreakLi {
        private Map<String,ArrayList<String>> map=new HashMap<String,ArrayList<String>>();
        public ArrayList<String> wordBreak(String s, Set<String> dict) {
            if(map.containsKey(s)){
                return map.get(s);
            }
            else{ //则需要进行计算
                int len=s.length();
                ArrayList<String> ret=new ArrayList<String>();
                if(dict.contains(s)){ //s刚好在词典中
                    ret.add(s);
                }
                else{//s不在词典中，则进行分词，动态规划的思想假设0～i位置的子串已经分好了，只剩下最后一个子串i～n
                    for(int i=1;i<len;i++){
                        String finalSub=s.substring(i);
                        if(dict.contains(finalSub)){ //必须保证finalSub在dict中
                            ArrayList<String> tmp=wordBreak(s.substring(0,i),dict);
                            if(tmp.size()>0){
                                for(String str:tmp){
                                    ret.add(str+" "+finalSub);
                                }
                            }
                        }
                    }
                }
                map.put(s,ret);
                return ret;
            }
        }
    }


