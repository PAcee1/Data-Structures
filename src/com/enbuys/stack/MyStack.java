package com.enbuys.stack;

import com.enbuys.array.MyArray;

/**
 * @Author: Pace
 * @Data: 2018/12/14 10:07
 * @Version: v1.0
 */
public class MyStack<E> implements Stack<E> {

    private MyArray<E> array;

    public MyStack(int capacity){
        array = new MyArray<>(capacity);
    }

    public MyStack(){
        array = new MyArray<>();
    }

    @Override
    public int getSize() {
        return array.getSize();
    }

    @Override
    public boolean isEmpty() {
        return array.isEmpty();
    }

    public int getCapacity() {
        return array.getCapacity();
    }

    @Override // 插入进栈
    public void push(E e) {
        array.addLast(e);
    }

    @Override // 弹出最后一个
    public E pop() {
        return array.removeLast();
    }

    @Override // 获取最后一个元素
    public E peek() {
        return array.getLast();
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("com.enbuys.stack.Stack：[");
        for(int i=0;i<array.getSize();i++){
            sb.append(array.get(i));
            if(i != array.getSize()-1){
                sb.append(", ");
            }
        }
        sb.append("] top");
        return sb.toString();
    }
}
