package offer;

import java.util.Arrays;

/**
 * Created by George on 2018/1/11.
 */
public class IsContinuous { //判断5张牌是否连续，把大小王当作赖子0，即可以作为任意牌，是否顺子的依据是相邻牌之间的总差据小于0或者等于0的个数
    public static void main(String[] args){
        IsContinuous ic=new IsContinuous();
        int[] cards=new int[]{0,1,3,4,5};
        System.out.println(ic.isContinuous(cards));
    }

    public boolean isContinuous(int[] cards){
        if(cards==null || cards.length!=5){
            return false;
        }
        else{
            Arrays.sort(cards);
            int zero=0;
            for(int i=0;i<cards.length;i++){
                if(cards[i]==0){
                    zero++;
                }
            }
            int gap=0;
            for(int j=zero;j<cards.length-1;j++){
                gap+=cards[j+1]-cards[j]-1;
            }
            return gap>zero?false:true;
        }
    }
}
