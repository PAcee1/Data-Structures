package com.enbuys.set;

import com.enbuys.linkedlist.LinkedList;

/**
 * @Author: Pace
 * @Data: 2018/12/19 15:31
 * @Version: v1.0
 */
public class LinkedListSet<E> implements Set<E> {
    // 使用链表
    private LinkedList<E> linkedList;

    public LinkedListSet(){
        linkedList = new LinkedList<>();
    }


    @Override
    public void add(E e) {
        // 先判断是否存在，不存在才添加
        if(!contains(e))
            linkedList.addFirst(e);
    }

    @Override
    public void remove(E e) {
        linkedList.removeElement(e);
    }

    @Override
    public boolean isEmpty() {
        return linkedList.isEmpty();
    }

    @Override
    public int getSize() {
        return linkedList.getSize();
    }

    @Override
    public boolean contains(E e) {
        return linkedList.contains(e);
    }
}
