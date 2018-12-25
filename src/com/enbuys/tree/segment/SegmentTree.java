package com.enbuys.tree.segment;

/**
 * @Author: Pace
 * @Data: 2018/12/24 10:11
 * @Version: v1.0
 */
public class SegmentTree<E> {

    private E[] data;
    private E[] tree;
    private Merger<E> merger; // 用来决定父节点为子节点之和还是其他的

    public SegmentTree(E[] arr,Merger<E> merger){
        this.merger = merger;
        data = (E[]) new Object[arr.length];
        for (int i=0;i<arr.length;i++){
            data[i] = arr[i];
        }
        tree = (E[]) new Object[arr.length*4];
        // 构造线段树
        buildSegmentTree(0, 0, arr.length - 1);
    }

    // 创建线段树
    private void buildSegmentTree(int treeNode,int l,int r){
        // 跳出条件，l=r即线段只有一个数值
        if(l == r){
            tree[treeNode] = data[l];
            return ;
        }

        // 左右孩子遍历
        int left = leftChild(treeNode);
        int right = rightChild(treeNode);
        int mid = l+(r-l)/2;
        // 迭代
        buildSegmentTree(left,l,mid);
        buildSegmentTree(right,mid+1,r);

        // 根据左右节点，更新父节点
        tree[treeNode] = merger.merge(tree[left] , tree[right]);
    }

    // 查找线段树中[queryL ... queryR]之前的值
    public E query(Integer queryL,Integer queryR){
        if(queryL<0 || queryL>data.length || queryR < 0 || queryR>data.length || queryL > queryR){
            throw new IllegalArgumentException("Index is Illegal");
        }
        return query(0,0,data.length-1,queryL,queryR);
    }

    // 在treeNode线段树中的[l .. r]位置寻找[queryL ... queryR]的值
    private E query(Integer treeNode,Integer l, Integer r,Integer queryL,Integer queryR){
        // 跳出条件
        if(l == queryL && r == queryR){
            return tree[treeNode];
        }

        int mid = l+(r-l)/2;
        int rightChild = rightChild(treeNode);
        int leftChild = leftChild(treeNode);

        // 判断需要查询queryL-queryR是否全在左或全在右
        if(mid+1 <= queryL){ // 说明需要查询的全在右边
            return query(rightChild,mid+1,r,queryL,queryR);
        }else if(mid >= queryR){ // 说明需要查询的全在左边
            return query(leftChild,l,mid,queryL,queryR);
        }

        E left = query(leftChild,l,mid,queryL,mid);
        E right = query(rightChild,mid+1,r,mid+1,queryR);
        return merger.merge(left,right);
    }

    // 修改线段树index索引的值
    public void set(int index,E e){
        if(index < 0 || index > data.length-1){
            throw new IllegalArgumentException("Index is illegal");
        }
        set(0,0,data.length-1,index,e);
    }

    // 在treeNode为根的线段树中查询index索引并更新值
    private void set(int treeNode,int l ,int r,int index, E e){
        // l == r说明为索引所在位置
        if(l == r){
            tree[treeNode] = e;
            return ;
        }

        int mid = l+(r-l)/2;
        int left = leftChild(treeNode);
        int right = rightChild(treeNode);
        // 判断是否在左右子树
        if(index >= mid +1) {// 说明在右子树
            set(right,mid+1,r,index,e);
        }else{ // 在左子树
            set(left,l,mid,index,e);
        }

        // 维护父节点值
        tree[treeNode] = merger.merge(tree[left],tree[right]);

    }

    public int getSize(){
        return data.length;
    }

    public E get(int index){
        if(index < 0 || index >= data.length){
            throw new IllegalArgumentException("Index is illegal");
        }
        return data[index];
    }

    // 获取左孩子位置
    private int leftChild(int index){
        return index * 2 + 1;
    }

    // 获取右孩子位置
    private int rightChild(int index){
        return index * 2 + 2;
    }

    @Override
    public String toString(){
        StringBuilder res = new StringBuilder();
        res.append('[');
        for(int i = 0 ; i < tree.length ; i ++){
            if(tree[i] != null)
                res.append(tree[i]);
            else
                res.append("null");

            if(i != tree.length - 1)
                res.append(", ");
        }
        res.append(']');
        return res.toString();
    }

}
