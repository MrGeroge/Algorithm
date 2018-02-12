package offer;

import java.util.Stack;

/**
 * Created by George on 2018/1/8.
 */
public class MinDataStack {
    private Stack<Integer> dataStack;
    private Stack<Integer> minStack;

    public MinDataStack(Stack<Integer> dataStack, Stack<Integer> minStack) {
        this.dataStack = dataStack;
        this.minStack = minStack;
    }

    public static void main(String[] args){
        Stack<Integer> dataStack=new Stack<Integer>();
        Stack<Integer> minStack=new Stack<Integer>();
        MinDataStack mds=new MinDataStack(dataStack,minStack);
        mds.push(3);
        mds.push(4);
        mds.push(2);
        mds.push(1);
        System.out.print(mds.pop());
        System.out.print(mds.min());
    }

    public void push(int value){
        dataStack.push(value);
        if(minStack.size()==0 || minStack.peek()>value){
            minStack.push(value);
        }
        else{
            minStack.push(minStack.peek());
        }
    }

    public int pop(){
        if(dataStack.size()>0 && minStack.size()>0){
            int top=dataStack.pop();
            minStack.pop();
            return top;
        }
        else{
            return -1;
        }
    }

    public int min(){
        if(dataStack.size()>0 && minStack.size()>0){
            return minStack.peek();
        }
        else{
            return -1;
        }
    }
}
