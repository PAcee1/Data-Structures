package com.enbuys.unionfind;

/**
 * @Author: Pace
 * @Data: 2018/12/25 14:39
 * @Version: v1.0
 * 第一版并查集，使用数组，查找非常迅速
 * unionElements O(n)
 * isConnected O(1)
 */
public class UnionFind1 implements UnionFind {

    private int[] data; // 使用集合来保存，根据索引与值形成并查集

    public UnionFind1(int size){
        data = new int[size];

        for(int i=0;i<size;i++){
            data[i] = i; // 每个元素的集合编号都不一样
        }
    }

    @Override
    public int getSize() {
        return data.length;
    }

    // 查找元素p所对应的集合编号
    private int find(int p){
        if(p < 0 || p>=data.length)
            throw new IllegalArgumentException("p is out of bound");
        return data[p];
    }

    // 查看元素p和元素q是否所属一个集合
    @Override
    public boolean isConnected(int p, int q) {
        if(data[p] == data[q]) // 判断集合编号是否相等
            return true;
        return false;
    }

    // 合并元素p与q的集合，
    @Override
    public void unionElements(int p, int q) {
        int pID = find(p);
        int qID = find(q);

        // 两者已经相连，所属一个集合
        if(qID == pID){
            return ;
        }

        // 将所有pID的值改写为qID
        for(int i=0;i<data.length;i++){
            if(data[i] == pID)
                data[i] = qID;
        }
    }
}
