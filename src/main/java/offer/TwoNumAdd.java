package offer;

/**
 * Created by George on 2018/1/11.
 */
public class TwoNumAdd { //两数相加
    public static void main(String[] args){
        TwoNumAdd tn=new TwoNumAdd();
        System.out.print(tn.sum(1,2));
    }
    public int sum(int num1,int num2){
        int sum,carry;
        while(num2!=0){
          sum=num1^num2;
            carry=(num1&num2)<<1;
            num1=sum;
            num2=carry;
        }
        return num1;
    }

}
