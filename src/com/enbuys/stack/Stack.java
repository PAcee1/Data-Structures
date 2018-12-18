package com.enbuys.stack;

/**
 * @Author: Pace
 * @Data: 2018/12/14 9:55
 * @Version: v1.0
 */
public interface Stack<E> {
    int getSize();
    boolean isEmpty();
    void push(E e);
    E pop();
    E peek();
}
