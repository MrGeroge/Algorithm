package offer;

import com.george.math.PrimMath;

/**
 * Created by George on 2018/1/7.
 */
public class PrintMaxN { //给定一个n，打印1～n位最大数,可以看作全排列问题
    public static void main(String[] args){
        PrintMaxN pm=new PrintMaxN();
        pm.printToMaxrecursivePrint(2);
    }
    public void printToMaxrecursivePrint(int n){
        if(n<=0){
            return;
        }
        else{
            char[] nums=new char[n];
            for(int i=0;i<10;i++){
                nums[0]=(char)('0'+i);
                printToMaxOfNDigitsRecursively(nums,n,0);
            }
        }
    }

    public void printToMaxOfNDigitsRecursively(char[] nums,int len,int index){
        if(index==len-1){
            printNums(nums);
            return;
        }
        else{
            for(int i=0;i<10;i++){
                nums[index+1]=(char)('0'+i);
                printToMaxOfNDigitsRecursively(nums,len,index+1);
            }
        }
    }

    public void printNums(char[] nums){//从第一个非0元素打印
        int len=nums.length;
        boolean isStartWithZero=true;
        for(int i=0;i<len;i++){
            if(isStartWithZero && nums[i]!='0'){
                isStartWithZero=false;
            }
            if(!isStartWithZero){
                System.out.print(nums[i]);
            }
        }
        System.out.println();
    }
}
