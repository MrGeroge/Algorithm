package leetcode;

/**
 * Created by George on 2017/12/21.
 */
public class SearchInRotatedSortedArrayII {
    public boolean search(int[] A, int target) {
        if(A==null || A.length==0){
            return false;
        }
        else{
            for(int i=0;i<A.length;i++){
                if(target==A[i]){
                    return true;
                }
            }
            return false;
        }
    }
}
