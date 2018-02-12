package leetcode;

import java.util.ArrayList;

/**
 * Created by George on 2017/12/19.
 */
public class GrayCode {
    public static void main(String[] args){
        GrayCode gc=new GrayCode();
        ArrayList<Integer> list=gc.grayCode1(4);
        System.out.print(1);
    }
    public ArrayList<Integer> grayCode(int n) {
        if(n==0){
            ArrayList<Integer> ins=new ArrayList<Integer>();
            return ins;
        }
        else if(n==1){
            ArrayList<Integer> ins=new ArrayList<Integer>();
            ins.add(0);
            ins.add(1);
            return ins;
        }
        else {//至少为1个
            ArrayList<Integer> results = new ArrayList<Integer>();
            ArrayList<Integer> grays = grayCode(n - 1); //前n-1个序列，首位+0不改变序列；shouxiang
            results.addAll(grays);
            int last = grays.get(grays.size() - 1); //得到加1的起始元素
            int nfirst = last + (int) Math.pow(2, n-1);
            int ntmp = nfirst;
            results.add(nfirst); //i-1的最后一个值的bits首元素变为1
            int[] bits = new int[n]; //bits数组
            for (int i = 0; i < n; i++) {
                int mi = (int) Math.pow(2, n - i - 1);
                if (nfirst >= mi) {
                    bits[i] = 1;
                    nfirst = nfirst - mi;
                } else {
                    bits[i] = 0;
                }
            }
            //bits[]数组
            for (int i = 0; i < (int) Math.pow(2, n - 1) - 1; i++) {//一共要找(int)Math.pow(2,n-1)-1个数
                for (int j = 1; j < n; j++) {
                    int tmp = (int) Math.pow(2, n - j - 1);
                    if (bits[j] == 0) {//替换j位为1
                        if (!results.contains(tmp + ntmp)) {//可替换
                            results.add(tmp + ntmp);
                            bits[j] = 1;
                            ntmp = tmp + ntmp; //当前值
                            break;
                        } else {
                            continue;
                        }
                    } else {//替换j位为0
                        if (!results.contains(ntmp - tmp)) {
                            results.add(ntmp - tmp);
                            bits[j] = 0;
                            ntmp = ntmp - tmp;
                            break;
                        } else {
                            continue;
                        }
                    }
                }
            }
            return results;
        }
    }

        public ArrayList<Integer> grayCode1(int n) {
            ArrayList<Integer> al = new ArrayList<Integer>();
            al.add(0);
            int size;
            int temp;
            for(int i = 0; i < n; ++i){
                size = al.size();
                for(int j = size - 1; j >=0; --j){
                    temp = al.get(j);
                    al.add(temp + (int)Math.pow(2,i));
                }
            }
            return al;
        }

}
