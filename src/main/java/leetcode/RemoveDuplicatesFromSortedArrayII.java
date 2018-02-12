package leetcode;

import java.util.HashMap;

/**
 * Created by George on 2017/12/21.
 */
public class RemoveDuplicatesFromSortedArrayII {
    public static void main(String[] args){
        int A[]=new int[]{1,1,3,3,3,3};
        RemoveDuplicatesFromSortedArrayII rd=new RemoveDuplicatesFromSortedArrayII();
        int len=rd.removeDuplicates(A);
        for(int i=0;i<len;i++){
            System.out.print(A[i]);
        }
    }
    public int removeDuplicates(int[] A) {
        if(A==null || A.length==0){
            return 0;
        }
        else if(A.length==1){
            return 1;
        }
        else if(A.length==2){
            return 2;
        }
        else{//A中元素>2
            int length=A.length;
            for(int i=0;i<length;){//超过2个删除
                int j=i+1;
                for(;j<length;j++){
                    if(A[i]==A[j]){
                        continue;
                    }
                    else{
                        break;
                    }
                }
                //第一个不相等的数索引为j
                if(j-i>2){ //A[i]所指的元素相同的超过2个，需要处理
                    if(j==length){
                        int tmp=j-(i+2);
                        length=length-tmp;
                        i=i+2;
                    }
                    else{
                        int tmp=j-(i+2); //相差tmp
                        for(;j<length;j++){
                            A[j-tmp]=A[j];
                        }
                        length=length-tmp;
                        i=i+2;
                    }
                }
                else{
                    i=j;
                }
            }
            return length;
        }
    }
}
