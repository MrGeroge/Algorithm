package offer;

/**
 * Created by George on 2018/1/5.
 */
public class EmptyBlankReplace { //空格替换
    public static void main(String[] args){
        EmptyBlankReplace eb=new EmptyBlankReplace();
        System.out.print(eb.replace(new String("a b c")));
    }
    public String replace(String str){
        if(str==null || str.equals("")){
            str=new String();
        }
        else{
            str=str.replace(" ","%");
        }
        return str;
    }
}
