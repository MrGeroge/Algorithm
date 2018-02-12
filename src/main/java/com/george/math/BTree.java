package com.george.math;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

/**
 * Created by George on 2017/9/22.
 *
 * 一.B树与红黑树同为平衡查找树，单其降低磁盘I/O操作次数更好（与B树的高度成正比），B树的根节点始终保存在内存，一旦修改需要重新持久化，任何被当作参数的节点传输之前，都需要进行一次DISK-READ
 * 二.B树的特性
 * 1）B树的节点可拥有多个子女
 * 2）包含n个节点的B树的高度为O(lgn)
 * 3）B树的内节点x包含n（x）个关键字，则x就有n[x]+1个子女（即x分成n[x]+1个子域），每个子域由一个子女处理
 *三.B树的属性：
 * 1）每个节点对应的属性
 * a)n[x] 当前节点x的关键字数
 * b)n[x]个关键字以升序排列
 * c)leaf[x]，如果x是叶子，则为True，否则为False
 * 2)每个内节点x包含n[x]+1个指向子女的指针ci;每个叶节点无此域
 * 3）子节点中的关键字一定位于父节点关键字域中
 * 4）每个叶节点具有相同的深度，即树高h
 * 5）每个节点能包含的关键字数有一个上界和下界，称为B树的最小度数
 * a)每个非根节点至少有t-1个关键字（下界），即有至少t个子女，如果B树非空，则根节点至少包含一个关键字
 * b)每个节点至多包含2t-1个关键字（上界），即至多有2t个子女，称为一个节点满
 *
 *
 *
 *
 */
public class BTree {
    private BTreeNode root; //B树根节点

    public static void main(String[] args) throws Exception {//每个节点对应一个文件
        BTree bTree = new BTree();
        bTree.createBTree();
        //bTree.bTreeDelete('F',3);
        bTree.bTreeDeleteNoNone(bTree.getRoot(),'F',3);
        bTree.bTreeDeleteNoNone(bTree.getRoot(),'M',3);
        bTree.bTreeDeleteNoNone(bTree.getRoot(),'G',3);
        bTree.bTreeDelete('D',3);
        bTree.bTreeDelete('B',3);
        bTree.bTreeDelete('V',3);
        SearchResult result=bTree.bTreeSearch(bTree.getRoot(),'P');
        if(result!=null){
            System.out.println("failed");
        }
        else{
            System.out.println("success");
        }
        /*SearchResult result = bTree.bTreeSearch(bTree.getRoot(), 'N');
        System.out.print("N in node" + result.getNode().getId() + "and index=" + result.getIndex());
        bTree.bTreeInsert('B',3); //最小度数t=3
        result=bTree.bTreeSearch(bTree.getRoot(),'B');
        bTree.bTreeInsert('Q',3);
        result=bTree.bTreeSearch(bTree.getRoot(),'Q');
        bTree.bTreeInsert('L',3);
        result=bTree.bTreeSearch(bTree.getRoot(),'L');
        bTree.bTreeInsert('F',3);
        result=bTree.bTreeSearch(bTree.getRoot(),'F');
       */




    }

    public BTreeNode getRoot() {
        return root;
    }

    public void setRoot(BTreeNode root) {
        this.root = root;
    }

    public SearchResult bTreeSearch(BTreeNode node, char k) throws Exception {//从当前节点开始查找关键字k所在节点x，以及其关键字索引i，返回(x,i)
        int i = 0;
        while (i < node.getKeyNum() && k > node.getKeys().get(i)) {
            i++;
        }
        if (i < node.getKeyNum() && k == node.getKeys().get(i)) {
            SearchResult result = new SearchResult();
            result.setNode(node);
            result.setIndex(i);
            return result;

        } else {
            if (node.getLeaf() == true) {
                return null;
            } else {
                //BTreeNode child = BTreeNode.readNode(node.getChilds().get(i).getId());
                BTreeNode child=node.getChilds().get(i);
                return bTreeSearch(child, k);
            }
        }
    }

   /*public void createBTree() throws Exception {
        root = new BTreeNode();
        root.setId(0);
        root.setKeyNum(4);
        List<Character> keys = new ArrayList<Character>();
        List<BTreeNode> childs = new ArrayList<BTreeNode>();
        keys.add(new Character('G'));
        keys.add(new Character('M'));
        keys.add(new Character('P'));
        keys.add(new Character('X'));
        root.setKeys(keys);
        root.setLeaf(false); //root需要持久化到磁盘

        BTreeNode node1 = new BTreeNode();
        node1.setId(1);
        node1.setLeaf(true);
        node1.setKeyNum(4);
        keys = new ArrayList<Character>();
        keys.add(new Character('A'));
        keys.add(new Character('C'));
        keys.add(new Character('D'));
        keys.add(new Character('E'));
        node1.setKeys(keys);
        node1.setChilds(null);

        BTreeNode node2 = new BTreeNode();
        node2.setId(2);
        node2.setLeaf(true);
        node2.setKeyNum(2);
        keys = new ArrayList<Character>();
        keys.add(new Character('J'));
        keys.add(new Character('K'));
        node2.setKeys(keys);
        node2.setChilds(null);

        BTreeNode node3 = new BTreeNode();
        node3.setId(3);
        node3.setLeaf(true);
        node3.setKeyNum(2);
        keys = new ArrayList<Character>();
        keys.add(new Character('N'));
        keys.add(new Character('O'));
        node3.setKeys(keys);
        node3.setChilds(null);

        BTreeNode node4 = new BTreeNode();
        node4.setId(4);
        node4.setLeaf(true);
        node4.setKeyNum(5);
        keys = new ArrayList<Character>();
        keys.add(new Character('R'));
        keys.add(new Character('S'));
        keys.add(new Character('T'));
        keys.add(new Character('U'));
        keys.add(new Character('V'));
        node4.setKeys(keys);
        node4.setChilds(null);

        BTreeNode node5 = new BTreeNode();
        node5.setId(5);
        node5.setLeaf(true);
        node5.setKeyNum(2);
        keys = new ArrayList<Character>();
        keys.add(new Character('Y'));
        keys.add(new Character('Z'));
        node5.setKeys(keys);
        node5.setChilds(null);

        childs.add(node1);
        childs.add(node2);
        childs.add(node3);
        childs.add(node4);
        childs.add(node5);
        root.setChilds(childs);

        BTreeNode.writeNode(root);
        BTreeNode.writeNode(node1);
        BTreeNode.writeNode(node2);
        BTreeNode.writeNode(node3);
        BTreeNode.writeNode(node4);
        BTreeNode.writeNode(node5);
    }*/

   public void createBTree(){
       root = new BTreeNode();
       root.setKeyNum(1);
       root.setLeaf(false);
       root.setId(0);
       List<Character> keys=new ArrayList<Character>();
       keys.add('P');
       root.setKeys(keys);

       BTreeNode node1=new BTreeNode();
       node1.setId(1);
       node1.setKeyNum(3);
       node1.setLeaf(false);
       keys=new ArrayList<Character>();
       keys.add('C');
       keys.add('G');
       keys.add('M');
       node1.setKeys(keys);

       BTreeNode node2=new BTreeNode();
       node2.setId(2);
       node2.setLeaf(false);
       node2.setKeyNum(2);
       keys=new ArrayList<Character>();
       keys.add('T');
       keys.add('X');
       node2.setKeys(keys);

       BTreeNode node3=new BTreeNode();
       node3.setId(3);
       node3.setLeaf(true);
       node3.setKeyNum(2);
       keys=new ArrayList<Character>();
       keys.add('A');
       keys.add('B');
       node3.setKeys(keys);

       BTreeNode node4=new BTreeNode();
       node4.setId(4);
       node4.setLeaf(true);
       node4.setKeyNum(3);
       keys=new ArrayList<Character>();
       keys.add('D');
       keys.add('E');
       keys.add('F');
       node4.setKeys(keys);

       BTreeNode node5=new BTreeNode();
       node5.setId(5);
       node5.setLeaf(true);
       node5.setKeyNum(3);
       keys=new ArrayList<Character>();
       keys.add('J');
       keys.add('K');
       keys.add('L');
       node5.setKeys(keys);

       BTreeNode node6=new BTreeNode();
       node6.setId(6);
       node6.setLeaf(true);
       node6.setKeyNum(2);
       keys=new ArrayList<Character>();
       keys.add('N');
       keys.add('O');
       node6.setKeys(keys);

       BTreeNode node7=new BTreeNode();
       node7.setId(7);
       node7.setLeaf(true);
       node7.setKeyNum(3);
       keys=new ArrayList<Character>();
       keys.add('Q');
       keys.add('R');
       keys.add('S');
       node7.setKeys(keys);

       BTreeNode node8=new BTreeNode();
       node8.setId(8);
       node8.setLeaf(true);
       node8.setKeyNum(2);
       keys=new ArrayList<Character>();
       keys.add('U');
       keys.add('V');
       node8.setKeys(keys);

       BTreeNode node9=new BTreeNode();
       node9.setId(9);
       node9.setLeaf(true);
       node9.setKeyNum(2);
       keys=new ArrayList<Character>();
       keys.add('Y');
       keys.add('Z');
       node9.setKeys(keys);

       List<BTreeNode> childs=new ArrayList<BTreeNode>();
       childs.add(node1);
       childs.add(node2);
       root.setChilds(childs);

       childs=new ArrayList<BTreeNode>();
       childs.add(node3);
       childs.add(node4);
       childs.add(node5);
       childs.add(node6);
       node1.setChilds(childs);

       childs=new ArrayList<BTreeNode>();
       childs.add(node7);
       childs.add(node8);
       childs.add(node9);
       node2.setChilds(childs);

       node3.setChilds(null);
       node4.setChilds(null);
       node5.setChilds(null);
       node6.setChilds(null);
       node7.setChilds(null);
       node8.setChilds(null);
       node9.setChilds(null);

   }


    public void bTreeSplitChild(BTreeNode x, int i, BTreeNode y, int t) throws Exception {//分割节点y(满节点，即关键字有2t-1个),t为最小度数（t-1为子节点中间关键字),i为子节点关键字插入父节点的index（0开始计数）
        BTreeNode z = new BTreeNode(); //z为分割后的新节点
        z.setLeaf(y.getLeaf());
        z.setKeyNum(t - 1);
        List<Character> tmp = new ArrayList<Character>();
        for (int j = 0; j < (t - 1); j++) {
            tmp.add(y.getKeys().get(j + t));
        }
        z.setKeys(tmp);
        List<BTreeNode> childs = new ArrayList<BTreeNode>();
        if (!y.getLeaf()) {
            for (int j = 0; j < t; j++) {
                childs.add(y.getChilds().get(j + t));
            }
        }
        z.setChilds(childs);
        y.setKeyNum(t - 1);
        childs = x.getChilds();
        childs.add(new BTreeNode());
        for (int p = x.getKeyNum(); p > i; p--) {
            childs.set(p + 1, x.getChilds().get(p));
        }
        childs.set(i+1, z);
        x.setChilds(childs);
        tmp = x.getKeys();
        tmp.add(new Character('0'));
        for (int p = (x.getKeyNum() - 1); p >= i; p--) {
            tmp.set(p + 1, x.getKeys().get(p));
        }
        tmp.set(i, y.getKeys().get(t - 1));
        x.setKeys(tmp);
        x.setKeyNum(x.getKeyNum() + 1);
        BTreeNode node=x.getChilds().get(i);
        List<Character> chars=node.getKeys();
        int size=chars.size();
        int index=0;
        Iterator<Character> it=chars.iterator();
        while(it.hasNext()){ //安全删除(t-1)~(n-1)的keys
            it.next();
            if(index>=(t-1)){
                it.remove();
            }
            index++;
        }
        node.setKeys(chars);
        BTreeNode.writeNode(y);
        BTreeNode.writeNode(z);
        BTreeNode.writeNode(x);


    }

    public void bTreeInsert(char k,int t) throws Exception { //往B树插入关键字K
        BTreeNode r=this.root;
        if(r.getKeyNum()==(2*t-1)) { //判断根节点是否满
            BTreeNode s = new BTreeNode();
            this.root = s;  //
            s.setLeaf(false);
            s.setKeyNum(0);
            List<BTreeNode> childs = new ArrayList<BTreeNode>();
            childs.add(r);
            s.setChilds(childs);
            bTreeSplitChild(s, 0, r, t); //对r进行分裂
            bTreeInsertNotFull(s, k, t);
        }
        else{
                bTreeInsertNotFull(r,k,t);
            }

    }

    public void bTreeMergeChild(BTreeNode x,int i,BTreeNode y,BTreeNode z,int t){//i为父节点x的第i个关键字
            y.setKeyNum(2*t-1); //2t-1为父节点x与子节点y，z合并之后的节点y的关键字个数
            List<Character> keys=y.getKeys();
            keys.add(new Character('0'));
            keys.set(t-1,x.getKeys().get(i));
          for(int j=t;j<(2*t-1);j++){
              keys.add(z.getKeys().get(j-t));
          }
            //y的关键字准备就绪
        y.setKeys(keys);
        if(!y.getLeaf() && !z.getLeaf()){//如果子节点y,z非叶子,
            List<BTreeNode> childs=y.getChilds();
            for(int j=0;j<z.getChilds().size();j++){
                childs.add(z.getChilds().get(j));
            }
        }
        //对x修改，删除i+1处的子节点
        List<BTreeNode> childs=x.getChilds();

        Iterator<BTreeNode> it=childs.iterator();
        int p=0;
        while(it.hasNext()){
                it.next();
                p++;
                if(p>(i+1)){
                    it.remove();
                    break;
                }
            }
        x.setChilds(childs);
        x.setKeyNum(x.getKeyNum()-1);
        keys=x.getKeys();
        Iterator<Character> its=keys.iterator();
        p=0;
        while(its.hasNext()){
            its.next();
            p++;
            if(p>i){
                its.remove();
                break;
            }
        }
    }

    public void bTreeDelete(char k,int t) throws Exception { //删除关键字k
        BTreeNode r=this.root;
        if(r.getKeyNum()==1){
            BTreeNode y=r.getChilds().get(0);
            BTreeNode z=r.getChilds().get(1);
            if(y.getKeyNum()==(t-1) &&z.getKeyNum()==(t-1)){ //
                bTreeMergeChild(r,0,y,z,t);
                this.root=y;
                bTreeDeleteNoNone(y,k,t);
            }
            else{
                bTreeDeleteNoNone(r,k,t);
            }
        }
        else{
            bTreeDeleteNoNone(r,k,t);
        }
    }

    public void bTreeDeleteNoNone(BTreeNode x,char k,int t) throws Exception { //在B树中删除关键字k
        SearchResult result=bTreeSearch(x,k);
        int index = result.getIndex();
        if(x.getLeaf()){ //根据x是否为叶节点划分
            if(k==x.getKeys().get(index)){ //如果k在x中,直接删除index节点
                List<Character> keys = x.getKeys();
                Iterator<Character> it = keys.iterator();
                int p = 0;
                while (it.hasNext()) {
                    it.next();
                    p++;
                    if (p > index) {
                        it.remove();
                        break;
                    }
                }
                x.setKeys(keys);
                x.setKeyNum(x.getKeyNum() - 1);
            }
        }
        else{ //x为内节点
            int i=0;
            while(i<x.getKeyNum() && k>x.getKeys().get(i)){ //寻找key的索引i
                i++;
            }
            BTreeNode y=x.getChilds().get(i); //y一定存在,可能为x的最后一个儿子或者为key的左儿子
            BTreeNode z=new BTreeNode();
            if(i<x.getKeyNum()){ //key在内节点x中
                z=x.getChilds().get(i+1);
            }
            if(k==x.getKeys().get(i)){
                if(y.getKeyNum()>=t){ //前于k的子节点y的关键字个数至少为t，2a)
                    char k1 = bTreeSearchPrev(y);
                    bTreeDeleteNoNone(y, k1, t);
                    List<Character> keys = x.getKeys();
                    keys.set(i, k1);
                    x.setKeys(keys);
                }
                else if(z.getKeyNum()>=t){//后于k的子节点y的关键字个数至少为t,2b)
                    char k2 = bTreeSearchNext(z);
                    bTreeDeleteNoNone(z, k2, t);
                    List<Character> keys = x.getKeys();
                    keys.set(i, k2);
                    x.setKeys(keys);
                }
                else{ //y,z都只有t-1，则需要进行合并
                    bTreeMergeChild(x, i, y, z, t);
                    bTreeDeleteNoNone(y, k, t);
                }
            }
            else{ //k不在内节点x中,3)
                BTreeNode p=new BTreeNode();
                if(i>=1){
                p=x.getChilds().get(i-1);
                }
                if(y.getKeyNum()==(t-1)){//ci[y]只包含t-1个关键字
                    if(i>=1 && p.getKeyNum()>=t){ //相邻兄弟p至少t个关键字
                        bTreeShiftToRightChild(x,i-1,p,y);
                    }
                    else if(i<x.getKeyNum() && z.getKeyNum()>=t){//相邻兄弟z至少t个关键字
                        bTreeShiftToLeftChild(x,i,y,z);
                    }
                    else if(i>=1){ //3b,y以及y的所有相邻兄弟p或者z的关键字都是t-1个,将y与其中一个兄弟合并
                        bTreeMergeChild(x,i,p,y,t);
                        y=p;
                    }
                    else{
                        bTreeMergeChild(x,i,y,z,t);
                    }
                }
                bTreeDeleteNoNone(y,k,t);
            }

        }
    }


    public void bTreeShiftToRightChild(BTreeNode x,int index,BTreeNode y,BTreeNode z){ //转移到右边的子节点
        z.setKeyNum(z.getKeyNum()+1); //x有个关键字下放到z,y有个关键字上升到x
        int j=z.getKeyNum()-1;
        List<Character> keys=z.getKeys();
        keys.add(new Character('0')); //
        while(j>=1){
            keys.set(j,z.getKeys().get(j-1));
            j=j-1;
        }
        keys.set(0,x.getKeys().get(index)); //x有个关键字下放到z的起始
        z.setKeys(keys);
        keys=x.getKeys();
        keys.set(index,y.getKeys().get(y.getKeyNum()-1)); //y有个关键字上升到x
        x.setKeys(keys);

        List<Character> tmp=y.getKeys();
        Iterator<Character> it=tmp.iterator();
        int p=0;
        while(it.hasNext()){
            it.next();
            p++;
            if(p>(tmp.size()-1)){
                it.remove();
                break;
            }
        }
        y.setKeys(tmp);
        y.setKeyNum(y.getKeyNum()-1);

        if(!z.getLeaf()){ //z非叶子节点
            int m=z.getKeyNum()-1;
            List<BTreeNode> childs=z.getChilds();
            childs.add(new BTreeNode());
            while(m>=0){
                childs.set(m+1,z.getChilds().get(m)); //腾出c0
                m--;
            }
            childs.set(0,y.getChilds().get(y.getKeyNum()+1)); //z的起始子节点为y的最后儿子
            z.setChilds(childs);

            //删除y的关键字,keyNum-1,删除末尾子节点

            p=0;
            childs=y.getChilds();
            Iterator<BTreeNode> its=childs.iterator();
            while(its.hasNext()){
                its.next();
                p++;
                if(p>(childs.size()-1)){
                    its.remove();
                    break;
                }
            }
            y.setChilds(childs);
        }
    }

    public void bTreeShiftToLeftChild(BTreeNode x,int index,BTreeNode y,BTreeNode z){ //转移到左边的子节点
       y.setKeyNum(y.getKeyNum()+1);
        List<Character> keys=y.getKeys();
        keys.add(x.getKeys().get(index)); //将x的关键字下放到y
        y.setKeys(keys);
        keys=x.getKeys();
        keys.set(index,z.getKeys().get(0));//,z的关键字上升到x
        z.setKeyNum(z.getKeyNum()-1);
        keys=z.getKeys();
        Iterator<Character> it=keys.iterator();
        it.next();
        it.remove();
        z.setKeys(keys);
        if(!z.getLeaf()){
            List<BTreeNode> childs=y.getChilds();
            childs.add(z.getChilds().get(0));
            y.setChilds(childs);
            childs=z.getChilds();
            Iterator<BTreeNode> its=childs.iterator();
            its.next();
            its.remove();
            z.setChilds(childs);
        }

    }
    //寻找前驱
    public char bTreeSearchPrev(BTreeNode y){
        BTreeNode x=y;
        int i=x.getKeyNum();
        while(!x.getLeaf()){
            x=x.getChilds().get(i);
            i=x.getKeyNum();
        }
        return x.getKeys().get(i-1);
    }


    //寻找后继
    public char bTreeSearchNext(BTreeNode y){
        BTreeNode x=y;
        while(!x.getLeaf()){
            x=x.getChilds().get(0);
        }
        return x.getKeys().get(0);
    }
    public void bTreeInsertNotFull(BTreeNode x,char k,int t) throws Exception { //从x节点开始插入关键字k
        int i=x.getKeyNum()-1;
        if(x.getLeaf()){ //x为叶子节点
            while(i>=0 && k<x.getKeys().get(i)){ //找到插入位置i
                i=i-1;
            }
            List<Character> list=x.getKeys();
            list.add(new Character('0'));
            int j=i+1;
            for(int p=(x.getKeyNum()-1);p>=j;p--){
                list.set(p+1,x.getKeys().get(p));
            }
            list.set(j,k);
            x.setKeys(list);
            x.setKeyNum(x.getKeyNum()+1);
            return;
            //BTreeNode.writeNode(x); //持久化
        }
        else {
            while(i>=0 && k<x.getKeys().get(i)){ //内节点插入
                i=i-1;
            }
            i=i+1;
            //BTreeNode child=BTreeNode.readNode(i);//读取子节点child
            BTreeNode child=x.getChilds().get(i);
            if(child.getKeyNum()==(2*t-1)){ //子节点满
                bTreeSplitChild(x,i,child,t);//分裂子节点
                if(k>x.getKeys().get(i)){
                    i=i+1;
                }
            }
            bTreeInsertNotFull(x.getChilds().get(i),k,t);

        }
    }
}

class BTreeNode implements Serializable{//B树节点
    private int keyNum; //实际关键字数
    private List<Character> keys=new ArrayList<Character>(); //节点关键字
    private List<BTreeNode> childs=new ArrayList<BTreeNode>(); //儿女
    private Boolean isLeaf; //是否为叶子节点
    private int id; //节点的唯一标识

    public BTreeNode(){
        this.keyNum=0;
        this.keys=null;
        this.childs=null;
        this.isLeaf=true;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getKeyNum() {
        return keyNum;
    }

    public void setKeyNum(int keyNum) {
        this.keyNum = keyNum;
    }

    public List<Character> getKeys() {
        return keys;
    }

    public void setKeys(List<Character> keys) {
        this.keys = keys;
    }

    public List<BTreeNode> getChilds() {
        return childs;
    }

    public void setChilds(List<BTreeNode> childs) {
        this.childs = childs;
    }

    public Boolean getLeaf() {
        return isLeaf;
    }

    public void setLeaf(Boolean leaf) {
        isLeaf = leaf;
    }

    public static void writeNode(BTreeNode node) throws Exception {
        File file=new File("/Users/George/Desktop/test"+node.getId());
        if(!file.exists()){
             file.createNewFile();
        }
        FileOutputStream fos=new FileOutputStream(file);
        ObjectOutputStream oos=new ObjectOutputStream(fos);
        oos.writeObject(node);
        oos.close();
        fos.close();
    }

    public static BTreeNode readNode(int id) throws Exception{
        FileInputStream fis=new FileInputStream("/Users/George/Desktop/test"+id);
        ObjectInputStream ois=new ObjectInputStream(fis);
        BTreeNode node=(BTreeNode)ois.readObject();
        ois.close();
        fis.close();
        return node;
    }


}
class SearchResult{
    private BTreeNode node;
    private int index;

    public BTreeNode getNode() {
        return node;
    }

    public void setNode(BTreeNode node) {
        this.node = node;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}
