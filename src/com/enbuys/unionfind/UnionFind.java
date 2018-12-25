package com.enbuys.unionfind;

/**
 * @Author: Pace
 * @Data: 2018/12/25 14:39
 * @Version: v1.0
 */
public interface UnionFind {

    int getSize();
    boolean isConnected(int p,int q);
    void unionElements(int p, int q);

}
