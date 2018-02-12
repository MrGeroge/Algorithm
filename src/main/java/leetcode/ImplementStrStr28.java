package leetcode;

/**
 * Created by George on 2017/11/24.
 */
public class ImplementStrStr28 {
    public static void main(String[] args){
        ImplementStrStr28 is=new ImplementStrStr28();
        System.out.println(is.strStr("aaaaa","bba"));
    }
    public int strStr(String haystack, String needle) {
        if(haystack=="" || needle==""){
            return -1;
        }
        else{
            if(!haystack.contains(needle)){
                return -1;
            }
            else{
                return haystack.indexOf(needle);
            }
        }
    }
}
