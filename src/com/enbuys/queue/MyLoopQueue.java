package com.enbuys.queue;

/**
 * @Author: Pace
 * @Data: 2018/12/14 15:21
 * @Version: v1.0
 */
public class MyLoopQueue<E> implements Queue<E>{

    private E[] data;
    private int front,tail;
    private int size;

    public MyLoopQueue(int capacity){
        data = (E[]) new Object[capacity+1];
        front = 0;
        tail = 0;
        size = 0;
    }

    public MyLoopQueue(){
        this(10);
    }

    @Override
    public boolean isEmpty() {
        return front == size;
    }

    @Override
    public int getSize() {
        return size;
    }

    public int getCapacity(){
        return data.length -1;
    }

    @Override // 入队
    public void enqueue(E e) {
        // 判断是否需要扩容
        if((tail + 1)%data.length == front){
            resize(getCapacity() * 2);
        }

        data[tail] = e;
        tail = (tail+1) % data.length;
        size++;
    }

    @Override // 出队
    public E dequeue() {
        // 判断队列是否为空
        if(isEmpty()){
            throw  new IllegalArgumentException("Dequeue failed,queue is empty");
        }
        E ret = data[front];
        data[front] = null;
        front ++ ;
        size --;
        //进行缩容
        if(size == getCapacity()/4 && getCapacity()/2!=0){
            resize(getCapacity()/2);
        }
        return ret;
    }

    @Override
    public E getFront() {
        // 判断队列是否为空
        if(isEmpty()){
            throw  new IllegalArgumentException("Dequeue failed,queue is empty");
        }
        return data[front];
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("LoopQueue：size=%d , capacity=%d\n",size,getCapacity()));
        sb.append("front [");
        /*for(int i=0;i<size;i++){
            sb.append(data[(i+front)%data.length]);
            if(i != size-1){
                sb.append(", ");
            }
        }
                两种遍历方式
        */
        for(int i=front;i!=tail;i=(i+1)%data.length){
            sb.append(data[i]);
            if((i+1)%data.length != tail){
                sb.append(", ");
            }
        }
        sb.append("] tail");
        return sb.toString();
    }

    // 扩容
    public void resize(int newCapacity){
        E[] newData = (E[]) new Object[newCapacity+1];
        for(int i=0;i<size ;i++){
            newData[i] = data[(i+front)%data.length];
        }
        data = newData;
        front = 0;
        tail = size;
    }
}
