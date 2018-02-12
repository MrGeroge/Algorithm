package offer;

/**
 * Created by George on 2018/1/9.
 */
public class NumberOf1BetweenAndN { //1～n之间出现1的个数

    public static void main(String[] args){
        NumberOf1BetweenAndN nob=new NumberOf1BetweenAndN();
        System.out.print(nob.numberof1BetweenAndN(21345));
    }

    public int numberof1BetweenAndN(int n){
        if(n<=0){
            return 0;
        }
        else{
            StringBuilder sb=new StringBuilder(""+n);
            return numOf1(sb);
        }
    }

    public int numOf1(StringBuilder s){
        if(s==null || s.length()==0 || s.charAt(0)<'0' || s.charAt(s.length()-1)>'9'){
            return 0;
        }
        else{
            int first=s.charAt(0)-'0';
            if(s.length()==1 && first==0){
                return 0;
            }
            if(s.length()==1 && first>0){
                return 1;
            }
            int numFirstDigit=0;
            if(first>1){
                numFirstDigit=(int)Math.pow(10,s.length()-1);
            }
            else if(first==1){
                numFirstDigit=Integer.parseInt(s.substring(1))+1;
            }

            int numOtherDigits=first*(s.length()-1)*(int)Math.pow(10,s.length()-2);

            int numRecursive=numOf1(s.deleteCharAt(0));

            return numFirstDigit+numOtherDigits+numRecursive;
        }
    }
}
