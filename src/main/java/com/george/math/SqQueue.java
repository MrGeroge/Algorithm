package com.george.math;

/**
 * Created by George on 2017/6/14.
 */
public class SqQueue {//循环队列,基于数组实现
    private Object[] contents;
    private static final int SIZE=10;//当前队列容量
    private int front;//队列头
    private int tail;//队列尾
    public SqQueue(){
        contents= new Object[SIZE];
        front=tail=0;
    }
    public int QueueLength(){//当前队列长度，即元素数
        return (tail-front+SIZE)%SIZE;
    }
    public boolean isEmpty(){
        if(front==tail){
            return true;
        }
        else{
            return false;
        }
    }
    public void enQueue(Object object){//入队
        if((tail+1)%SIZE==front){//队列满
            return;
        }
        else{
            contents[tail]=object;
            tail=(tail+1)%SIZE;
        }
    }
    public Object deQueue(){//出队
        if(isEmpty()){
            return null;
        }
        else{
            Object result;
            result=contents[front];
            front=(front+1)%SIZE;
            return result;
        }
    }
    public Object getHead(){//返回队列首元素
        if(isEmpty()){
            return null;
        }
        else{
            return contents[front];
        }
    }
    public static void main(String[] args){
        SqQueue sqQueue=new SqQueue();
        for(int i=0;i<8;i++){
            sqQueue.enQueue(i);
        }
        for(int i=0;i<8;i++){
            System.out.println("出队元素是："+sqQueue.deQueue());
        }

    }}
