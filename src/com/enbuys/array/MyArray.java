package com.enbuys.array;

/**
 * @Author: Pace
 * @Data: 2018/12/13 16:31
 * @Version: v1.0
 */
public class MyArray<E> {

    private E[] data; // 数组
    private int size; // 数组中元素个数

    public MyArray(int capacity){
        data =(E[])new Object[capacity]; // 初始化数组
        size = 0;
    }

    public MyArray(){
        this(10);
    }

    public MyArray(E[] arr){
        data = (E[])new Object[arr.length];
        for(int i = 0 ; i < arr.length ; i ++)
            data[i] = arr[i];
        size = arr.length;
    }

    // 获取数组元素个数
    public int getSize(){
        return size;
    }

    //获取数组容量
    public int getCapacity(){
        return data.length;
    }

    // 判断是否为空
    public boolean isEmpty(){
        return size == 0 ? true : false;
    }

    // 向数组的末尾添加元素
    public void addLast(E e){
        this.add(size,e);
    }

    //向数组头部添加元素
    public void addFirst(E e){
        this.add(0,e);
    }

    // 数组指定位置添加元素
    public void add(int index,E e){
        if(index < 0 || index > size){
            throw new IllegalArgumentException("Add failed,Require index>=0 && index<=size");
        }
        if(size == data.length){ // 进行动态扩容
            resize(2 * size);
        }

        for(int i = size-1;i >= index;i--){ // index后的元素向后挪一位
            data[i+1] = data[i];
        }
        data[index] = e;
        size ++;
    }

    // 查找元素
    public E get(int index){
        if(index < 0 || index >= size){
            throw new IllegalArgumentException("Get failed,Require index>=0 && index<=size");
        }
        return data[index];
    }

    // 查找最后一个元素
    public E getLast(){
        return get(size-1);
    }

    // 查找第一个元素
    public E getFirst(){
        return get(0);
    }

    // 修改元素
    public void set(int index,E e){
        if(index < 0 || index >= size){
            throw new IllegalArgumentException("Set failed,Require index>=0 && index<=size");
        }
        data[index] = e;
    }

    // 是否包含元素
    public boolean contain(E e){
        for(int i = 0;i<size ;i++){
            if(data[i].equals(e)){
                return true;
            }
        }
        return false;
    }

    // 查找元素下标
    public int find(E e){
        for(int i=0;i<size;i++){
            if(data[i].equals(e)){
                return i;
            }
        }
        return -1;
    }

    // 删除元素,并且返回被删除的元素
    public E remove(int index){
        if(index < 0 || index > size){
            throw new IllegalArgumentException("Set failed,Require index>=0 && index<=size");
        }
        E ret = data[index];
        for(int i = index;i < size - 1;i ++){
            data[i]=data[i + 1];
        }
        size --;
        data[size] = null; // loitering objects [size]指向一个对象，这个对象不会被释放，需要手动释放

        // 数组所占用容量小于一半时，进行缩容
        if(size == data.length / 4 && data.length / 2 != 0){
            resize(data.length / 2);
        }
        return ret;
    }

    // 删除第一个元素
    public E removeFirst(){
        return remove(0);
    }

    // 删除最后一个元素
    public E removeLast(){
        return remove(size - 1);
    }

    // 删除数组第一个元素e
    public boolean removeElement(E e){
        int index = find(e);
        E flag = remove(index);
        if(!flag.equals(-1)){
            return true;
        }
        return false;
    }

    // 数的交换
    public void swap(int i,int j){
        if(i<0 || i>=size || j<0 || j>=size){
            throw new IllegalArgumentException("Index is Illegal");
        }
        E e = data[i];
        data[i] = data[j];
        data[j] = e;
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("com.enbuys.array.MyArray：size:%d , capacity:%d\n",size,data.length));
        sb.append("[");
        for(int i=0;i<size;i++){
            sb.append(data[i]);
            if(i != size-1){
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }

    /**
     * 动态扩缩容
     * 扩容：newCapacity = data.length * 2
     * 缩容：newCapacity = data.length / 2
     */
    private void resize(int newCapacity) {
        E[] newData = (E[]) new Object[newCapacity];
        for(int i = 0;i < size ; i++){
            newData[i] = data[i];
        }
        data = newData;
    }
}
