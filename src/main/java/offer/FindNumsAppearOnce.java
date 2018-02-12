package offer;

import java.util.ArrayList;

/**
 * Created by George on 2018/1/10.
 */
public class FindNumsAppearOnce { //找到只出现一次的值
    public static void main(String[] args){
        FindNumsAppearOnce fn=new FindNumsAppearOnce();
        int[] a=new int[]{1,1,2,3,3,4,4,5};
        int[] result=fn.findNumAppearOnce(a);
        System.out.println(result[0]);
        System.out.println(result[1]);
    }

    public int[] findNumAppearOnce(int[] a){
        int[] onces=new int[2];
        if(a==null || a.length<2){
            return onces;
        }
        else{
            int result=0;
            for(int i=0;i<a.length;i++){
                result=result&a[i];
            }
            int indexOf1=findFirstBitIs1(result);
            ArrayList<Integer> left=new ArrayList<Integer>();
            ArrayList<Integer> right=new ArrayList<Integer>();

            for(int i=0;i<a.length;i++){
                if(isBit1(a[i],indexOf1)){
                    left.add(a[i]);
                }
                else{
                    right.add(a[i]);
                }
            }
            for(Integer i:left){
                onces[0]=onces[0]^i;
            }
            for(Integer j:right){
                onces[1]=onces[1]^j;
            }
            return onces;
        }

    }

    public int findFirstBitIs1(int num){//给定num，找到其二进制表示位最右边1的数
        int indexBit=0;
        while(((num & 1) == 0) && (indexBit<32)){
            num=num>>1; //右移一位等价于num/2
            ++indexBit;
        }
        return indexBit;
    }

    boolean isBit1(int num,int indexBit){ //判断一个数从右数起indexBit是否为1
        num=num>>indexBit;
        return ((num & 1)==1);
    }
}
