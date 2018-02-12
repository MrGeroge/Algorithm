package offer;

import java.util.ArrayList;

/**
 * Created by George on 2018/1/8.
 */
public class isBinarySearchTree { //根据给定的后序遍历结果，判断该二叉搜索树是否存在
    public static void main(String[] args){
        isBinarySearchTree ibs=new isBinarySearchTree();
        int[] a=new int[]{7,4,6,5};
        System.out.print(ibs.isBST(a,4));
    }
    public boolean isBST(int[] a,int length){
        if(a==null || length<=0){
            return false;
        }
        else{
            int index=0;
            int root=a[length-1];
            for(;index<length-1;index++){ //0~index-1均满足左子树各节点小于根节点,则要判断index~length-2右子树
                if(a[index]>root){
                    break;
                }
            }
            for(int j=index;j<length-1;j++){
                if(a[j]<root){
                    return false;
                }
            }
            boolean left=true;//默认空树是二叉搜索树
            boolean right=true;
            if(index>0){//左子树存在
               left=isBST(a,index);
            }
            if(index<length-1){//右子树存在
                int[] b=new int[length-1-index];
                for(int i=0;i<b.length;i++){
                    b[i]=a[index+i];
                }
                right=isBST(b,length-1-index);
            }
            return (left && right);
        }
    }
}
