package offer;

import java.util.Stack;

/**
 * Created by George on 2018/1/8.
 */
public class StackPushPopOrder {//给定压入序列，判断弹出序列是否正确
    private Stack<Integer> stack;

    public StackPushPopOrder(Stack<Integer> stack) {
        this.stack = stack;
    }

    public static void main(String[] args){
        Stack<Integer> stack=new Stack<Integer>();
        StackPushPopOrder spp=new StackPushPopOrder(stack);
        int[] a=new int[]{1,2,3,4,5};
        int[] b=new int[]{4,3,5,1,2};
        System.out.println(spp.isPopSeq(a,b));
    }

    public boolean isPopSeq(int[] a,int[] b){
        if(a==null || b==null || a.length!=b.length){
            return false;
        }
        else{
            int indexA=0;
            for(int i=0;i<b.length;i++){//遍历退栈序列
                if(stack.empty() || b[i]!=stack.peek()){//都需要从压栈序列中找到相同的值压入
                    boolean flag=false;
                    for(int j=indexA;j<a.length;j++){
                        if(a[j]!=b[i]){
                            stack.push(a[j]);
                        }
                        else{//找到了需要压栈的元素
                            stack.push(a[j]);
                            stack.pop();
                            indexA=j+1;
                            flag=true;
                            break;
                        }
                    }
                    if(!flag){
                        return false;
                    }
                    else{
                        continue;
                    }
                }
                else{
                    stack.pop(); //直接弹出
                    continue;
                }
            }
            return true;
        }
    }
}
