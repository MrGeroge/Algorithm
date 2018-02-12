package offer;

/**
 * Created by George on 2018/1/26.
 */
public class SortColors {
    public static void main(String[] args){
        int[] A=new int[]{1,2,0};
        SortColors sc=new SortColors();
        sc.sortColors(A);
        for(int i=0;i<A.length;i++){
            System.out.println(A[i]);
        }
    }
    public void sortColors(int[] A) {
        if(A==null || A.length==0){
            return;
        }
        else{
            int n=A.length;
            for(int i=0;i<n-1;i++){
                for(int j=0;j<n-1-i;j++){
                    if(A[j]>A[j+1]){
                        int temp=A[j+1];
                        A[j+1]=A[j];
                        A[j]=temp;
                    }
                }
            }
        }
    }
}
