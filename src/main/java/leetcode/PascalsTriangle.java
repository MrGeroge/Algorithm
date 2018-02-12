package leetcode;

import java.util.ArrayList;
public class PascalsTriangle {
    private  ArrayList<ArrayList<Integer>> results;
    public ArrayList<ArrayList<Integer>> generate(int numRows) {
        if(numRows==0){
            results=new ArrayList<ArrayList<Integer>>();
            return results;
        }
        else if(numRows==1){
            results=new ArrayList<ArrayList<Integer>>();
            ArrayList<Integer> list=new ArrayList<Integer>();
            list.add(1);
            results.add(list);
            return results;
        }
        else if(numRows==2){
            results=new ArrayList<ArrayList<Integer>>();
            ArrayList<Integer> list1=new ArrayList<Integer>();
            list1.add(1);
            ArrayList<Integer> list2=new ArrayList<Integer>();
            list2.add(1);
            list2.add(1);
            results.add(list1);
            results.add(list2);
            return results;
        }
        else{ //杨辉三角
            results=generate(numRows-1);
            ArrayList<Integer> temp=results.get(results.size()-1); //第row-1行元素
            ArrayList<Integer> list=new ArrayList<Integer>();
            list.add(1);
            for(int i=0;i<temp.size()-1;i++){
                list.add(temp.get(i)+temp.get(i+1));
            }
            list.add(1);
            results.add(list);
            return results;
        }

    }
}