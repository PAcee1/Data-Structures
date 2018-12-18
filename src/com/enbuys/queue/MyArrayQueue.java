package com.enbuys.queue;

import com.enbuys.array.MyArray;

/**
 * @Author: Pace
 * @Data: 2018/12/14 14:44
 * @Version: v1.0
 */
public class MyArrayQueue<E> implements Queue<E> {

    private MyArray<E> array;

    public MyArrayQueue(){
        array = new MyArray<>();
    }

    public MyArrayQueue(int capacity){
        array = new MyArray<>(capacity);
    }

    @Override
    public boolean isEmpty() {
        return array.isEmpty();
    }

    @Override
    public int getSize() {
        return array.getSize();
    }

    @Override // 插入进栈
    public void enqueue(E e) {
        array.addLast(e);
    }


    @Override
    public E dequeue() {
        return array.removeFirst();
    }

    @Override
    public E getFront() {
        return array.getFirst();
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("com.enbuys.queue.Queue：front [");
        for(int i=0;i<array.getSize();i++){
            sb.append(array.get(i));
            if(i != array.getSize()-1){
                sb.append(", ");
            }
        }
        sb.append("] tail");
        return sb.toString();
    }
}
