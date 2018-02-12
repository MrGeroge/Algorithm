package offer;

import java.util.ArrayList;

/**
 * Created by George on 2018/1/10.
 */
public class FindTwoNumsWithSum {//给定一个递增数组，以及一个数字

    public boolean find(int[] a,int num,int[] result){
        if(a==null || a.length==0 || result==null){
            return false;
        }
        else{
            int low=0;
            int high=a.length-1;
            while(high>low){
                if(a[high]+a[low]==num){
                    result[0]=a[low];
                    result[1]=a[high];
                    return true;
                }
                else if(a[high]+a[low]<num){
                    low++;
                }
                else{
                    high--;
                }
            }
            return false;
        }
    }

    public ArrayList<ArrayList<Integer>> findMore(int sum){//给定一个num，找到所有连续正数和为sum的组合
        ArrayList<ArrayList<Integer>> results=new ArrayList<ArrayList<Integer>>();
        if(sum<3){
            return results;
        }
        int small=1;
        int big=2;
        int curSum=small+big; //若curSum<sum则big++,curSum+big；若curSum==sum则保存small->big序列;若curSum>sum则curSum-small,small++
        int mid=(1+sum)/2; //small
        while(small<mid){
            if(curSum==sum){
                ArrayList<Integer> list=new ArrayList<Integer>();
                for(int i=small;i<=big;i++){
                    list.add(i);
                }
                results.add(list);
            }
            while(curSum>sum && small<mid){
                curSum=curSum-small;
                small++;
                if(curSum==sum){
                    ArrayList<Integer> list=new ArrayList<Integer>();
                    for(int i=small;i<=big;i++){
                        list.add(i);
                    }
                    results.add(list);
                }
            }
            big++;
            curSum=curSum+big;
        }
        return results;
    }

    public static void main(String[] args){
        FindTwoNumsWithSum fw=new FindTwoNumsWithSum();
        ArrayList<ArrayList<Integer>> results=fw.findMore(9);
        for(ArrayList<Integer> list:results){
            StringBuffer sb=new StringBuffer();
            for(Integer i:list){
                sb.append(i+"+");
            }
            System.out.print(sb.toString());
            System.out.println();
        }

        int[] result=new int[2];
        int[] a=new int[]{1,2,4,7,11,15};
        fw.find(a,15,result);
        System.out.println("%%%%%%%%%%%");
        System.out.println(result[0]);
        System.out.println(result[1]);
    }
}
