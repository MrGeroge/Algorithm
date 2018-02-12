package leetcode;

/**
 * Created by George on 2017/12/22.
 */
public class Count1 {//给定一个数n，求1～n之间一共多少个1
    public static void main(String[] args){
        Count1 count1=new Count1();
        System.out.print(count1.count(13));
    }
    public int count(int n){
        if(n==1){
            return 1;
        }
        else{
            String str=n+"";
            int count=0;
            for(int i=0;i<str.length();i++){
                char c=str.charAt(i);
                if(c=='1'){
                    count++;
                }
            }
            return count+count(n-1);
        }
    }
}
