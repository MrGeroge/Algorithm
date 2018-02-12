package leetcode;

/**
 * Created by George on 2017/12/5.
 */
public class GasStation {
    public static void main(String[] args){
        GasStation gs=new GasStation();
        int[] gas=new int[]{1,2};
        int[] cost=new int[]{2,1};
        System.out.print(gs.canCompleteCircuit(gas,cost));
    }
    public int canCompleteCircuit(int[] gas, int[] cost) {
        if(gas.length==0 || cost.length==0){
            return -1;
        }
        else if(gas.length==1 || cost.length==1){//若只有一个站点，则需要判断
            if(gas[0]>=cost[0]){
                return 0;
            }
            else{
                return -1;
            }
        }
        else{//有站点
           for(int i=0;i<gas.length;i++){ //假设以i点作为煤气站起始
               int[] res=new int[gas.length+1];
               res[0]=0;
               boolean flag=true; //假设可以到达
               for(int j=i;j<i+gas.length;j++){
                   int index=j%gas.length; //实际下标
                   if(res[j-i]+gas[index]>=cost[index]){ //可以到达j+1点
                       res[j-i+1]=res[j-i]+gas[index]-cost[index];
                       continue;
                   }
                   else{
                       flag=false;
                       break; //不能到达，则找下一个i
                   }
               }
               if(flag==true){
                   return i;
               }
               else{
                   continue;
               }
           }
           return -1;
        }
    }
}
