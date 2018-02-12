package offer;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

/**
 * Created by George on 2018/1/11.
 */
public class Add { //不通过乘除，条件循环实现累加
    public int add0(Integer n){
        return 0;
    }

    public int add1(Integer n) throws InvocationTargetException, IllegalAccessException, IllegalArgumentException, NoSuchMethodException {
        ArrayList<Boolean> list=new ArrayList<Boolean>();
        list.add(true);
        list.add(false);

        int index=list.indexOf(n==0);
        return n+(Integer) (this.getClass().getMethod("add"+index,Integer.class).invoke(this,(n-1)));
    }

    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException, IllegalArgumentException, NoSuchMethodException {
        Add add=new Add();
        System.out.println(add.add1(5));
    }
}
