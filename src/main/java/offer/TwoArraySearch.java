package offer;

/**
 * Created by George on 2018/1/5.
 */
public class TwoArraySearch { //二维数组查找
    public static void main(String[] args){
        int[][] a=new int[][]{{1,2,8,9},{2,4,9,12},{4,7,10,13},{6,8,11,15}};
        boolean[][] status=new boolean[4][4];
        TwoArraySearch ts=new TwoArraySearch();
        System.out.print(ts.search(a,0,0,15,status));
    }
    public boolean search(int[][] a,int i,int j,int key,boolean[][] status){
        int row=a.length;
        int col=a[0].length;
        if(i<0 || i>=row || j<0 || j>=col || status[i][j]){ //status说明a[i][j]已经访问过,避免陷入死循环
            return false;
        }
        else{
            if(a[i][j]==key){
                return true;
            }
            else if(a[i][j]<key){
                status[i][j]=true;
                return search(a,i+1,j,key,status) || search(a,i,j+1,key,status);
            }
            else{
                status[i][j]=true;
                return search(a,i-1,j,key,status) || search(a,i,j-1,key,status);
            }
        }
    }
}
