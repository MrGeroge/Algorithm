package offer;

import com.george.math.NumberTheory;

/**
 * Created by George on 2018/1/5.
 */
public class NumOfOne {
    public static void main(String[] args){
        NumOfOne one=new NumOfOne();
        System.out.print(one.count(10));
    }
    public int count(int n){
        int count=0;
        while(n>0){
            count++;
            n=n&(n-1);
        }
        return count;
    }
}
