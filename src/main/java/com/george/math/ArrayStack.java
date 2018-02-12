package com.george.math;

/**
 * Created by George on 2017/6/14.
 */
public class ArrayStack {//顺序栈实现
    private Object[] contents;
    private int top;//top标记下一个入栈的位置，同时也表示栈的容量大小，跟链式实现的count比较一下！！！
    private int size = 10;//栈最大容量
    public ArrayStack()
    {
        contents = new Object[size];
        top = 0;
    }
    public void expand(){//借助于申请一个辅助空间,每次扩展容量一倍

        Object[] larger = new Object[size*2];
        for(int index = 0;index < size;index++)
            larger[index] =  contents[index];
        contents = larger;
        size=size*2;
    }
    public int size() {//当前栈有多少个元素
        return top;
    }
    public boolean isEmpty() {
        if(size()==0){
            return true;
        }
        else{
            return false;
        }
    }
    public void push(Object element) {
        if(size() == size)
            expand();
        contents[top] = element;
        top++;
    }
    public Object pop() {
        if(isEmpty())
        {
            System.out.println("stack is empty!");
            System.exit(1);
        }
        top--;
        Object result = contents[top];
        contents[top] = null;//出栈
        return result;
    }
    public Object peek() {
        Object result;
        if(isEmpty())
            result = null;
        else
            result = contents[top-1];
        return result;
    }
    public static void main(String[] args) {
        ArrayStack stack = new ArrayStack();
        System.out.println("将0到24依次压栈，然后连续10次出栈");
        for(int i = 0;i < 25;i++)
            stack.push(i);
        for(int i = 0;i < 10;i++)
            stack.pop();
        System.out.println("栈的大小为： " + stack.size());
        System.out.println("栈为空吗？： " + stack.isEmpty());
        System.out.println("栈顶元素为： " + stack.peek());
    }
}