package offer;

import java.util.Random;

/**
 * Created by George on 2018/1/9.
 */
public class ArrayBuildMin { //数组中所有值组合最小

    public static void main(String[] args){
        int[] a=new int[]{3,32,321};
        ArrayBuildMin ab=new ArrayBuildMin();
        String min=ab.buildMin(a);
        System.out.print(min);
    }

    public String buildMin(int[] a){
        if(a==null || a.length==0){
            return "";
        }
        else{
            quick_sort(a,a.length);
            StringBuffer sb=new StringBuffer();
            for(Integer i:a){
                sb.append(i);
            }
            return sb.toString();
        }
    }
    public int compare(int m,int n){//定制排序规则
        String M=new String(""+m);
        String N=new String(""+n);
        String MN=M+N;
        String NM=N+M;
        return MN.compareTo(NM);
    }

    public int quick_sort(int[] arrays, int lenght) {
        if (null == arrays || lenght < 1) {
            System.out.println("input error!");
            return -1;
        }
        return _quick_sort(arrays, 0, lenght - 1);
    }

    public int _quick_sort(int[] arrays, int start, int end) {
        if(start>=end){
            return -1;
        }

        int i = start;
        int j = end;
        Random random = new Random();
        System.out.println("start="+start);
        System.out.println("end="+end);
        int s = random.nextInt(end)%(end-start+1) + start;//产生start-end范围的随机数
        System.out.println("random="+s);
        //int s=start;
        int temp;
        temp=arrays[i];
        arrays[i]=arrays[s];
        arrays[s]=temp;
        int value = arrays[i];
        boolean flag = true;
        while (i != j) {
            if (flag) {
                if (compare(value,arrays[j])>0) {
                    swap(arrays, i, j);
                    flag=false;

                } else {
                    j--;
                }
            }else{
                if(compare(value,arrays[i])<0){
                    swap(arrays, i, j);
                    flag=true;
                }else{
                    i++;
                }
            }
        }
        System.out.println("q="+i);
        _quick_sort(arrays, start, i-1);
        _quick_sort(arrays, i+1, end);
        return i;

    }
    private void swap(int[] arrays, int i, int j) {
        int temp;
        temp = arrays[i];
        arrays[i] = arrays[j];
        arrays[j] = temp;
    }
}
