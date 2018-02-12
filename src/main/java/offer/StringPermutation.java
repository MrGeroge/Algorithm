package offer;

import java.util.ArrayList;

/**
 * Created by George on 2018/1/9.
 */
public class StringPermutation { //字符串全排列问题
    private static ArrayList<String> strs=new ArrayList<String>();
    public void permutation(char[] str,int start){
        if(str==null || start<0 || start>str.length){
            return;
        }
        else if(start==str.length){
            //System.out.print(str);
            strs.add(new String(str));
            //System.out.println();
            return;
        }
        else{
            for(int i=start;i<str.length;i++){
                //第一个字符有n种可能，确定第一个字符
                char temp=str[start];
                str[start]=str[i];
                str[i]=temp;

                permutation(str,start+1); //对后面字符进行全排列

                //恢复原来的str
                temp=str[start];
                str[start]=str[i];
                str[i]=temp;
            }
        }
    }

    public void permutationStr(String str){
        char[] s=str.toCharArray();
        permutation(s,0);
    }

    public static void main(String[] args){
        String str=new String("abc");
        StringPermutation sp=new StringPermutation();
        sp.permutationStr(str);
        for(String s:strs){
            System.out.print(s);
            System.out.println();
        }
    }

}
