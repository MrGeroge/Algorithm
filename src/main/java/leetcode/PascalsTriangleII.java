package leetcode;

import java.util.ArrayList;

/**
 * Created by George on 2017/12/10.
 */
public class PascalsTriangleII {
    public ArrayList<Integer> getRow(int rowIndex) { //输出杨辉三角的第rowIndex行数
        ArrayList<Integer> ins=new ArrayList<Integer>();
        if(rowIndex==0){
            ins.add(1);
            return ins;
        }
        else if(rowIndex==1){
            ins.add(1);
            ins.add(1);
            return ins;
        }
        else{ //只有>=2才具有杨辉三角形特性
            ins.add(1); //首元素，第rowIndex行一共rowIndex+1个元素
            ArrayList<Integer> temp=getRow(rowIndex-1); //获得第rowIndex-1行元素
            for(int i=0;i<temp.size()-1;i++){
                ins.add(temp.get(i)+temp.get(i+1));
            }
            ins.add(1);
            return ins;
        }
    }
}
