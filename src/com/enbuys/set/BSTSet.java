package com.enbuys.set;

import com.enbuys.tree.bst.BSTree;

/**
 * @Author: Pace
 * @Data: 2018/12/19 15:09
 * @Version: v1.0
 * 基于二叉搜索树实现
 */
public class BSTSet<E extends Comparable<E>> implements Set<E> {

    private BSTree<E> bsTree;

    public BSTSet(){
        bsTree = new BSTree();
    }

    @Override
    public void add(E e) {
        bsTree.add(e);
    }

    @Override
    public void remove(E e) {
        bsTree.remove(e);
    }

    @Override
    public boolean isEmpty() {
        return bsTree.isEmpty();
    }

    @Override
    public int getSize() {
        return bsTree.size();
    }

    @Override
    public boolean contains(E e) {
        return bsTree.contains(e);
    }
}
