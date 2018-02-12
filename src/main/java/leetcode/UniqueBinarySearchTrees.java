package leetcode;

import java.util.ArrayList;

/**
 * Created by George on 2017/12/18.
 */
public class UniqueBinarySearchTrees {
    public static void main(String[] args){
        UniqueBinarySearchTrees ub=new UniqueBinarySearchTrees();
        System.out.print(ub.numTrees(3));
    }
    public int numTrees(int n) {
        if(n<=1){
            return n;
        }
        else{
            return generateTrees(1,n);
        }
    }
    public int generateTrees(int start, int end) {
        if (start == end) {
            return 1;
        } else if (start > end) {
            return 0;
        } else {//至少两个元素start<end
            ArrayList<Integer> ins=new ArrayList<Integer>();
            for (int i = start; i <= end; i++) {//以每个元素为根节点
                int leftNum = generateTrees(start, i - 1);
                int rightNum = generateTrees(i + 1, end);
                if (leftNum == 0 && rightNum == 0) {
                    ins.add(1);
                }
                else if (leftNum != 0 && rightNum == 0) {//只有左子树
                    ins.add(leftNum);
                } else if (leftNum == 0 && rightNum != 0) {//只有右子树
                    ins.add(rightNum);
                } else {
                   ins.add(leftNum*rightNum);
                }
            }
            int sum=0;
            for(Integer i:ins){
                sum=sum+i;
            }
            return sum;
        }
    }
}
