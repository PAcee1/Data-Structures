package com.enbuys.map;

/**
 * @Author: Pace
 * @Data: 2018/12/20 9:33
 * @Version: v1.0
 */
public class LinkedListMap<K,V> implements Map<K,V> {

    // 节点内部类
    private class Node<K,V>{
        public K key;
        public V value;
        public Node<K,V> next;

        public Node(K key, V value, Node next){
            this.key = key;
            this.value = value;
            this.next = next;
        }

        public Node(K key){
            this(key,null,null);
        }

        public Node(){
            this(null,null,null);
        }

        @Override
        public String toString(){
            return  key.toString()+":"+value.toString();
        }
    }
    private Node dummyHead;
    private int size;

    public LinkedListMap(){
        dummyHead = new Node();
        size = 0;
    }

    public Node getNode(K key){
        Node cur = dummyHead.next;
        while(cur != null){
            if(cur.key.equals(key)) {
                return cur;
            }else{
                cur = cur.next;
            }
        }
        return null;
    }

    @Override
    public V get(K key) {
        Node node = getNode(key);
        return node == null ? null: (V) node.value;
    }

    @Override
    public boolean contains(K key) {
        return getNode(key) != null;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0?true:false;
    }

    @Override
    public void add(K key, V value) {
        Node node = getNode(key);
        if(node == null) {
            dummyHead.next = new Node(key, value, dummyHead.next);
        }else {
            node.value = value;
        }
    }

    @Override
    public void set(K key, V value) {
        Node node = getNode(key);
        if(node == null){
            throw new IllegalArgumentException(key+" doesn't exist!");
        }
        node.value = value;
    }

    @Override
    public V remove(K key) {
        // 删除节点，先找到节点的前置节点
        Node cur = dummyHead.next;
        while(cur != null){
            if(cur.next.key.equals(key))
                break;
            cur = cur.next;
        }
        if(cur.next != null){
            Node delNode = cur.next;
            cur.next = delNode.next;
            delNode.next = null;
            size --;
            return (V) delNode.value;
        }
        return null;
    }


}
