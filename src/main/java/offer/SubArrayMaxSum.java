package offer;

/**
 * Created by George on 2018/1/9.
 */
public class SubArrayMaxSum { //子数组的最大和
    public int maxSum(int[] a){
        if(a==null || a.length==0){
            return -1;
        }
        else{
            int[] dp=new int[a.length];
            dp[0]=a[0];
            int max=dp[0];
            for(int i=1;i<a.length;i++){
                dp[i]=Math.max(a[i],dp[i-1]+a[i]);
                if(max<dp[i]){
                    max=dp[i];
                }
            }
            return max;
        }
    }


    public static void main(String[] args){
        SubArrayMaxSum sa=new SubArrayMaxSum();
        int[] a=new int[]{1,-2,3,10,-4,7,2,-5};
        System.out.print(sa.maxSum(a));
    }
}
