package offer;

/**
 * Created by George on 2018/1/11.
 */
public class ReverseString { //翻转字符串
    public static void main(String[] args){
        ReverseString rs=new ReverseString();
        System.out.print(rs.reverse("I am a student!"));
        System.out.println(rs.leftReverse("abcdefg",2));

    }
    public String reverse(String sentence){
       if(sentence==null || sentence.equals("")){
           return sentence;
       }
       else{
           StringBuffer sb=new StringBuffer();
           for(int i=sentence.length()-1;i>=0;i--){
               sb.append(sentence.charAt(i));
           }
           String str=sb.toString();
           String[] words=str.split(" ");
           StringBuffer result=new StringBuffer();

           for(String word:words){
               sb=new StringBuffer();
               for(int i=word.length()-1;i>=0;i--){
                   sb.append(word.charAt(i));
               }
               result.append(sb.toString()+" ");
           }
           return result.toString().trim();
       }
    }

    public String leftReverse(String str,int index){
        if(str==null || str.equals("") || index<0 || index>str.length()-1){
            return str;
        }
        else{
            StringBuffer sb=new StringBuffer();
            for(int i=index;i<str.length();i++){
                sb.append(str.charAt(i));
            }
            for(int i=0;i<index;i++){
                sb.append(str.charAt(i));
            }
            return sb.toString();
        }

    }
}
