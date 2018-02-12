package leetcode;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 * Created by George on 2018/1/23.
 */
public class SubSet {

    public static void main(String[] args){
        SubSet ss=new SubSet();
        int[] S=new int[]{1,2,3,4};
        ArrayList<ArrayList<Integer>> result=ss.subsets(S);
        return;
    }

    public ArrayList<ArrayList<Integer>> subsets(int[] S) {//求0～n的子集
        if(S==null || S.length==0){
            ArrayList<ArrayList<Integer>> result=new ArrayList<ArrayList<Integer>>();
            ArrayList<Integer> item=new ArrayList<Integer>();
            result.add(item);
            return result;
        }
        else{
            Arrays.sort(S);
            ArrayList<ArrayList<Integer>> result=sub(S,S.length-1);
            return result;
        }
    }

    public ArrayList<ArrayList<Integer>> sub(int[] S,int index){//求0~index的子集
        if(index==0){
            ArrayList<ArrayList<Integer>> result=new ArrayList<ArrayList<Integer>>();
            ArrayList<Integer> item1=new ArrayList<Integer>();
            ArrayList<Integer> item2=new ArrayList<Integer>();
            item2.add(S[0]);
            result.add(item1);
            result.add(item2);
            return result;
        }
        else{
            ArrayList<ArrayList<Integer>> result=new ArrayList<ArrayList<Integer>>();
            ArrayList<ArrayList<Integer>> old=sub(S,index-1);
            result.addAll(old);
            for(ArrayList<Integer> list:old){
                ArrayList<Integer> tmp=new ArrayList<Integer>();
                for(int i=0;i<list.size();i++){
                    tmp.add(list.get(i).intValue());
                }
                tmp.add(S[index]);
                result.add(tmp);
            }
            return result;
        }
    }
}
