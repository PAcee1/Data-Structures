package com.enbuys.set;

/**
 * @Author: Pace
 * @Data: 2018/12/19 14:47
 * @Version: v1.0
 */
public interface Set<E> {
    void add(E e);
    void remove(E e);
    boolean isEmpty();
    int getSize();
    boolean contains(E e);
}
