package offer;

/**
 * Created by George on 2018/1/9.
 */
public class UglyNumber {//丑数：因子只含2或者3或者5
    public static void main(String[] args){
        UglyNumber un=new UglyNumber();
        System.out.print(un.uglyNumOfIndex(1500));
    }

    public boolean isUgly(int num){
        if(num<=0){
            return false;
        }
        else{
            while(num%2==0){
                num=num/2;
            }
            while(num%3==0){
                num=num/3;
            }
            while(num%5==0){
                num=num/5;
            }
            if(num==1){
                return true;
            }
            else{
                return false;
            }
        }
    }

    public int uglyNumOfIndex(int index){
        if(index<=0){
            return 0;
        }
        else{
            int[] uglys=new int[index];
            uglys[0]=1;
            int m2=uglys[0];
            int m3=uglys[0];
            int m5=uglys[0];

            int nextIndex=1;

            while(nextIndex<index){
                int m=Math.min(Math.min(2*m2,3*m3),5*m5);
                uglys[nextIndex]=m;
                while(2*m2<=m){
                    m2++;
                }
                while(3*m3<=m){
                    m3++;
                }
                while(5*m5<=m){
                    m5++;
                }
                nextIndex++;
            }
            return uglys[nextIndex-1];
        }
    }
}
