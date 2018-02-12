package offer;

/**
 * Created by George on 2018/1/9.
 */

import java.util.TreeSet;

public class MinK {
    public static void main(String[] args){
        int[] a=new int[]{1,3,2,4,5,6,9};
        MinK mk=new MinK();
        int[] b=mk.minK(a,4);
        for(Integer i:b){
            System.out.print(i);
            System.out.println();
        }
    }

    public int[] minK(int[] a,int k){
        if(a==null || a.length==0 || k<=0 ||k>a.length){
            return a;
        }
        else{
           TreeSet<Integer> set=new TreeSet<Integer>();
            for(int i=0;i<a.length;i++){
                if(set.size()<k){
                    set.add(a[i]);
                }
                else{
                    if(a[i]<set.last()){
                        set.remove(set.last());
                        set.add(a[i]);
                    }
                    else{
                        continue;
                    }
                }
            }
            int[] b=new int[k];
            for(int j=0;j<k;j++){
                b[j]=set.first();
                set.remove(set.first());
            }
            return b;
        }
    }


}
