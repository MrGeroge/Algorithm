package offer;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by George on 2018/1/5.
 */
public class TwoQueueToStack { //两个队列->一个栈
    private Queue<Integer> queue1;
    private Queue<Integer> queue2;
    public TwoQueueToStack(Queue<Integer> queue1,Queue<Integer> queue2){
        this.queue1=queue1;
        this.queue2=queue2;
    }
    public static void main(String[] args){
        Queue<Integer> queue1=new LinkedList<Integer>();
        Queue<Integer> queue2=new LinkedList<Integer>();
        TwoQueueToStack tt=new TwoQueueToStack(queue1,queue2);
        tt.push(1);
        tt.push(2);
        tt.push(3);
        System.out.print(tt.pop());
        System.out.print(tt.pop());
        System.out.print(tt.pop());
    }
    public void push(int val){
        if(!queue1.isEmpty() && queue2.isEmpty()){
            queue1.add(val);
        }
        else if(!queue2.isEmpty() && queue1.isEmpty()){
            queue2.add(val);
        }
        else{
            queue1.add(val);
        }
    }
    public int pop(){
        if(!queue1.isEmpty() && queue2.isEmpty()){//保证queue1只有一个元素，其他元素移到queue2
            int len=queue1.size();
            if(len==1){
                return queue1.poll();
            }
            else{
                while(len>1){
                    queue2.add(queue1.poll());
                    len--;
                }
                return queue1.poll();
            }
        }
        else if(queue1.isEmpty() && !queue2.isEmpty()){
            int len=queue2.size();
            if(len==1){
                return  queue2.poll();
            }
            else{
                while(len>1){
                    queue1.add(queue2.poll());
                    len--;
                }
                return queue2.poll();
            }
        }
        else{
            return -1;
        }
    }
}
