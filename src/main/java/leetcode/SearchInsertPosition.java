package leetcode;

/**
 * Created by George on 2017/11/23.
 */
public class SearchInsertPosition {
    public static void main(String[] args){
        return;
    }
    public int searchInsert(int[] nums, int target) {
        if(target<nums[0]){
            return 0;
        }
        else{
            for(int i=0;i<nums.length;i++){
                if(nums[i]<target){
                    continue;
                }
                else{
                    return i;
                }
            }
            return nums.length;
        }
    }
}
