package offer;

/**
 * Created by George on 2018/1/7.
 */
public class Pow {
    public static void main(String[] args){
        Pow pow=new Pow();
        System.out.print(pow.pow(0,-2));

    }
    public double pow(double base,int exp){
        if(base==(double) 0.0 && exp<0){
            return 0.0; //错误输入
        }
        else{
            if(exp<0){
                double result=1.0;
                for(int i=0;i<-exp;i++){
                    result=result/base;
                }
                return result;
            }
            else if(exp>0){
                double result=1.0;
                for(int i=0;i<exp;i++){
                    result=result*base;
                }
                return result;
            }
            else{
                return 1.0;
            }
        }
    }
}
