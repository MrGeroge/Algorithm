package leetcode;

/**
 * Created by George on 2017/12/6.
 */
public class SurroundedRegions {//// TODO: 2017/12/6  
    public void solve(char[][] board) {
        if(board.length==0){
            return;
        }
        else{
            int row=board.length;
            int col=board[0].length;
            for(int i=0;i<board.length;i++){
                DFS(board,i,col-1);
                DFS(board,i,0);
            }
            for(int j=0;j<board[0].length;j++){
                DFS(board,0,j);
                DFS(board,row-1,j);
            }
            for(int i=0;i<board.length;i++){
                for(int j=0;j<board[0].length;j++){
                    if(board[i][j]=='O'){
                        board[i][j]='X';
                    }
                    if(board[i][j]=='*'){
                        board[i][j]='O';
                    }
                }
            }
        }
    }
    public void DFS(char[][] board,int row,int col){//对board[row][col]元素进行处理
        if(row<0 || row>=board.length || col<0 || col>=board[0].length){
            return;
        }
        else{
            if(board[row][col]=='O'){
                board[row][col]='*';
                DFS(board,row-1,col);
                DFS(board,row+1,col);
                DFS(board, row, col-1);
                DFS(board,row,col+1);
            }
        }
    }
}
