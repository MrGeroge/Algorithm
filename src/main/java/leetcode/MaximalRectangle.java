package leetcode;

import java.util.ArrayList;
import java.util.Stack;

/**
 * Created by George on 2017/12/19.
 */
public class MaximalRectangle {//在给定的2D空间中，找到一个只包含1的最大矩阵，并返回其面积
    public static void main(String[] args){
        MaximalRectangle mr=new MaximalRectangle();
        char[][] matrix=new char[][]{{1,0,1,0},{1,1,1,0},{0,1,1,0}};
    }

        public int maximalRectangle(char[][] matrix) {
            if(matrix==null || matrix.length==0 || matrix[0].length==0)
                return 0;
            int max = 0;
            int rows = matrix.length; //行数
            int cols = matrix[0].length;//列数
            int[] height = new int[cols];//第i行第j列直方图的高
            int[] left = new int[cols];  //左边界(与高度相关进行判断)
            int[] right = new int[cols]; //右边界
            for(int i=0; i<cols; i++){
                right[i] = cols;
            }
            for(int i=0; i<rows; i++){//第i层
                int curLeft = 0;
                int curRight = cols;   //当前左边界和右边界
                //更新左边界
                for(int j=0; j<cols; j++){//寻找连续为'1'的直方图的起始左边界
                    if(matrix[i][j] == '1'){
                        height[j]++;
                        left[j] = Math.max(curLeft, left[j]);
                    }else{
                        height[j] = 0;
                        curLeft = j+1;
                        left[j] = 0;
                    }
                }
                for(int j=cols-1; j>=0; j--){ //更新右边界
                    if(matrix[i][j]=='1'){
                        right[j] = Math.min(curRight, right[j]);  //很重要的一步
                    }else{
                        right[j] = cols;
                        curRight = j;
                    }
                    //max = Math.max(max, (right[j]-left[j])*height[j]);
                }
                for(int k=0;k<cols;k++){
                    if(max<(right[k]-left[k])*height[k]){
                        max=(right[k]-left[k])*height[k];
                    }
                }
            }
            return max;
        }
    }

