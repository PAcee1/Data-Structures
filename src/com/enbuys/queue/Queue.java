package com.enbuys.queue;

/**
 * @Author: Pace
 * @Data: 2018/12/14 14:43
 * @Version: v1.0
 */
public interface Queue<E> {
    boolean isEmpty();
    int getSize();
    void enqueue(E e);
    E dequeue();
    E getFront();
}
