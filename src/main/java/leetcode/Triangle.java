package leetcode;

/**
 * Created by George on 2017/12/10.
 */
import java.util.*;
public class Triangle {
    private int sum=0;
    private int maxRow=0;
    private ArrayList<Integer> gList;
    public static  void main(String[] args){
        ArrayList<Integer> list0=new ArrayList<Integer>();
        list0.add(2);
        ArrayList<Integer> list1=new ArrayList<Integer>();
        list1.add(3);
        list1.add(4);
        ArrayList<Integer> list2=new ArrayList<Integer>();
        list2.add(6);
        list2.add(5);
        list2.add(7);
        ArrayList<Integer> list3=new ArrayList<Integer>();
        list3.add(4);
        list3.add(1);
        list3.add(8);
        list3.add(3);
        ArrayList<ArrayList<Integer>> triangle=new ArrayList<ArrayList<Integer>>();
        triangle.add(list0);
        triangle.add(list1);
        triangle.add(list2);
        triangle.add(list3);
        Triangle t=new Triangle();
        System.out.print(t.minimumTotal(triangle));
    }
    public int minimumTotal(ArrayList<ArrayList<Integer>> triangle) {
        if(triangle==null || triangle.size()==0){
            return 0;
        }
        else{
            ArrayList<Integer> ins=new ArrayList<Integer>();
            for(ArrayList<Integer> list:triangle){
                maxRow++;
                for(Integer i:list){
                    ins.add(i);
                }
            }//转换为一维数组,下标为i的元素的下一行相邻元素为，假设i所在行为row，则下一行的相邻元素可以表示为(i+row+1)和（i+row+2)
           int[] array=func2(ins,maxRow-1);
            int min=array[0];
            for(int i=1;i<array.length;i++){
                if(array[i]<min){
                    min=array[i];
                }
            }
            return min;
        }
    }
    public int func(ArrayList<Integer> list,int row,int index){ //当前行row，选中元素为index
            int nextFirstIndex=index+row+1;
            int nextSecondIndex=index+row+2;
            int nextFirst=list.get(nextFirstIndex);
            int nextSecond=list.get(nextSecondIndex);
            if(nextFirst>nextSecond){
                sum=sum+nextSecond;
                return nextSecondIndex;
            }
            else{
                sum=sum+nextFirst;
                return nextFirstIndex;
            }
        }
    public int[] func2(ArrayList<Integer> list,int i){//求第i行的所有结果
        if(i==0){
            gList=new ArrayList<Integer>();
            gList.add(0);
            int[] temp=new int[1];
            temp[0]=list.get(0);
            return temp;
        }
        int[] old=func2(list,i-1); //第i-1行的所有值
        int[] arry=new int[2*old.length];
        ArrayList<Integer> tmp=new ArrayList<Integer>(); //保存当前路径最后一个节点的索引
        for(int j=0;j<old.length;j++){
                arry[2*j]=old[j]+list.get((i+1)*i/2+gList.get(j));
                arry[2*j+1]=old[j]+list.get((i+1)*i/2+gList.get(j)+1);
                tmp.add(gList.get(j));
                tmp.add(gList.get(j)+1);
        }
        gList=tmp;
        return arry;
    }
    }

