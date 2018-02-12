package offer;

/**
 * Created by George on 2018/1/5.
 */
public class ArrayRotate { //数组旋转的含义是取一个递增数组形如{1,2,3,4,5}，{4,5,1,2,3}为其一个旋转数组，然后取递增数组前若干个元素放在数组末尾，找出该旋转数组中的最小值，采用类似二分查找的方法
    public int min(int a[]){//旋转数组a肯定有两个递增子数组组成
        int index1=0;
        int index2=a.length-1;
        int mid=0;
        while(a[index1]>=a[index2]){
            if(index2-index1==1){
                mid=index2;
                break;
            }
            else{
                mid=(index1+index2)/2;
                if(a[mid]==a[index1] && a[index1]==a[index2]){//顺序查找index1~index2
                    int result=a[index1];
                    for(int i=index1+1;i<=index2;i++){
                        if(a[i]<result){
                            result=a[i];
                        }
                    }
                    return result;
                }
                else{
                    if(a[mid]>=a[index1]){//位于第一个子数组
                        index1=mid;
                    }
                    else if(a[mid]<=a[index2]){
                        index2=mid;
                    }
                    else{
                        continue;
                    }
                }
            }
        }
        return a[mid];
    }

    public static void main(String[] args){
        int[] a=new int[]{1,0,1,1,1};
        ArrayRotate ar=new ArrayRotate();
        System.out.print(ar.min(a));

    }

}
