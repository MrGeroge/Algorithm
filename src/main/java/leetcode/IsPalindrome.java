package leetcode;

/**
 * Created by George on 2017/12/7.
 */
public class IsPalindrome {
    public static void main(String[] agrs){
        String s="1a2";
        IsPalindrome ip=new IsPalindrome();
        System.out.print(ip.isPalindrome(s));
    }
    public boolean isPalindrome(String s) {
        if(s.equals("") || s.length()==0){
            return true;
        }
        else{
            StringBuilder sb=new StringBuilder();
            String str=s.toLowerCase();
            for(int i=0;i<str.length();i++){
                if(('a'<=str.charAt(i) && str.charAt(i)<='z') || ('0'<=str.charAt(i) && str.charAt(i)<='9')){
                    sb.append(str.charAt(i));
                }
                else{
                    continue;
                }
            }
            String newS=sb.toString();
            if(newS.equals("") || newS.length()==0){
                return true;
            }
            else{
                int low=0;
                int high=newS.length()-1;
                boolean flag=true;
                while(low<=high){
                    if(newS.charAt(low)==newS.charAt(high)){
                        low++;
                        high--;
                    }
                    else{
                        flag=false;
                        break;
                    }
                }
                return flag;
            }
        }
    }
}
