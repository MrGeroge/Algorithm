package leetcode;

/**
 * Created by George on 2017/11/25.
 */
public class MergeSortedArray88 {
    public static void main(String[] args){
        int[] a=new int[]{1,0};
        int[] b=new int[]{2};
        MergeSortedArray88 ms=new MergeSortedArray88();
        ms.merge(a,1,b,1);
        for(int i=0;i<a.length;i++){
            System.out.print(a[i]);
        }
    }
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int count=m;
        int j=0;
        int i=0;
        //for(;i<n && j<count;i++){
        while(i<n && j<count){
            if(nums2[i]<nums1[j]){ //j为待插入位置
                for(int k=count-1;k>=j;k--){
                    nums1[k+1]=nums1[k];
                }
                nums1[j]=nums2[i];
                count++;
                i++;
            }
            else{
                j++;
            }
        }

        //}
        if(j>=count){
           for(int h=i;h<n;h++){
               nums1[h-i+count]=nums2[h];
           }
           count=m+n;
        }
    }
}
