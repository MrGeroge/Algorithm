package offer;

/**
 * Created by George on 2018/1/10.
 */
public class GetCountOfK { //给定一个有序数组，有序数组中k出现的次数

    public static void main(String[] args){
        GetCountOfK gc=new GetCountOfK();
        int[] a=new int[]{1,2,3,3,3,4};
        System.out.print(gc.getCountOfK(a,3));
    }

    public int getFirstK(int[] a,int start,int end,int k){ //得到第一个k的位置，二分查找
        if(start>end){
            return -1;
        }
        else{
            int mid=(end+start)/2;//中间数
            if(a[mid]==k){ //找到其中一个
                if((mid>0 && a[mid-1]!=k) || mid==0){
                    return mid;
                }
                else{
                    end=mid-1;
                }
            }
            else if(a[mid]>k){
                end=mid-1;
            }
            else{
                start=mid+1;
            }
            return getFirstK(a,start,end,k);
        }
    }

    public int getLastK(int[] a,int start,int end,int k){ //得到最后一个k的位置，二分查找
        if(start>end){
            return -1;
        }
        else{
            int mid=(start+end)/2;
            if(a[mid]==k){
                if((mid<a.length-1 && a[mid+1]!=k) || mid==a.length-1){
                    return mid;
                }
                else{
                    start=mid+1;
                }
            }
            else if(a[mid]<k){
                start=mid+1;
            }
            else{
                end=mid-1;
            }
            return getLastK(a,start,end,k);
        }
    }

    public int getCountOfK(int[] a,int k){ //给定k查找个数
       if(a==null || a.length==0){
           return -1;
       }
       else{
           int first=getFirstK(a,0,a.length-1,k);
           int last=getLastK(a,0,a.length-1,k);
           if(first>-1 && last>-1){
               return last-first+1;
           }
           else{
               return -1;
           }
       }
    }
}
