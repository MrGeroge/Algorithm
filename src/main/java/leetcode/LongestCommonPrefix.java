package leetcode;

/**
 * Created by George on 2017/11/8.
 */
public class LongestCommonPrefix { //寻找多个字符串的最长相同前缀
    public static void main(String[] args){
        LongestCommonPrefix lcp=new LongestCommonPrefix();
        String[] strs=new String[]{"caiqi","caijianping","caibangyun"};
        String result=lcp.longestCommonPrefix(null);
        if(result==null){
            System.out.println("null");
        }
        else{
            System.out.println(result);
        }

    }
    public String longestCommonPrefix(String[] strs) {
        if(strs.length==0){
            return null;
        }
        else if(strs.length==1){
            return strs[0];
        }
        else{ //至少两个字符串
            int num=strs.length;
            StringBuilder sb=new StringBuilder();
            int[] strLengths=new int[num];
            int min=Integer.MAX_VALUE;
            for(int i=0;i<num;i++){
                strLengths[i]=strs[i].length();
                if(strLengths[i]<min){
                   min=strLengths[i];
                }
            }//最小字符串长度

           for(int j=0;j<min;j++){
               for(int i=0;i<num-1;i++){
                   if(strs[i].charAt(j)==strs[i+1].charAt(j)){
                       continue;
                   }
                   else{
                       return sb.toString();
                   }
               }
               sb.append(strs[0].charAt(j));
           }
           return sb.toString();
        }
    }
}
