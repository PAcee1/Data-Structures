package com.enbuys.linkedlist;

/**
 * @Description: 链表
 * @Author: Pace
 * @Data: 2018/12/16 12:46
 * @Version: v1.0
 */
public class LinkedList<E> {

    // 节点内部类
    private class Node<E>{
        public E e;
        public Node<E> next;

        public Node(E e, Node next){
            this.e = e;
            this.next = next;
        }

        public Node(E e){
            this(e,null);
        }

        public Node(){
            this(null,null);
        }

        @Override
        public String toString(){
            return  e.toString();
        }
    }

    private Node dummyHead; // 虚拟头结点，不存在的节点，在head的前一个，为了更方便的实现逻辑
    private int size; // 链表中元素个数

    public LinkedList(){
        this.dummyHead = new Node(null,null);
        size = 0;
    }

    // 获取链表长度
    public int getSize(){
        return this.size;
    }

    // 判断是否为空
    public boolean isEmpty(){
        return size==0;
    }

    // 向链表头添加节点
    public void addFirst(E e){
        add(0,e);
    }

    // 向链表末尾添加节点
    public void addLast(E e){
        add(size,e);
    }

    // 在链表中添加元素
    public void  add(int index, E e){
        /* 需要将插入位置的前一个节点指向新节点
           然后让新节点的next指向插入位置的节点
         */
        if(index < 0 || index > size){
            throw new IllegalArgumentException("Add failed,Illegal index.");
        }
        // 获取索引index前一个节点
        Node prev = dummyHead;
        for(int i=0; i< index;i++){
            prev = prev.next;
        }
        Node newNode = new Node(e);
        newNode.next = prev.next;
        prev.next = newNode;

        size ++;
    }

    // 链表中获取节点
    public E get(int index){
        if(index < 0 || index > size){
            throw new IllegalArgumentException("Add failed,Illegal index.");
        }
        Node cur = dummyHead.next; // 需要从头结点开始，即虚拟头结点下一个节点
        for(int i = 0;i < index;i++){
            cur = cur.next;
        }
        return (E) cur.e;
    }

    // 获取第一个节点
    public E getFirst(){
        return get(0);
    }
    // 获取最后一个节点
    public E getLast(){
        return get(size);
    }

    // 修改链表中元素
    public void set(int index,E e){
        if(index < 0 || index > size){
            throw new IllegalArgumentException("Add failed,Illegal index.");
        }
        Node cur = dummyHead.next; // 需要从头结点开始，即虚拟头结点下一个节点
        for(int i = 0;i < index;i++){ // 查找index位置的节点
            cur = cur.next;
        }
        cur.e = e;
    }

    // 链表是否包含某个节点
    public boolean contains(E e){
        Node cur = dummyHead.next; // 需要从头结点开始，即虚拟头结点下一个节点
        while(cur != null){
            if(cur.e == e)
                return true;
            cur = cur.next;
        }
        return false;
    }

    //删除链表中元素
    public E remove(int index){
        if(index < 0 || index > size){
            throw new IllegalArgumentException("Add failed,Illegal index.");
        }
        Node prev = dummyHead;
        for(int i=0;i<index;i++){
            prev = prev.next;
        }
        Node delNode = prev.next;
        prev.next = delNode.next;
        delNode.next = null;
        size --;
        return (E) delNode.e;
    }

    // 删除第一个元素
    public E removeFirst(){
        return remove(0);
    }

    // 删除最后一个元素
    public E removeLast(){
        return remove(size - 1);
    }

    @Override
    public String toString(){
        StringBuilder result = new StringBuilder();
        result.append("com.enbuys.linkedlist.LinkedList : ");
        for(Node cur = dummyHead.next; cur != null ;cur = cur.next){
            result.append(cur + " -> ");
        }
        result.append("null");
        return  result.toString();
    }
}

