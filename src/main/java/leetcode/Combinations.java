package leetcode;

import java.util.ArrayList;

/**
 * Created by George on 2018/1/24.
 */
public class Combinations {
    private ArrayList<ArrayList<Integer>> result=new ArrayList<ArrayList<Integer>>();
    public static void main(String[] args){
        Combinations c=new Combinations();
        ArrayList<ArrayList<Integer>> a=c.combine(4,2);
        return;
    }
    public ArrayList<ArrayList<Integer>> combine(int n, int k) { //给定n，k求所有可能的组合
            if(n<=0 || k<=0){
                return result;
            }
            else{
                ArrayList<Integer> list=new ArrayList<Integer>();
                combineList(n,k,1,list);
                return result;
            }
    }

    public void combineList(int n,int k,int start,ArrayList<Integer> list){
        if(k==0){
            result.add(list);
            return;
        }
        else{
            for(int i=start;i<=n;i++){
               ArrayList<Integer> tmp=new ArrayList<Integer>(list);
                tmp.add(i);//从start～n之间随机选择一个数作为第一个元素,第二个元素则是在i+1～n之间
                combineList(n,k-1,i+1,tmp);
            }
        }
    }
}
