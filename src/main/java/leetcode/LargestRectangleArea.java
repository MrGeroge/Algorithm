package leetcode;

import java.util.Stack;

/**
 * Created by George on 2017/12/20.
 */
public class LargestRectangleArea {
    public static void main(String[] args){
        LargestRectangleArea lra=new LargestRectangleArea();
        int height[]=new int[]{2,1,3,4};
        System.out.print(lra.largestRectangleArea(height));
    }
    public int largestRectangleArea(int[] height) {
        int max=0;
        int len=height.length;
        Stack<Integer> stack=new Stack<Integer>();
        for(int i=0;i<len;i++){ //每一列
            if(stack.isEmpty() || height[stack.peek()]<=height[i]){
                stack.push(i);
            }
            else{
                int high=height[stack.pop()];
                int width=stack.isEmpty()?i:i-stack.peek()-1;
                max=Math.max(max,high*width);
                i--;
            }
        }
        while(!stack.isEmpty()){
            int high=height[stack.pop()];
            int width=stack.isEmpty()?len:len-stack.peek()-1;
            max=Math.max(max,high*width);
        }
        return max;

    }
}
