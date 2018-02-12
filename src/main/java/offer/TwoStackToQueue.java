package offer;

import com.sun.org.apache.xerces.internal.impl.xs.SchemaSymbols;

import java.util.Stack;

/**
 * Created by George on 2018/1/5.
 */
public class TwoStackToQueue {//两个栈->一个队列
    private Stack<Integer> stack1;
    private Stack<Integer> stack2;

    public TwoStackToQueue(Stack<Integer> stack1,Stack<Integer> stack2){
        this.stack1=stack1;
        this.stack2=stack2;
    }
    public static void main(String[] args){
        Stack<Integer> stack1=new Stack<Integer>();
        Stack<Integer> stack2=new Stack<Integer>();
        TwoStackToQueue tt=new TwoStackToQueue(stack1,stack1);//将两个stack封装成队列使用
        tt.appendTail(1);//尾加
        tt.appendTail(2);
        tt.appendTail(3);
        int elem1=tt.deleteHead();
        int elem2=tt.deleteHead();
        int elem3=tt.deleteHead();
        System.out.print(elem1);
        System.out.print(elem2);
        System.out.print(elem3);
    }
    public void appendTail(int val){//将val加入队列中，尾加
        stack1.push(val);
    }
    public int deleteHead(){
        if(!stack2.isEmpty()){
            return stack2.pop();
        }
        else{//stack1->stack2
            if(stack1.isEmpty()){
                return -1; //空队列
            }
            else{
                while(!stack1.isEmpty()){
                    stack2.push(stack1.pop());
                }
                return stack2.pop();
            }

        }
    }
}
