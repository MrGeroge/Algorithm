package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * Created by George on 2017/11/17.
 */
public class RemoveArray {
    public static void main(String[] args){
        RemoveArray ra=new RemoveArray();
        int[] nums=new int[]{3,2,2,3};
        int size=ra.removeElement(nums,3);
        for(int i=0;i<size;i++){
            System.out.println(nums[i]);
        }
        System.out.println(size);
    }
    public int removeElement(int[] nums, int val) {
       /* List<Integer> inits=new ArrayList<Integer>();
        for(int i:nums){
            inits.add(i);
        }
        Iterator<Integer> it=inits.iterator();
        while(it.hasNext()){
            int tmp=it.next().intValue();
            if(tmp==val){
                it.remove();
            }
            else{
                continue;
            }
        }
        nums=new int[inits.size()];
        for(int i=0;i<inits.size();i++){
            nums[i]=inits.get(i);
        }
        return inits.size();
        */
        int index=0;
        for(;index<nums.length;index++){
            if(nums[index]!=val){
                continue;
            }
            else{
                if(index==nums.length-1){ //index即为当前数据大小
                    break;
                }
                else{//当前位置不是尾端
                    int j=index+1;
                    for(;j<nums.length;j++){ //从当前位置后面一个开始找与val不相等的第一个元素进行交换
                        if(nums[j]==val){
                            continue;
                        }
                        else{ //找到了
                            int t=nums[j];
                            nums[j]=nums[index];
                            nums[index]=t;
                            break;
                        }
                    }
                    if(j<nums.length){
                        continue;
                    }
                    else{
                        break;
                    }
                    //index后面都是相等的val值，则返回index
                }

            }
        }
        return index;
    }
}
