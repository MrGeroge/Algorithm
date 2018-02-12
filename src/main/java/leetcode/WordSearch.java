package leetcode;

import java.io.IOException;

/**
 * Created by George on 2018/1/22.
 */
public class WordSearch {
    public static void main(String[] args)throws IOException{
        WordSearch ws=new WordSearch();
        char[][] board=new char[][]{{'A','B','C','E'},{'S','F','C','S'},{'A','D','E','E'}};
        String word="SEE";
        System.out.println(ws.exist(board,word));
    }
    public boolean exist(char[][] board, String word){
        if(board==null||board.length<=0||board[0].length<=0||word==null)
            return false;
        int row=board.length;
        int col=board[0].length;
        for(int i=0;i<row;i++){
            for(int j=0;j<col;j++){
                if(dfs(i,j,board,word,0)){
                    return true;
                }
            }
        }
        return false;
    }
    public boolean dfs(int i,int j,char[][] board, String word, int index){
        if(index==word.length())
            return true;
        if(i<0||i>=board.length||j<0||j>=board[0].length||board[i][j]!=word.charAt(index))
            return false;
        board[i][j]='*';
        boolean res=dfs(i+1,j,board,word,index+1)||dfs(i-1,j,board,word,index+1)||
                dfs(i,j+1,board,word,index+1)||dfs(i,j-1,board,word,index+1);
        board[i][j]=word.charAt(index);
        return res;
    }
}
