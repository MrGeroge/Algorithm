package leetcode;

import java.util.*;
public class WordLadder {
    public static void main(String[] args){
        WordLadder wl=new WordLadder();
        HashSet<String> dict=new HashSet<String>();
        dict.add("hot");
        dict.add("dot");
        dict.add("dog");
        dict.add("lot");
        dict.add("log");
        System.out.println(wl.ladderLength("hit","cog",dict));
    }
    public int ladderLength(String start, String end, HashSet<String> dict) {
        //思路：双端HashSet,将其分为起始集合和结束集合，当起始集合中的单词变换一次后得到结束集合中的某词时可以结束
        //当起始、结束集合中有任意一个为空时，总的也为空
        //由于只有26个字母，因此，采用字母替换法所能达到的26*n在n较大时远远优于n*n
        //init
        HashSet<String> beginSet=new HashSet<String>(); //起始集合
        HashSet<String> endSet=new HashSet<String>(); //终止集合
        HashSet<String> visited=new HashSet<String>(); //访问集合

        beginSet.add(start);
        endSet.add(end);
        visited.add(start);
        int res=1;

        while(!beginSet.isEmpty()&&!endSet.isEmpty()){
            //保证每次对较少数目的endSet做处理
            if(beginSet.size()>endSet.size()){ //保证beginSet.size()<=endSet.size()
                HashSet<String> temp=beginSet;
                beginSet=endSet;
                endSet=temp;
            }

            HashSet<String> temp=new HashSet<String>();

            for(String str:beginSet){
                char[] chs=str.toCharArray();
                for(int i=0;i<chs.length;i++){
                    char old=chs[i];
                    for(char ch='a';ch<='z';ch++){
                        chs[i]=ch;
                        String cur=String.valueOf(chs);
                        if(endSet.contains(cur)){
                            return res+1;
                        }
                        if(!visited.contains(cur)&&dict.contains(cur)){
                            temp.add(cur);
                            visited.add(cur);
                        }
                    }
                    chs[i]=old;
                }

            }
            beginSet=temp;
            res++;
        }
        return 0;
    }
    public int ladderLength1(String start, String end, HashSet<String> dict){ //给定起始单词start和结束单词end，找到start->end的最短变换路径，每次变换的单词都在dict中，且与前一个单词只相差一个字符
        HashSet<String> beginSet=new HashSet<String>();
        HashSet<String> endSet=new HashSet<String>();
        HashSet<String> visited=new HashSet<String>();
        beginSet.add(start);
        endSet.add(end);
        visited.add(start);
        int res=1;
        while(!beginSet.isEmpty() && !endSet.isEmpty()){
            if(beginSet.size()>endSet.size()){ //从后向前找
                HashSet<String> tmp=endSet;
                endSet=beginSet;
                beginSet=tmp;
            }
            HashSet<String> temp=new HashSet<String>();
            for(String str:beginSet){ //遍历开始集合中的元素
                char[] chs=str.toCharArray(); //遍历beginSet中的每个单词str，尝试对其每个字符进行一次变换，找到长度为1的相关单词
                for(int i=0;i<chs.length;i++){
                    char old=chs[i];
                    for(char c='a';c<='z';c++){
                        chs[i]=c; //每次变换一个字符
                        String word=String.valueOf(chs);//当前字串
                        if(endSet.contains(word)){
                            return res+1;
                        }
                        if(!visited.contains(word) && dict.contains(word)){
                            temp.add(word);
                            visited.add(word);
                        }
                    }
                    chs[i]=old;
                }
            }
            beginSet=temp; //当前已访问word
            res++;
        }
        return 0;
    }
}