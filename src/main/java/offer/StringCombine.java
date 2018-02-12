package offer;

import java.util.ArrayList;
import java.util.Stack;

/**
 * Created by George on 2018/1/9.
 */
public class StringCombine { //字符串组合问题
    private static ArrayList<String> strs=new ArrayList<String>();
    public static void main(String[] args){
        char[] chs=new char[]{'a','b','c'};
        StringCombine sc=new StringCombine();
        sc.combinantion(chs);
        for(String ac:strs){
            System.out.print(ac);
            System.out.println();
        }
    }
    public void combinantion(char[] chs){
        if(chs==null || chs.length==0){
            return;
        }
        else{
           Stack<Character> list=new Stack<Character>(); //组合
            for(int i=1;i<=chs.length;i++){
                combine(chs,0,i,list);
            }
        }
    }

    public void combine(char[] chs,int begin,int num,Stack<Character> list){//从begin处开始选择num字符构成组合list
        if(num==0){
            //System.out.println(list.toString());
            strs.add(list.toString());
            return;
        }
        if(begin==chs.length){
            return;
        }
        list.push(chs[begin]);
        combine(chs,begin+1,num-1,list);
        list.pop();
        combine(chs,begin+1,num,list);

    }
}
