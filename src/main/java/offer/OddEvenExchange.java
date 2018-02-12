package offer;

/**
 * Created by George on 2018/1/7.
 */
public class OddEvenExchange { //给定一个数组，交换其中奇数与偶数的位置
    public static void main(String[] args){
        int[] a=new int[]{1,2,3,4,5};
        OddEvenExchange oe=new OddEvenExchange();
        oe.exchange(a);
        return;
    }
    public void exchange(int[] a){
        if(a==null || a.length==0){
            return;
        }
        else{
            int low=0;
            int high=a.length-1;
            while(low<high){
                while(low<high && a[high]%2==0){ //找到第一个奇数
                    high--;
                }
                while(low<high && a[low]%2!=0){ //找到第一个偶数
                    low++;
                }
                if(low<high){
                    int temp;
                    temp=a[high];
                    a[high]=a[low];
                    a[low]=temp;
                }
            }
            return;
        }
    }
}
