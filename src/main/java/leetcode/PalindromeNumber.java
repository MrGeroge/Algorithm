package leetcode;

/**
 * Created by George on 2017/11/8.
 */
public class PalindromeNumber { //判断一个整数是不是回文数,所谓的回文数即正着读和反着读一样
    public static void main(String[] args){
        PalindromeNumber pn=new PalindromeNumber();
        System.out.println(pn.isPalindrome(-2147483648));
    }
    public boolean isPalindrome(int x) {
        if(x<0){
            return false;
        }
        else{
            String num=x+"";
            int low=0;
            int high=num.length()-1;
            boolean status=true;
            while(low<high){
                if(num.charAt(low)==num.charAt(high)){
                    low++;
                    high--;
                }
                else{
                    status=false;
                    break;
                }
            }
            if(status==false){
                return false;
            }
            else{
                return true;
            }
        }
    }
}
