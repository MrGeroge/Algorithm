package offer;

import java.util.Arrays;

/**
 * Created by George on 2018/1/9.
 */
public class MoreThanHalf {
    public int moreThanHalf(int[] a){
        if(a==null || a.length==0){
            return 0;
        }
        else{
            int result=a[0];
            int time=1;
            for(int i=1;i<a.length;i++){
                if(time==0){ //result肯定是最后一个time变为1的值
                    result=a[i];
                    time=1;
                }
                else if(a[i]==result){
                    time++;
                }
                else {
                    time--;
                }
            }
            int count=0;
            for(int i=0;i<a.length;i++){
                if(a[i]==result){
                    count++;
                }
                else{
                    continue;
                }
            }
            if(count>a.length/2){
                return result;
            }
            else{
                return 0;
            }
        }
    }

    public int moreThanHalf1(int[] a){
        if(a==null || a.length==0){
            return 0;
        }
        else{
            int count=1;
            Arrays.sort(a); //更改数组位置
            for(int i=0;i<a.length-1;i++){
                if(a[i]==a[i+1]){
                    count++;
                }
                else{
                    if(count>a.length/2){
                        return a[i];
                    }
                    else{
                        count=1;
                        continue;
                    }
                }
            }
            return 0;
        }
    }

    public static void main(String[] args){
        MoreThanHalf mt=new MoreThanHalf();
        int[] a=new int[]{1,2,3,1};
        System.out.print(mt.moreThanHalf(a));
    }
}
