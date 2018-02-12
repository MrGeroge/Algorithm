package leetcode;

import java.util.ArrayList;

/**
 * Created by George on 2017/12/18.
 */
public class SubsetsII {
    public static void main(String[] args){
        int num[]=new int[]{1,2,2};
        SubsetsII si=new SubsetsII();
        ArrayList<ArrayList<Integer>> list=si.subsetsWithDup(num);
        System.out.print(true);
    }
    public ArrayList<ArrayList<Integer>> subsetsWithDup(int[] num) {
        if(num.length==0){ //没有元素
            ArrayList<ArrayList<Integer>> list=new ArrayList<ArrayList<Integer>>();
            list.add(new ArrayList<Integer>());
            return list;
        }
        else{//至少一个元素
            //return subsetsWithDup(num,num.length-1);
            ArrayList<ArrayList<Integer>> ins=new ArrayList<ArrayList<Integer>>();
            /*for(int i=0;i<num.length;i++){
                ins.addAll(subsetsWithDup(num,i));
            }*/
            return subsetsWithDup(num,num.length-1);
        }
    }
    public ArrayList<ArrayList<Integer>> subsetsWithDup(int[] num,int index){//0~index一共多少个子集,子集最大为index+1个
        if(num.length==0 || index<0){
            ArrayList<ArrayList<Integer>> list=new ArrayList<ArrayList<Integer>>();
            list.add(new ArrayList<Integer>());
            return list;
        }
        else{
            ArrayList<ArrayList<Integer>> results=new ArrayList<ArrayList<Integer>>();
            ArrayList<ArrayList<Integer>> list=subsetsWithDup(num,index-1); //子集最大为index个
            results.addAll(list);//添加index-1的子集
            for(ArrayList<Integer> tmpList:list){//遍历index-1的每个子集
                ArrayList<Integer> tmp=new ArrayList<Integer>();
                tmp.addAll(tmpList);
                tmp.add(num[index]);//新的子集，判断是否与results中重复
                if(!results.contains(tmp)){//不重父
                    results.add(tmp);
                }
            }
            return results;
        }
    }

}
