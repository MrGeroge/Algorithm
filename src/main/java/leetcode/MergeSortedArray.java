package leetcode;

/**
 * Created by George on 2017/12/19.
 */
public class MergeSortedArray {
    public static void main(String[] args){
        int A[]=new int[]{0,0,0,0,0,0,0,0,0,0};
        int B[]=new int[]{1};
        MergeSortedArray ms=new MergeSortedArray();
        ms.merge(A,0,B,3);
        for(int i=0;i<6;i++){
            System.out.println(A[i]);
        }
    }
    public void merge(int[] nums1, int m, int[] nums2, int n) { //最优解从后向前走
        int i = m - 1, j = n - 1, index = m + n - 1;
        while (i >= 0 && j >= 0)
            nums1[index--] = nums1[i] > nums2[j] ? nums1[i--] : nums2[j--];
        while (j >= 0)
            nums1[index--] = nums2[j--];
    }
}
