package leetcode;

/**
 * Created by George on 2017/11/23.
 */
public class RemoveDuplicatesFromSortedArray26 {
    public static void main(String[] args){
        int[] nums=new int[]{0,1,2,2,2,2,2,3,4,4,4};
        RemoveDuplicatesFromSortedArray26 rd=new RemoveDuplicatesFromSortedArray26();
        int count=rd.removeDuplicates(nums);
        System.out.println(count);
        for(int i=0;i<count;i++){
            System.out.print(nums[i]);
        }

    }
    public int removeDuplicates(int[] nums) { //排序好的数组
        if(nums.length==1){
            return nums[0];
        }
        int index=0;//头标
        int diff;
        int last=nums.length-1;//尾标
        while(index<last){
            boolean status=true;
            diff=index;
            while(diff<=last){
                if(nums[diff]==nums[index]){
                    diff++;
                }
                else{//找到了第一个
                    int offset=diff-index-1; //迁移长度
                    if(offset==0){
                        index++;
                        break;
                    }
                    int temp=diff;
                    while(temp<=last){ //将diff->last中的每个元素前移offset位
                        nums[temp-offset]=nums[temp];
                        temp++;
                    }
                    last=last-offset;
                    index++;
                    status=false;
                    break;
                }

            }
            if(diff>last && status){
                return index+1;
            }
            else{
                continue;
            }
        }
        return last+1;
    }
}
