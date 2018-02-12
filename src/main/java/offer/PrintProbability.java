package offer;

import java.util.HashMap;

/**
 * Created by George on 2018/1/11.
 */
public class PrintProbability { //打印骰子概率,给定number个骰子，求每个值出现的概率
    private int maxValue=6; //骰子点数
    public static void main(String[] args){
        PrintProbability pp=new PrintProbability();
        HashMap<Integer,Double> probabilitys=pp.probability(2);
        for(HashMap.Entry entry:probabilitys.entrySet()){
            System.out.println("int="+entry.getKey()+" | probability="+entry.getValue());
        }
    }
    public HashMap<Integer,Double> probability(int number){
        HashMap<Integer,Double> probabilitys=new HashMap<Integer, Double>();
        if(number<=0){
            return probabilitys;
        }
        else{
            int[][] frequents=new int[2][maxValue*number+1];
            int flag=0;
            for(int i=1;i<=maxValue;i++){
                frequents[flag][i]=1;
            }
            for(int k=2;k<=number;k++){ //有2到number个骰子,因为最小为k，最大为6k
                for(int i=0;i<k;i++){
                    frequents[1-flag][i]=0;
                }
                for(int i=k;i<=maxValue*k;i++){ //实际和s
                    frequents[1-flag][i]=0; //清零
                    for(int j=1;j<=i&&j<=maxValue;j++){
                        frequents[1-flag][i]+=frequents[flag][i-j];
                    }
                }
                flag=1-flag;
            }
            double total=Math.pow(maxValue,number);
            for(int i=number;i<=maxValue*number;i++){
                double ratio=(double)frequents[flag][i]/total;
                probabilitys.put(i,ratio);
            }
            return probabilitys;
        }
    }
}
