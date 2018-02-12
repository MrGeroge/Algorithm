package com.george.math;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by George on 2017/11/24.
 */
public class MaximumSubarray {//最大值所在
    public static void main(String[] args){

    }
    public int maxSubArray(int[] nums) {
        if(nums.length==1){
            return nums[0];
        }
        else{//至少有两个元素

                int[] sum = new int[nums.length];

                int max = nums[0];
                sum[0] = nums[0];

                for (int i = 1; i < nums.length; i++) {
                    sum[i] = Math.max(nums[i], sum[i - 1] + nums[i]);
                    max = Math.max(max, sum[i]);
                }

                return max;
            }
        }
    }


