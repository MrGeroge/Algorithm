package offer;

/**
 * Created by George on 2018/1/8.
 */
public class PrintMatrix { //顺时针打印矩阵
    public static void main(String[] args){
        PrintMatrix pm=new PrintMatrix();
        int[][] a=new int[][]{{1,2,3,4},{5,6,7,8,},{9,10,11,12},{13,14,15,16}};
        pm.printMatrixClockwisely(a,4,4);
    }
    public void printMatrixClockwisely(int[][] a,int rows,int cols){
        if(a==null || rows<=0 || cols<=0){
            return;
        }
        else{
            int start=0;
            while(rows>2*start && cols>2*start){
                printMatrix(a,rows,cols,start);
                start++;
            }
        }
    }
    public void printMatrix(int[][] a,int rows,int cols,int start){
            int endX=cols-1-start;
            int endY=rows-1-start;
        //打印第一行
        for(int i=start;i<=endX;i++){
            System.out.print(a[start][i]);
        }

        //打印第一列
        if(start<endY){
            for(int i=start+1;i<=endY;i++){
                System.out.print(a[i][endX]);
            }
        }

        //打印第二行
        if(start<endX && start<endY){
            for(int i=endX-1;i>=start;i--){
                System.out.print(a[endY][i]);
            }
        }

        //打印第二列
        if(start<endY-1 && start<endX){
            for(int i=endY-1;i>start;i--){
                System.out.print(a[i][start]);
            }
        }
    }
}
