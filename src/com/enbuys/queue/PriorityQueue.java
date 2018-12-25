package com.enbuys.queue;

import com.enbuys.heap.MaxHeap;

/**
 * @Author: Pace
 * @Data: 2018/12/21 15:00
 * @Version: v1.0
 * 优先队列
 */
public class PriorityQueue<E extends Comparable<E>> implements Queue<E> {

    // 底层使用堆
    private MaxHeap<E> heap;

    public PriorityQueue(){
        heap = new MaxHeap<>();
    }

    @Override
    public boolean isEmpty() {
        return heap.isEmpty();
    }

    @Override
    public int getSize() {
        return heap.getSize();
    }

    @Override // 入队
    public void enqueue(E e) {
        heap.add(e);
    }

    @Override // 出队
    public E dequeue() {
        return heap.extractMax();
    }

    @Override
    public E getFront() {
        return heap.findMax();
    }
}
