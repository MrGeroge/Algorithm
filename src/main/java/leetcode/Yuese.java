package leetcode;

/**
 * Created by George on 2017/12/27.
 */
public class Yuese { //约瑟环问题
    public static void main(String[] args){
        Yuese y=new Yuese();
        int[] a=new int[15];
        int index=0;
        boolean[] status=y.cycle();
        for(int i=0;i<status.length;i++){
            if(status[i]){
                a[index]=i;
                index++;
            }
        }
        for(Integer i:a){
            System.out.println(i);
        }
    }
    public boolean[] cycle(){
        boolean[] status=new boolean[30];
        int counter=1;
        int nums=0;
        while(nums<=15) {
            for (int i = 0; i < 30; i++) {//一次
                if(nums==15){
                    nums++;
                    break;
                }
                if (status[i] == true) {
                    continue;
                }
                else if(status[i]==false && counter==9){
                    status[i] = true;
                    nums++;
                    counter = 1;
                }
                   else {
                        counter++;
                    }
                }
            }
        return status;
        }

    }

