package leetcode;

import java.util.ArrayList;
import java.util.Stack;

/**
 * Created by George on 2017/12/2.
 */
public class EvaluateReversePolishNotation {
    public static void main(String[] args){

    }

    public int evalRPN(String[] tokens) {
        Stack<String> opStack=new Stack<String>(); //操作栈
        Stack<Integer> dataStack=new Stack<Integer>(); //操作数栈
        ArrayList<String> ops=new ArrayList<String>();
        ops.add("+");
        ops.add("-");
        ops.add("*");
        ops.add("/");
        if(tokens.length==0){
            return 0;
        }
        else if(tokens.length==1){
            return Integer.parseInt(tokens[0]);
        }
        else{ //至少两个数
            for(int i=tokens.length-1;i>=0;i--){//分类存储
                opStack.add(tokens[i]);
            }
            while(!opStack.isEmpty()){ //操作数不为空
                String elem=opStack.pop();  //弹出栈顶元素
                if(!ops.contains(elem)){ //操作数
                    dataStack.add(Integer.parseInt(elem));
                    continue;
                }
                else{ //elem操作符
                    int op2=dataStack.pop();  //操作数2
                    int op1=dataStack.pop(); //操作数1
                    if(elem.equals("+")){
                        int result=op1+op2;
                        dataStack.add(result);
                    }
                    else if(elem.equals("-")){
                        int result=op1-op2;
                        dataStack.add(result);
                    }
                    else if(elem.equals("*")){
                        int result=op1*op2;
                        dataStack.add(result);
                    }
                    else{
                        int result=op1/op2;
                        dataStack.add(result);
                    }
                }
            }
            return dataStack.pop();
        }
    }
}
