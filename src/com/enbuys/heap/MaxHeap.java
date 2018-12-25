package com.enbuys.heap;

import com.enbuys.array.MyArray;

/**
 * @Author: Pace
 * @Data: 2018/12/21 10:57
 * @Version: v1.0
 */
public class MaxHeap<E extends Comparable<E>> {

    private MyArray<E> array;

    public MaxHeap(int capacity){
        array = new MyArray(capacity);
    }

    public MaxHeap(){
        array = new MyArray();
    }

    public MaxHeap(E[] arr){
        array = new MyArray<E>(arr);
        for(int i = parent(arr.length - 1) ; i >= 0 ; i --)
            siftDown(i);
    }

    public int getSize(){
        return array.getSize();
    }

    public boolean isEmpty(){
        return array.isEmpty();
    }

    // 向堆中添加元素
    public void add(E e){
        array.addLast(e);
        siftUp(array.getSize()-1);
    }

    // 查看最大根节点
    public E findMax(){
        if(array.getSize() == 0){
            throw new IllegalArgumentException("Heap is empty,can not findMax!");
        }
        return array.get(0);
    }

    // 获取最大根节点
    public E extractMax(){
        E res = findMax();
        // 使最后一个节点替换根节点
        array.swap(0,getSize()-1);
        // 删除最后一个节点，即被取出的根节点
        array.removeLast();
        // 新根节点进行下沉操作
        siftDown(0);
        return res;
    }

    // 取出堆中的最大元素，并且替换成元素e
    public E replace(E e){

        E ret = findMax();
        array.set(0, e);
        siftDown(0);
        return ret;
    }

    // 下沉操作
    private void siftDown(int k){
        // 进行循环，循环条件：此节点有左孩子节点
        while(leftChild(k) < getSize()){ // 小于数组容量说明有左孩子
            // 获取左孩子
            int child = leftChild(k);
            //判断右孩子是否存在，且比左孩子大
            if(child+1 < getSize() &&
                    array.get(child+1).compareTo(array.get(child)) > 0){
                // 说明需要替换右孩子
                child = rightChild(k);
            }
            // 判断此节点与最大的孩子节点谁大
            if(array.get(k).compareTo(array.get(child)) > 0){
                // 如果大于孩子节点，直接跳出不进行操作
                break;
            }
            // 进行父子调换
            array.swap(k,child);
            // 使当前节点索引变为子树的索引
            k = child;
        }
    }

    // 上浮元素
    private void siftUp(int index){
        // 如果当前节点数值比父节点大，与之交换
        while(index > 0 && array.get(parent(index)).compareTo(array.get(index)) < 0){
            // 交换
            array.swap(index,parent(index));
            index = parent(index); // 把index换成父节点的，继续循环
        }
    }

    // 根据index获取父节点所在索引
    private int parent(int index){
        if(index == 0){
            throw new IllegalArgumentException("index-0 doesn't have parent");
        }
        return (index-1)/2;
    }

    // 根据index获取左孩子所在索引
    private int leftChild(int index){
        return index*2+1;
    }

    // 根据index获取右孩子所在索引
    private int rightChild(int index){
        return index*2+2;
    }
}
